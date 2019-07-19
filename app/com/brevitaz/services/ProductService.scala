package com.brevitaz.services

import com.brevitaz.elasticsearch.ElasticOperation
import com.brevitaz.models.ResponseModel
import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProductService @Inject()() {

  def checkAndCreateIndex(indexName: String) = {
    for {
      isExist <- ElasticOperation.isIndexExist(indexName).map(_.result.exists)
      response <- createIndex(indexName, isExist)
    } yield {
      response
    }
  }

  private def createIndex(indexName: String, isExist: Boolean) ={
    if(isExist) Future.successful(ResponseModel(false, "Index already exist."))
    else {
      ElasticOperation.createEsIndex(indexName).map(response => {
        if(response.result.acknowledged) ResponseModel(true, "Index is created")
        else ResponseModel(false, "Some error occurred")
      })
    }
  }
}
