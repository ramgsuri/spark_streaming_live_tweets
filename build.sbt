name := "spark_streaming_live_tweets"
version := "0.1"
scalaVersion := "2.11.8"
val sparkVersion = "1.5.2"

organization := "com.learn.sparkstreaming"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %%  "spark-sql" % sparkVersion,
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "org.twitter4j" % "twitter4j-stream" % "4.0.4",
  "org.apache.spark" %% "spark-streaming" % sparkVersion, // This version is important otherwise you get log4j NoClassDefFound error.
  "org.apache.spark" %% "spark-streaming-twitter" % "1.6.2"
)

