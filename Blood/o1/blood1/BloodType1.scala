package o1.blood1

class BloodType(val abo: String, val rhesus: Boolean):

  // 혈액형을 문자열로 표현합니다.
  override def toString = abo + (if rhesus then "+" else "-")

  // 수신자에게 안전한 ABO혈액형인지 확인합니다.
  def hasSafeABOFor(recipient: BloodType) =
    (abo, recipient.abo) match {
      case ("O", _) => true
      case ("A", "A" | "AB") => true
      case ("B", "B" | "AB") => true
      case ("AB", "AB") => true
      case _ => false
    }

  // 수신자에게 안전한 Rh형인지 확인합니다.
  def hasSafeRhesusFor(recipient: BloodType) =
    if rhesus then recipient.rhesus else true

  // 혈액을 기증할 수 있는지 확인합니다.
  def canDonateTo(recipient: BloodType) =
    hasSafeABOFor(recipient) && hasSafeRhesusFor(recipient)

  // 혈액을 수혈받을 수 있는지 확인합니다.
  def canReceiveFrom(donor: BloodType) =
    donor.canDonateTo(this)

end BloodType