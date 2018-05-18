import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.kotya",
      // version      := "0.1.0-SNAPSHOT",
      scalaVersion := "2.11.11"
    )),
    name := "scala-sbt-test",
    libraryDependencies += scalaTest % Test
  )
