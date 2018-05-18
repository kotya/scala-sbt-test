/*import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github.kotya",
      // version      := "0.1.0-SNAPSHOT",
      scalaVersion := "2.11.11"
    )),
    name := "scala-sbt-test",
    libraryDependencies += scalaTest % Test
  )
*/

//         

scalaVersion := "2.11.11"
sbtVersion := "0.13.16"

lazy val `scala-sbt-test` = project in file(".")

organization := "com.github.kotya"
name := "scala-sbt-test"

homepage := Some(url("https://github.com/kotya/scala-sbt-test"))
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

crossScalaVersions := Vector("2.11.11", "2.12.6")
crossSbtVersions := Vector("0.13.16", "1.1.5")
sbtPlugin := true

publishMavenStyle := true
pomIncludeRepository := { _ => false }
publishTo := Some(Resolver.file("file",  new File( "D:/Data/.ivy2/releases" )) )

scalacOptions += "-deprecation"

val unusedWarnings = Seq("-Ywarn-unused-import")

scalacOptions ++= PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion.value)){
  case Some((2, v)) if v >= 11 => unusedWarnings
}.toList.flatten

Seq(Compile, Test).flatMap(c =>
  scalacOptions in (c, console) --= unusedWarnings
)

val tagName = Def.setting{
  s"v${if (releaseUseGlobalVersion.value) (version in ThisBuild).value else version.value}"
}
val tagOrHash = Def.setting{
  if(isSnapshot.value)
    sys.process.Process("git rev-parse HEAD").lines_!.head
  else
    tagName.value
}

releaseTagName := tagName.value

scalacOptions in (Compile, doc) ++= {
  val tag = tagOrHash.value
  Seq(
    "-sourcepath", (baseDirectory in LocalRootProject).value.getAbsolutePath,
    "-doc-source-url", s"https://github.com/kotya/scala-sbt-test/tree/${tag}€{FILE_PATH}.scala"
  )
}

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.9.1" % "test")
libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.5" % "test")
// libraryDependencies += "org.scala-sbt" % "scripted-plugin" % sbtVersion.value

// Scripted
/*scriptedSettings
scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-XX:MaxPermSize=256M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false*/

// Bintray
//bintrayOrganization := Some("kotyanaik")
//bintrayRepository := "sbt-plugin-releases"
//bintrayPackage := "scala-sbt-test"
//bintrayReleaseOnPublish := false

// Release
import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  //releaseStepCommandAndRemaining("^ test"),
  //releaseStepCommandAndRemaining("^ scripted"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
	publishArtifacts,
  //releaseStepCommandAndRemaining("^ publishSigned"),
  //releaseStepTask(bintrayRelease in `scala-sbt-test`),
  setNextVersion,
  commitNextVersion,
  pushChanges
)


