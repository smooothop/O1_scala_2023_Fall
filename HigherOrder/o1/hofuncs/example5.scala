package o1.hofuncs
import scala.util.Random
import scala.collection.mutable.Buffer

// These example functions are introduced in Chapter 6.1.

def parity(index: Int) = index % 2 == 0
def randomElement(upperLimit: Int) = Random.nextInt(upperLimit + 1)

@main def example5() =

  println(Vector.tabulate(10)(doubled))  // doubled and next are defined in example1
  println(Buffer.tabulate(15)(next))

  println(Vector.tabulate(5)(parity))
  println(Vector.tabulate(5)(parity).mkString("\t"))

  val randomButAscending = Vector.tabulate(30)(randomElement)
  println(randomButAscending.mkString(","))

end example5

