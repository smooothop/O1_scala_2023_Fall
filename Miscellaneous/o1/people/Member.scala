package o1.people

class Member(val id: Int, val name: String, val yearOfBirth: Int, val yearOfDeath: Option[Int]):

  // TODO: missing methods
  def isAlive = {
    yearOfDeath match
    case None => true
    case Some(yearOfDeath) => false
  }

  override def toString: String =
    yearOfDeath match
    case None => name + "(" + yearOfBirth + "-" + ")"
    case Some(yearOfDeath) => name + "(" + yearOfBirth + "-" + yearOfDeath + ")"
end Member
