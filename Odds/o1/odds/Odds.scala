package o1.odds

import o1.util.Random

// This class is developed gradually between Chapters 2.4 and 3.4.

class Odds(val wont: Int, val will: Int):

  def probability = 1.0 * this.will / (this.wont + this.will)
  // TODO: other methods missing
  def fractional = s"${this.wont}" + "/" + this.will
  def decimal = 1.0 * (this.wont + this.will) / this.will
  def winnings(bet: Double) = bet * decimal
  def not = Odds(this.will, this.wont)
  override def toString = this.wont + "/" + this.will
  def both(odds: Odds) =
    val bothOdds =
      new Odds( (this.wont * odds.wont + this.wont * odds.will
      + this.will * odds.wont), (this.will * odds.will))
    bothOdds
  def either(odds: Odds) =
    val eitherOdds =
      new Odds( (this.wont * odds.wont), (this.wont * odds.will
        + this.will * odds.wont + this.will * odds.will))
    eitherOdds
  def isLikely =
    this.wont < this.will
  def isLikelierThan(odds: Odds) =
    this.probability > odds.probability
  def moneyline =
    if this.probability < 0.5 then
      100 * this.wont/this.will
    else
      -100 * this.will/this.wont
  def eventHappens(): Boolean =
     Random.nextInt(wont + will) < will
end Odds
