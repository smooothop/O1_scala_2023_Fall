// At this stage, you don’t have to worry about the definitions immediately below this text.
package o1.singletons

import scala.math.*   // Math functions will come in handy in this assignment.

// CHAPTER 2.2: Define the account object below.

object account:
  var balance = 0
  val number = "15903000000776FI00"

  //시험문제??
  // Write your code here. Remember to indent it appropriately.
  def deposit(money: Int) =
    balance = balance + max(money, 0)

  def withdraw(money: Int) =
    val withdrawnMoney = min(money, balance)
    balance = max(balance - money, 0)
    withdrawnMoney
end account

