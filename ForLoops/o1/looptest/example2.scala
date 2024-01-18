package o1.looptest

// This example is discussed in Chapter 5.6.

@main def forExample2() =

  var index = 0
  for character <- "llama" do
    println("Index " + index + " stores " + character + ", which is character #" + character.toInt + " in Unicode.")
    index += 1

end forExample2