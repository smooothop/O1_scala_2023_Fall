package o1.sentiment

/** Cleans the given line of text for use by a [[SentimentAnalyzer]]:
  * normalizes apostrophes and whitespace and discards unwanted punctuation. */
def cleanText(text: String) =
  val normalized = text.trim.toLowerCase.replaceAll("’", "'")
  val separateGenitive = normalized.replaceAll("'s", " 's")
  val relevantCharsOnly = separateGenitive.replaceAll("""[^\w'/ -]""", " ").replaceAll("""\s+""", " ")
  relevantCharsOnly

