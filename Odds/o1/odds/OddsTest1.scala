package o1.odds

// This program is developed in Chapters 2.7 and 3.4.
// It creates a single Odds object and uses some of its methods.

import scala.io.StdIn.*

object OddsTest1 extends App:

  println("Please enter the odds of an event as two integers on separate lines.")
  println("For instance, to enter the odds 5/1 (one in six chance of happening), write 5 and 1 on separate lines.")
  val firstInput = readInt()
  val secondInput = readInt()
  val oDds = Odds(firstInput, secondInput)

  println("The odds you entered are: " )
  println("In fractional format: " + firstInput + "/" + secondInput)
  println("In decimal format: " + 1.0*(firstInput+secondInput)/secondInput)
  println("In monyline format: " + oDds.moneyline)
  println("Event probability: " +1.0*secondInput/(firstInput+secondInput))
  println("Reverse odds: " + secondInput + "/" + firstInput)
  println("Odds of happening twice: "
    + ((firstInput+secondInput)*(firstInput+secondInput)-secondInput*secondInput) + "/" + secondInput*secondInput)
  println("Please enter the size of a bet:")
  val thirdInput = readDouble()
  println("If successful, the bettor would claim " + oDds.decimal*thirdInput)
  println("Please enter the odds of a second event as two integers on separate lines.")
    val fourthInput = readInt()
    val fifthInput = readInt()
    val odDs = Odds(fourthInput, fifthInput)
  println("The odds of both events happening are: "+ oDds.both(odDs))
  println("The odds of one or both happening are: "+ oDds.either(odDs))

  def requestOdds() =
    val readOdd1 = readInt()
    val readOdd2 = readInt()
    Odds(readOdd1, readOdd2)
end OddsTest1

//+ (firstInput*fourthInput)+(firstInput*fifthInput)+(secondInput*fourthInput)
   // + "/" + (firstInput*fourthInput)