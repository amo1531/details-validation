name := "details-validation"

version := "0.1"

scalaVersion := "2.13.6"


libraryDependencies ++= Seq(
  "com.opencsv" % "opencsv" % "3.7",
  "org.specs2" %% "specs2-core" % "4.8.3" % Test,
  "org.specs2" %% "specs2-mock" % "4.8.0" % Test
)