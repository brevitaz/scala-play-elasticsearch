# Play Elasticsearch demo
This application demonstrates implementation of Elasticsearch with Play framework and Scala.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites


- Java v8+

- Elasticsearch v7.0.1

- sbt build-tool



## Build and run the project

To build and run the project:

1. Make sure you have Elasticsearch running on `port 9200`. 

2. Use a command window to change into the example project directory, for
  example: `cd play-elastic4s-scala`

3. Build the project. Enter: `sbt run`. The project builds and starts the
  embedded HTTP server. Since this downloads libraries and dependencies,
  the amount of time required depends partly on your connection's speed.

4. After the message `Server started, ...` displays, enter the following
  URL in a browser: <http://localhost:9000>

## APIs

##### 1 Create index

```http
GET /api/create-index/{indexName}
```

##### 2 Add product
```http
POST /api/create-product
```

Request format: 
```javascript
{
    "indexName": String,
    "product": {
        "id": Long,
        "title": String,
        "description": String,
        "rating": Long,
        "price": Double
    }
}
```

##### 3 search product
```http
POST /api/search-product
```

Request format: 
```javascript
{
    "indexName": String,
    "keyword": String
}
```

## Built With

* [Scala-2.13](https://docs.scala-lang.org/) - Language
* [Play Framework-2.7](https://www.playframework.com/documentation/2.7.x/ScalaHome)
  \- The web framework used
* [Sbt](https://www.scala-sbt.org/documentation.html) - Dependency
  Management
* [Elasticsearch](https://www.elastic.co/guide/index.html) - Search engine/ database
* [Elastic4s](https://github.com/sksamuel/elastic4s) - Elasticsearch
  scala client.