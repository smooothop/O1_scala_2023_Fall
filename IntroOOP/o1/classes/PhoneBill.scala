package o1.classes

// This class is introduced in Chapter 2.3.

class PhoneBill(var customerName: String):

  private var callsMade = List[PhoneCall]()

  def addCall(newCall: PhoneCall) =
    this.callsMade = newCall :: callsMade

  def totalPrice = this.callsMade.map( _.totalPrice ).sum

  def breakdown =
    val callInfo = this.callsMade.map( "\n - " + _.description ).mkString("")
    s"INVOICE --- $customerName:$callInfo"

end PhoneBill

