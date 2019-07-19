package com.brevitaz.models

import play.api.libs.json.Json

case class ResponseModel(success:Boolean, message: String)

object ResponseModel {
  implicit val format = Json.format[ResponseModel]
}
