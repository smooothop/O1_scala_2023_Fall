class Person(val name: String) {
 def say(sentence: String) = this.name + ": " + sentence
 def reactToSriracha = this.say("Nice.")
 def reactToKryptonite = this.say("Odd.")
 }