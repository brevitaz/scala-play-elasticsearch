name := """scala-play-elasticsearch"""
organization := "com.brevitaz"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-client-esjava" % "7.1.1"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
