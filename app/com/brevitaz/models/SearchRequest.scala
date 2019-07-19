package com.brevitaz.models

import play.api.libs.json.Json

case class  SearchRequest(indexName:String, keyword: String)

object SearchRequest {
  implicit val format = Json.format[SearchRequest]
}
