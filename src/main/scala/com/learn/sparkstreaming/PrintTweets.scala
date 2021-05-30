package com.learn.sparkstreaming

import com.learn.sparkstreaming.Utilities._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._


object PrintTweets {

  def main(args: Array[String]) {

    // Configure Twitter credentials using Twitter.txt
    setupTwitter()

    // Set up a Spark streaming context named "PrintTweets" that runs locally using
    // all CPU cores and one-second batches of data
    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(1))

    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets = TwitterUtils.createStream(ssc, None)

    // Now extract the text of each status update into RDD's using map()
    val statuses = tweets.map(status => status.getText)

    val tweetWords = statuses.flatMap(tweetText => tweetText.split(" "))

    val hashtags = tweetWords.filter(word => word.startsWith("#"))


    // Print out the first ten
    hashtags.print()

    // Set a checkpoint directory
    ssc.checkpoint("/Users/rsuri/Documents/Personal/Learn/Streaming/checkpoint")
    // Kick it all off
    ssc.start()
    ssc.awaitTermination()

  }

}
