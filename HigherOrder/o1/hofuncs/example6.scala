package o1.hofuncs

// This example is introduced in Chapter 6.1.

def multiplicationTableEntry(row: Int, column: Int) = (row + 1) * (column + 1)

@main def example6() =
  val vectorOfRows = Vector.tabulate(10, 10)(multiplicationTableEntry)
  println(vectorOfRows)
  for numbersOnRow <- vectorOfRows do
    println(numbersOnRow.mkString("\t"))

