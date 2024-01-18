package o1.hofuncs
import o1.*

// This program is introduced in Chapter 6.1.

// Your task: use the combine function and a helper function to combine “original”
// and “silhouette”, which are defined below. The combination should contain a
// part of “original” in the shape of “silhouette”, set against a black background.
// More specifically:
//
//  * Where the silhouette has a black pixel, the
//    combination should retain the pixel of the original.
//  * Where the silhouette has anything other than a
//    black pixel, the combination should have a black pixel.
//
// Store the result in “combination” (below). You may name the helper function
// as you like.
@main def task6() =
  val original   = Pic("llama.png")
  val silhouette = rectangle(original.width, original.height, White).place(circle(140, Black), Pos(320, 165))
  val combination = original.combine(silhouette, cutToSilhouette)
  combination.show()

def cutToSilhouette(colorInOriginal: Color, colorInSilhouette: Color) =
  if colorInSilhouette == Black then colorInOriginal else Black

