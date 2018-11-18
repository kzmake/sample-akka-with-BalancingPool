val akkaVersion = "2.5.18"
val sprayVersion = "1.3.4"

lazy val root = (project in file("."))
  .settings(
    organization := "com.github.kzmkae.sample.akka",
    name := "sample-akka-with-BalancingPool",
    version := "1.0",
    scalaVersion := "2.11.12",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "io.spray" %% "spray-can" % sprayVersion,
      "io.spray" %% "spray-routing" % sprayVersion
    )
  )
