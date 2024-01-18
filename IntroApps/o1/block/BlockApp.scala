package o1.block
import o1.*

// This program is introduced in Chapter 2.7.

val background = rectangle(500, 500, Black)

val block = Block(20, Pos(300, 50), Gray)

object viewOfBlock extends View(block, "An uninteractive test app"):

  def makePic =
    val blockPic = rectangle(block.size, block.size, block.color)
    background.place(blockPic, block.location)

end viewOfBlock


@main def launchBlockApp() =
  viewOfBlock.start()
