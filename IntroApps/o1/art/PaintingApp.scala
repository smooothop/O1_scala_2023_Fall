package o1.art
import o1.*

// This program is introduced in Chapter 3.1.

val artwork = ArtProject(rectangle(600, 600, White))

object painterView extends View(artwork):

  def makePic = artwork.image

  // The event-handling code is missing.

end painterView


@main def launchPaintingApp() =
  painterView.start()

