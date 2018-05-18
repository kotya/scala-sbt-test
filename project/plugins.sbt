addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.2")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.8")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.1")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.3")
addSbtPlugin("no.arktekk.sbt" % "aether-deploy" % "0.21")
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

// This project is its own plugin :)
unmanagedSourceDirectories in Compile += baseDirectory.value.getParentFile / "src" / "main" / "scala"
unmanagedSourceDirectories in Compile += baseDirectory.value.getParentFile / "src" / "main" / "scala-sbt-0.13"

libraryDependencies += "org.scala-sbt" % "scripted-plugin" % sbtVersion.value
