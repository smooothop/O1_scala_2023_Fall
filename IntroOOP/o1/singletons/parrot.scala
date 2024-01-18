// At this stage, you don’t have to worry about the definitions immediately below this text.
package o1.singletons

// The following example is introduced in Chapter 2.1. It isn’t necessary to
// understand its internal workings. Some of the code that follows is not written
// in a beginner-friendly style.

object parrot:

  private var repertoire = List("Yo-Yo Ma and a bottle of rum", "Polly wants a cracker")

  def respond(heardPhrase: String) =

    def cleanse(phrase: String) = phrase.replaceAll(raw"[^\w åäöÅÄÖ]+", "")

    def toWords(phrase: String) = cleanse(phrase).toLowerCase.split(" ").filter(_.length > 2)

    val heardWords = toWords(heardPhrase)

    def isSimilar(word: String, another: String) = o1.util.editDistance(word, another, 1) <= 1

    def hasFamiliarSoundingWord(phrase: String) =
      toWords(phrase).exists(familiarWord => heardWords.exists(isSimilar(_, familiarWord)))

    val knownReply = this.repertoire.find(hasFamiliarSoundingWord)
    val response = knownReply.getOrElse(cleanse(heardPhrase).takeWhile(_ != ' '))
    if response.nonEmpty then response + "!" else ""

  end respond

  def learnPhrase(newPhrase: String) =
    this.repertoire = newPhrase :: this.repertoire

end parrot

