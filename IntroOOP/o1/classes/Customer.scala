package o1.classes

// This class is introduced in Chapter 2.6.

class Customer(val name: String, val customerNumber: Int, val email: String, val address: String):
 override def toString = "#" + this.customerNumber + " " + this.name + " <" + this.email + ">"

