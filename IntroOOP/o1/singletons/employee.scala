// At this stage, you don’t have to worry about the definitions immediately below this text.
package o1.singletons

// This example is introduced in Chapter 2.2.

object employee:

  var name = "Edelweiss Fume"
  val yearOfBirth = 1965
  var monthlySalary = 5000.0
  var workingTime = 1.0

  def ageInYear(year: Int) = year - this.yearOfBirth

  def monthlyCost(multiplier: Double) = this.monthlySalary * this.workingTime * multiplier

  def raiseSalary(multiplier: Double) =
    this.monthlySalary = this.monthlySalary * multiplier

  def description =
    this.name + " (b. " + this.yearOfBirth + "), salary " + this.workingTime + " * " + this.monthlySalary + " e/month"

end employee

