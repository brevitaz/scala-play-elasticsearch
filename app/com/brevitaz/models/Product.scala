package com.brevitaz.models

import com.sksamuel.elastic4s.{Hit, HitReader}
import play.api.libs.json.Json
import scala.util.Try

case class Product(id: Long, title: String, description: String, rating: Long, price: Double)

object Product {
  implicit val personFormat = Json.format[Product]

  implicit val HitReader: HitReader[Product] = (hit: Hit) => Try(Product(
    hit.sourceField("id").toString.toLong,
    hit.sourceField("title").toString,
    hit.sourceField("description").toString,
    hit.sourceField("rating").toString.toLong,
    hit.sourceField("price").toString.toDouble,
  ))
}
