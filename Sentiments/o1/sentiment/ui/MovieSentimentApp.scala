package o1.sentiment.ui

import o1.sentiment.*
import scala.io.StdIn.*

/** This program loads training data for a [[SentimentAnalyzer]] from a text file
  * that contains movie reviews. It then interacts with the user in the text
  * console, interactively prompting them to enter movie reviews and using the
  * analyzer to report whether those reviews are positive or negative.
  * The session ends when the user enters an empty input.
  *
  * (The training data is originally from the Rotten Tomatoes review aggregator,
  * was collected by the Sentiment Analysis project at Stanford University. It
  * has been further pre-processed for educational purposes first by Eric D. Manley
  * and Timothy M. Urness, then by Juha Sorva.) */
@main def runSentimentApp() =

  val analyzer = SentimentAnalyzer.fromFile("sample_reviews_from_rotten_tomatoes.txt")

  // Asks the user to enter a movie review and returns the input as a string.
  def requestMovieReview(): String =
    cleanText(readLine("\nPlease comment on a movie or hit Enter to quit: "))

  // Given a movie review, returns a report of whether it seems positive or negative.
  def analyze(review: String): String =
    def category(average: Double) = if average >= 0 then "positive" else "negative"
    analyzer.rate(review) match
      case Some(average) => f"I think this sentiment is ${category(average)}. (Average word sentiment: $average%.2f.)"
      case None          => "No recognized words in the given text."

  // TODO: should interact with the user using the functions defined above.

  println("Bye.")

end runSentimentApp

