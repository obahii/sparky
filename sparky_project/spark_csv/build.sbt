name := "ScalaSparkProject"

version := "0.1"

scalaVersion := "2.12.18"

logLevel := Level.Error

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.2",
  "org.apache.spark" %% "spark-sql" % "3.1.2",
  "mysql" % "mysql-connector-java" % "8.0.26" 
)