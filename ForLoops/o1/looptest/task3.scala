package o1.looptest

// This program is associated with Chapter 5.6.


def gcContent(dna: String) =
  var gcCount = 0
  var totalCount = 0
  for base <- dna do
    if base == 'G' || base == 'C' then
      gcCount += 1
      totalCount += 1
    else if base == 'A' || base == 'T' then
      totalCount += 1
  end for
  100.0 * gcCount / totalCount
end gcContent


// The program below reads in some DNA data from a text file indicated
// by the user, passes that data to gcContent (defined above), and
// prints out the return value.
@main def forTask3() =

  import scala.io.StdIn.*
  import o1.util.readTextFile

  val speciesName = readLine("Enter a species file name (without .mtdna extension): ")
  val fileName = s"mtDNA_examples/$speciesName.mtdna"
  readTextFile(fileName) match
    case Some(dnaData) => println("The GC content is " + gcContent(dnaData.toUpperCase) + "%.")
    case None          => println("Failed to read the file.")

end forTask3

