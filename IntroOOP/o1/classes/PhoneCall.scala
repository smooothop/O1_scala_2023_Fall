package o1.classes

// This class is introduced in Chapter 2.3.

class PhoneCall(initialFeeExcludingNetwork: Double, pricePerMinuteExcludingNetwork: Double, val duration: Double):

  val NetworkFeeInitially = 0.130
  val NetworkFeePerMinute = 0.013
  val initialFee = initialFeeExcludingNetwork + NetworkFeeInitially
  val pricePerMinute = pricePerMinuteExcludingNetwork + NetworkFeePerMinute

  def totalPrice = this.initialFee + this.pricePerMinute * this.duration

  def description =
    "%.2f min, %.2fe (%.3fe + %.3fe/min)".format(this.duration,   this.totalPrice,
                                                 this.initialFee, this.pricePerMinute)

end PhoneCall
