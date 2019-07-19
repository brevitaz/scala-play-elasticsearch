package com.brevitaz.models

import play.api.libs.json.Json

case class RequestModel(indexName:String, product: Product)

object RequestModel {
  implicit val format = Json.format[RequestModel]
}