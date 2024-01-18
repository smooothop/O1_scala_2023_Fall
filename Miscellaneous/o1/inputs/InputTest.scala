package o1.inputs

import io.StdIn.*

@main def inputTest() =

  val input = readLine("Please enter an integer: ")
  val digits = input.length

  input.toIntOption match {
    case Some(number) =>
      println(s"Your number is $digits digits long.")
      val multiplied = number * digits
      println(s"Multiplying it by its length gives $multiplied.")
    case None =>
      println("That is not a valid input. Sorry!")
  }

end inputTest