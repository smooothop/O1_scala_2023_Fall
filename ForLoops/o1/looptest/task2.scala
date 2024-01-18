package o1.looptest

// This program is associated with Chapter 5.6.

@main def forTask2() =

  val data = "Urho Kaleva Kekkonen, " +
             "North Atlantic Treaty Organization, " +
             "Write Everything Twice" * 2

  // Flesh out the loop below. It should extract all characters *except* lower-case
  // letters and space characters from the data string, gathering them in the
  // result variable.

  // Note: Char objects have a method named isLower (see example below). It returns
  // a Boolean value that indicates if the character is a lower-case letter.

  var result = ""  // gatherer: collects the output string letter by letter
  for character <- data do
    if character != ' ' && !character.isLower then
      result += character
  println(result)  // this should print out: UKK,NATO,WETWET

end forTask2

