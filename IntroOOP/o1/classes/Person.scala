package o1.classes

// This code is introduced in Chapter 2.4.

class Person(val name: String):
  def say(phrase: String) = this.name + ": " + phrase
  def reactToSriracha     = this.say("What a nice sauce.")
  def reactToKryptonite   = this.say("What a strange mineral.")


object Superman extends Person("Clark"):
  def fly = "WOOSH!"
  override def reactToKryptonite = "GARRRRGH!"

