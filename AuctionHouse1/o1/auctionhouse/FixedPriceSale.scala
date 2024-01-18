package o1.auctionhouse

class FixedPriceSale(val description: String, val price: Int, private val duration: Int) {
  private var buyerName: Option[String] = None
  private var remainingDays = duration
  def advanceOneDay(): Unit = {
    if isOpen then remainingDays -= 1
  }

  def buy(buyer: String): Boolean =
    if isOpen then
      buyerName = Some(buyer)
      true
    else
      false


  def buyer: Option[String] = buyerName


  def daysLeft = remainingDays

  def isExpired: Boolean = daysLeft <= 0 && buyerName.isEmpty

  def isOpen: Boolean = daysLeft > 0 && buyerName.isEmpty

  override def toString: String = description
}
