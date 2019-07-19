package com.brevitaz.controllers

import com.brevitaz.elasticsearch.ElasticOperation
import com.brevitaz.services.ProductService
import javax.inject._
import com.brevitaz.models.{RequestModel, ResponseModel, SearchRequest}
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProductController @Inject()(cc: ControllerComponents, productService: ProductService) extends AbstractController(cc) {

  def createIndex(indexName: String) = Action.async {
    productService.checkAndCreateIndex(indexName).map(r =>  Ok(Json.toJson(r)))
  }

  def insertProduct = Action(parse.json).async {
    implicit request =>

    val validation = request.body.validate[RequestModel]
    validation.fold(
      invalid = { fieldErrors => {
        Future(BadRequest(Json.obj("success" -> false, "message" -> JsError.toJson(fieldErrors))))
        }
      },
      valid = requestModel => {
        ElasticOperation.insertData(requestModel.indexName, requestModel.product).map(response => {
          Ok(Json.toJson(ResponseModel(true, s"Product is created with _id : ${response.result.id}")))
        }
        )
      }
    )
  }

  def search: Action[JsValue] = Action.async(parse.json) {
    implicit request =>

    val validation = request.body.validate[SearchRequest]
    validation.fold(
      invalid = { fieldErrors => {
        Future(BadRequest(Json.obj("success" -> false, "message" -> JsError.toJson(fieldErrors))))
        }
      },
      valid = requestModel => {
        ElasticOperation.searchProduct(requestModel.indexName, requestModel.keyword).map(response => {
          Ok(Json.toJson(response))
        })
      }
    )
  }
}
