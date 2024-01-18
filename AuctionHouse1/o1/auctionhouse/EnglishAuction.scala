package o1.auctionhouse

import scala.math.*

class EnglishAuction(val description: String, val startingPrice: Int, duration: Int):

  private var highest = Bid(None, startingPrice)
  private var secondHighest = Bid(None, startingPrice)
  private var remainingDays = duration

  def daysLeft = this.remainingDays

  def advanceOneDay() =
    if this.isOpen then
      this.remainingDays -= 1

  def isOpen = this.remainingDays > 0

  def hasNoBids = this.highest.isInitialBid

  def isExpired = !this.isOpen && this.hasNoBids

  def buyer = this.highest.bidder

  def price =
    if this.secondHighest.isInitialBid then
      this.startingPrice
    else
      min(this.secondHighest.limit + 1, this.highest.limit)

  def requiredBid = if this.hasNoBids then this.startingPrice else this.price + 1

  def bid(bidder: String, amount: Int) =
    val newBid = Bid(Some(bidder), amount)
    if this.isOpen && amount >= this.requiredBid then
      this.secondHighest = if newBid.beats(this.highest) then this.highest
                           else newBid.winner(this.secondHighest)
      this.highest = newBid.winner(this.highest)
    end if
    this.highest == newBid

  override def toString = this.description

end EnglishAuction