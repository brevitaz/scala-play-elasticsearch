package com.brevitaz.elasticsearch

import com.brevitaz.models.Product
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties}
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global

object ElasticOperation {

  private val client = ElasticClient(JavaClient(ElasticProperties("http://localhost:9200")))

  def isIndexExist(indexName: String) = client.execute {
    indexExists(indexName)
  }

  def createEsIndex(indexName: String) = client.execute {
    createIndex(indexName).mapping(emptyMapping)
  }

  def insertData(indexName: String, product: Product) = client.execute {
      indexInto(indexName).source(Json.toJson(product).toString())
  }

  def searchProduct(indexName: String, keyword: String) = client.execute {
      search(indexName).query(multiMatchQuery(keyword).fields("title", "description"))
  }.map(response => response.result.to[Product])

}
