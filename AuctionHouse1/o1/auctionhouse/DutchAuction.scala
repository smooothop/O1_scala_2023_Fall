package o1.auctionhouse

class DutchAuction(val description: String, startingPrice: Int, decrement: Int, minimumPrice: Int) {
  var currentPrice: Int = startingPrice
  private var _buyer: Option[String] = None
  private var daysAfterMinPrice: Int = 0

  def advanceOneDay(): Unit = {
    if (isOpen) then {
      if (currentPrice > minimumPrice) then {
        currentPrice = (currentPrice - decrement) max minimumPrice
      } else {
        daysAfterMinPrice += 1
      }
    }
  }

  def buy(buyer: String): Boolean = {
    if (isOpen) then {
      this._buyer = Some(buyer)
      true
    } else {
      false
    }
  }

  def buyer: Option[String] = this._buyer

  def isExpired: Boolean = daysAfterMinPrice > 3

  def isOpen: Boolean = _buyer.isEmpty && !isExpired

  def price: Int = currentPrice

  def priceRatio: Double = currentPrice.toDouble / startingPrice

  override def toString: String = description
}
