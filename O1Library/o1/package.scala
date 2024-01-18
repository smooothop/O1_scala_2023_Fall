/** **O1Library** is a toolkit designed for the course [Programming 1 (a.k.a. O1)](https://plus.cs.aalto.fi/o1/)
  * at Aalto University. It contains an assortment of tools; most prominently, it provides a
  * framework for simple graphical programming and utilities for playing sound.
  *
  * This is the front page of O1Library’s documentation. However, this is probably not the best
  * place to start learning about O1Library as a student. That’s because the relevant content
  * of this library is introduced bit by bit in the chapters of O1’s custom ebook alongside the
  * associated programming concepts and assignments.
  *
  * You may nevertheless find this documentation useful as a reference. You can also find some
  * optional content here that you may wish to try.
  *
  * This file front page lists the content available in the top-level package called simply `o1`.
  * These tools are available with the simple command `import o1.*` in your Scala programs.
  * Some of them you’ll use a lot; some of them you won’t necessarily need at all.
  *
  * The tools listed here are actually implemented in a number of subpackages (`o1.gui`,
  * `o1.sound`, etc.); what you see here are just “shortcut aliases” to those actual
  * implementations. The aliases enable that convenient `import` command. The subpackeges
  * also contain some additional tools beyond what is available via `import o1.*`.
  *
  * O1Library’s student-facing interface was designed by Juha Sorva; Aleksi Lukkarinen and
  * Juha Sorva did the underlying implementation. Several of the key components in `o1.gui` and
  * `o1.world` are built upon Aleksi’s [Scala Media Computation Library](https://github.com/Aalto-LeTech/Scala-Media-Computation).
  * Some parts of O1Library draw inspiration from the “teachpacks” of the Racket programming language.
  *
  * We are grateful to Riku Autio, Joonatan Honkamaa, Juhani Numminen, Leo Varis, Veera Kahva,
  * Oskari Lahti, and anonymous students for bug reports and fixes. We thank Otto Seppälä for 
  * helpful discussions.
  *
  * @author Juha Sorva (juha.sorva@aalto.fi)
  * @author Aleksi Lukkarinen (aleksi.lukkarinen@aalto.fi) */
package o1

import o1.gui.event as GE
import o1.sound.{midi as SM, sampled as SS}
import o1.{grid as R, gui as G, world as W}

private val _ = o1.util.smclInit()


//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            SHORTCUTS TO THE STANDARD SCALA API
//////////
//////////////////////////////////////////////////////////////////////////////////////////////

/** This is a shortcut alias to Scala’s standard `Buffer` type, which we use a lot early in O1. */
type Buffer[Element] = scala.collection.mutable.Buffer[Element]
/** This is a shortcut alias to Scala’s standard `Buffer` type, which we use a lot early in O1. */
val Buffer: scala.collection.mutable.Buffer.type = scala.collection.mutable.Buffer




//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            O1.SOUND
//////////
//////////////////////////////////////////////////////////////////////////////////////////////


/** Plays the music written in the given `String` (as described under [[o1.sound.midi]]) on
 * a MIDI synthesizer; this is a shortcut alias to `def play(music: String)` in [[o1.sound.midi]]. */
def play(song: String): Unit =
  SM.play(song)

/** The `Sound` type represents recorded sound samples; this is a shortcut alias to `o1.sound.sampled`. */
type Sound = SS.Sound
/** The `Sound` type represents recorded sound samples; this is a shortcut alias to `o1.sound.sampled. */
val Sound: SS.Sound.type = SS.Sound

/** a constant that you can use to make the sound repeat indefinitely; this is a shortcut alias
  * to `o1.sound.sampled`. */
val KeepRepeating: Int = SS.KeepRepeating
/** a constant that you can use to mute a sound; this is a shortcut alias to `o1.sound.sampled`. */
val Mute: Float = SS.Mute





//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            O1.GUI
//////////
//////////////////////////////////////////////////////////////////////////////////////////////


/** The `Color` type represents colors; this is a shortcut alias to `o1.gui`. */
type Color = G.Color
/** The `Color` type represents colors; this is a shortcut alias to `o1.gui`. */
val Color: G.Color.type = G.Color


/** The `Pic` type represents images; this is a shortcut alias to `o1.gui`. */
type Pic = G.Pic
/** The `Pic` type represents images; this is a shortcut alias to `o1.gui`. */
val Pic: G.Pic.type = G.Pic


/** Displays the given picture in a minimalistic window; this is a shortcut
  * alias to [[o1.gui.Pic.show `o1.gui`]]. */
def show(pic: Pic): Unit =
  pic.show()


/** The `Animation` type represents sequences of pictures shown in a GUI window;
  * this is a shortcut alias to `o1.gui`. */
type Animation = G.Animation
/** The `Animation` type represents sequences of pictures shown in a GUI window;
  * this is a shortcut alias to `o1.gui`. */
val Animation: G.Animation.type = G.Animation


/** Displays the given pictures in sequence in a minimalistic window;
  * this is a shortcut alias to [[o1.gui.Animation.show `o1.gui`]]. */
def animate(pics: Iterable[Pic], picsPerSecond: Double): Unit =
  Animation.show(frames = pics, frameRate = picsPerSecond)

/** Generates pictures with the given function and displays them in sequence in a minimalistic window;
  * this is a shortcut alias to [[o1.gui.Animation.generate `o1.gui`]]. */
def animateWithFunction(picGeneratingFunction: Int => Pic, numberOfPics: Int, picsPerSecond: Double): Unit =
  Animation.generate(picGeneratingFunction, numberOfPics, picsPerSecond)


/** The `View` type represents GUI windows that graphically represent a given domain model;
  * this is a shortcut alias to class `ViewFrame` in `o1.gui.mutable`. */
type View[Model <: AnyRef] = G.mutable.View[Model]


/** The `Key` type represents keys on a computer keyboard;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type Key = G.Key
/** The `Key` type represents keys on a computer keyboard;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val Key: G.Key.type = G.Key



/** Creates a picture of a filled rectangle with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The picture’s [[Anchor]] is at its center.
  * @param bounds  the width and height of the rectangle and the resulting picture
  * @param color   the color of the rectangle and thus the only color visible in the picture */
def rectangle(bounds: Bounds, color: Color): G.Pic =
  G.Pic.rectangle(bounds, color)

/** Creates a picture of a filled rectangle with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The picture’s [[Anchor]] is at its center.
  * @param width   the width of the rectangle and the picture
  * @param height  the height of the rectangle and the picture
  * @param color   the color of the rectangle and thus the only color visible in the picture */
def rectangle(width: Double, height: Double, color: Color): G.Pic =
  G.Pic.rectangle(width, height, color)

/** Creates a picture of a filled rectangle with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]].
  * @param width   the width of the rectangle and the picture
  * @param height  the height of the rectangle and the picture
  * @param color   the color of the rectangle and thus the only color visible in the picture
  * @param anchor  the picture’s anchoring point */
def rectangle(width: Double, height: Double, color: Color, anchor: Anchor): G.Pic =
  G.Pic.rectangle(width, height, color, anchor)


/** Creates a picture of a filled rectangle with the given parameters, anchored at the
  * top left-hand corner; this is a shortcut alias to [[o1.gui.Pic$ `o1.gui.Pic`]].
  * @param width   the width of the rectangle and the picture
  * @param height  the height of the rectangle and the picture
  * @param color   the color of the rectangle and thus the only color visible in the picture;
  *                if unspecified, defaults to `White` */
def emptyCanvas(width: Double, height: Double, color: Color = G.colors.White): G.Pic =
  G.Pic.emptyCanvas(width, height, color)


/** Creates a picture of a filled square with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The picture’s [[Anchor]] is at its center.
  * @param side    the width and height of the square and the picture
  * @param color   the color of the square and thus the only color visible in the picture */
def square(side: Double, color: Color): G.Pic =
  G.Pic.square(side, color)

/** Creates a picture of a filled square with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]].
  * @param side    the width and height of the square and the picture
  * @param color   the color of the square and thus the only color visible in the picture
  * @param anchor  the picture’s anchoring point */
def square(side: Double, color: Color, anchor: Anchor): G.Pic =
  G.Pic.square(side, color, anchor)


/** Creates a picture of a filled circle with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The picture’s [[Anchor]] is at its center.
  * @param diameter  the diameter of the circle, which also sets the width and height of the picture
  * @param color     the circle’s color of the circle */
def circle(diameter: Double, color: Color): G.Pic =
  G.Pic.circle(diameter, color)

/** Creates a picture of a filled circle with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]].
  * @param diameter  the diameter of the circle, which also sets the width and height of the picture
  * @param color     the circle’s color of
  * @param anchor    an anchor for the new picture */
def circle(diameter: Double, color: Color, anchor: Anchor): G.Pic =
  G.Pic.circle(diameter, color, anchor)


/** Creates a picture of an ellipse with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The picture’s [[Anchor]] is at its center.
  * @param width     the width of the ellipse and the picture
  * @param height    the height of the ellipse and the picture
  * @param color     the ellipse’s color */
def ellipse(width: Double, height: Double, color: Color): G.Pic =
  G.Pic.ellipse(width, height, color)

/** Creates a picture of an ellipse with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]].
  * @param width     the width of the ellipse and the picture
  * @param height    the height of the ellipse and the picture
  * @param color     the ellipse’s color
  * @param anchor    an anchor for the new picture */
def ellipse(width: Double, height: Double, color: Color, anchor: Anchor): G.Pic =
  G.Pic.ellipse(width, height, color, anchor)


/** Creates a picture of an isosceles triangle with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The triangle’s base is at the bottom of the picture
  * and its apex is at the top center. The picture’s [[Anchor]] is at its center.
  * @param width     the width of the triangle’s base, which determines the width of the picture, too
  * @param height    the height of the triangle, which determines the height of the picture, too
  * @param color     the triangle’s color */
def triangle(width: Double, height: Double, color: Color): G.Pic =
  G.Pic.triangle(width, height, color)

/** Creates a picture of an isosceles triangle with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The triangle’s base is at the bottom of the picture
  * and its apex is at the top center.
  * @param width     the width of the triangle’s base, which determines the width of the picture, too
  * @param height    the height of the triangle, which determines the height of the picture, too
  * @param color     the triangle’s color
  * @param anchor    an anchor for the new picture */
def triangle(width: Double, height: Double, color: Color, anchor: Anchor): G.Pic =
  G.Pic.triangle(width, height, color, anchor)


/** Creates a picture of a five-pointed star with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The picture’s [[Anchor]] is at its center.
  * @param width   the width of the star, which determines the picture’s dimensions
  * @param color   the star’s color */
def star(width: Double, color: Color): G.Pic =
  G.Pic.star(width, color)

/** Creates a picture of a five-pointed star with the given parameters; this is a shortcut
  * alias to [[o1.gui.Pic$ `o1.gui.Pic`]].
  * @param width   the width of the star, which determines the picture’s dimensions
  * @param color   the star’s color
  * @param anchor  an anchor for the new picture */
def star(width: Double, color: Color, anchor: Anchor): G.Pic =
  G.Pic.star(width, color, anchor)


/** Creates a picture of a thin line defined in terms of two locations on a plane;
  * this is a shortcut alias to [[o1.gui.Pic$ `o1.gui.Pic`]]. The line is specified
  * in terms of two [[o1.world.Pos Pos]] objects: imagine drawing a line between the
  * two points on a plane and then cropping the plane to just the part that contains
  * the line. The line runs from one corner of the resulting picture to another,
  * against a transparent background.
  * @param from      the line’s “starting point”; the picture will anchor at the corresponding corner
  * @param to        the line’s “end point”
  * @param color     the color of the line */
def line(from: Pos, to: Pos, color: Color): G.Pic =
  G.Pic.line(from, to, color)



//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            O1.GUI.EVENT
//////////
//////////////////////////////////////////////////////////////////////////////////////////////

/** The `MouseMoved` type represents mouse-movement events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MouseMoved = GE.MouseMoved
/** The `MouseMoved` type represents mouse-movement events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MouseMoved: GE.MouseMoved.type = GE.MouseMoved

/** The `MouseDragged` type represents mouse-dragging events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MouseDragged = GE.MouseDragged
/** The `MouseDragged` type represents mouse-dragging events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MouseDragged: GE.MouseDragged.type = GE.MouseDragged

/** The `MouseExited` type represents cursor-exiting events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MouseExited = GE.MouseExited
/** The `MouseExited` type represents cursor-exiting events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MouseExited: GE.MouseExited.type = GE.MouseExited

/** The `MouseEntered` type represents cursor-entering events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MouseEntered = GE.MouseEntered
/** The `MouseEntered` type represents cursor-entering events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MouseEntered: GE.MouseEntered.type = GE.MouseEntered

/** The `MouseWheelMoved` type represents mouse-wheel events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MouseWheelMoved = GE.MouseWheelMoved
/** The `MouseWheelMoved` type represents mouse-wheel events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MouseWheelMoved: GE.MouseWheelMoved.type = GE.MouseWheelMoved

/** The `MouseReleased` type represents button-release events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MouseReleased = GE.MouseReleased
/** The `MouseReleased` type represents button-release events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MouseReleased: GE.MouseReleased.type = GE.MouseReleased

/** The `MousePressed` type represents button-press events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MousePressed = GE.MousePressed
/** The `MousePressed` type represents button-press events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MousePressed: GE.MousePressed.type = GE.MousePressed

/** The `MouseClicked` type represents mouse-click events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type MouseClicked = GE.MouseClicked
/** The `MouseClicked` type represents mouse-click events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val MouseClicked: GE.MouseClicked.type = GE.MouseClicked

/** The `KeyPressed` type represents key-press events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type KeyPressed = GE.KeyPressed
/** The `KeyPressed` type represents key-press events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val KeyPressed: GE.KeyPressed.type = GE.KeyPressed

/** The `KeyReleased` type represents key-release events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type KeyReleased = GE.KeyReleased
/** The `KeyReleased` type represents key-release events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val KeyReleased: GE.KeyReleased.type = GE.KeyReleased

/** The `KeyTyped` type represents keyboard-typing events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type KeyTyped = GE.KeyTyped
/** The `KeyTyped` type represents keyboard-typing events in a GUI;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
val KeyTyped: GE.KeyTyped.type = GE.KeyTyped

/** The `InputEvent` type represents GUI events that generate user input;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type InputEvent = GE.InputEvent

/** The `KeyEvent` type represents keyboard-related GUI events;
  * this is a shortcut alias to `scala.swing.event` via `o1.gui`. */
type KeyEvent = GE.KeyEvent


// NOTE: DOC DOES NOT GET GENERATED (2/2022)
/** This implicit conversion adds some methods to Swing’s `InputEvent` class;
  * the methods are described under [[o1.gui.event.ConvenientInputEvent]]. */
export o1.gui.event.ops.*




//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            O1.WORLD  AND  O1.WORLD.OBJECTS
//////////
//////////////////////////////////////////////////////////////////////////////////////////////


/** The `Pos` type represents coordinates on a two-dimensional surface;
  * this is a shortcut alias to `o1.world`. */
type Pos = W.Pos
/** The `Pos` type represents coordinates on a two-dimensional surface;
  * this is a shortcut alias to `o1.world`. */
val Pos = W.Pos


/** The `Bounds` type represents the boundaries of rectangular areas in two-dimensional space;
  * this is a shortcut alias to `o1.world`. */
type Bounds = W.Bounds
/** The `Bounds` type represents the boundaries of rectangular areas in two-dimensional space;
  * this is a shortcut alias to `o1.world`. */
val Bounds = W.Bounds


/** The `Velocity` type represents directed movement in two-dimensional space;
  * this is a shortcut alias to `o1.world`. */
type Velocity = W.Velocity
/** The `Velocity` type represents directed movement in two-dimensional space;
  * this is a shortcut alias to `o1.world`. */
val Velocity: W.Velocity.type = W.Velocity


/** The `Velocity` type represents directions in two-dimensional space;
  * this is a shortcut alias to `o1.world`. */
type Direction = W.Direction
/** The `Velocity` type represents directions in two-dimensional space;
  * this is a shortcut alias to `o1.world`. */
val Direction: W.Direction.type = W.Direction


/** The `Anchor` type  represents points of attachment in two-dimensional elements;
  * this is a shortcut alias to `o1.world.objects`. */
type Anchor = W.objects.Anchor
/** The `Anchor` type  represents points of attachment in two-dimensional elements;
  * this is a shortcut alias to `o1.world.objects`. */
val Anchor: W.objects.Anchor.type = W.objects.Anchor


/** An anchor at the top left-hand corner;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val TopLeft: G.Anchor = G.Anchor.TopLeft
/** An anchor at the middle of the top edge;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val TopCenter: G.Anchor = G.Anchor.TopCenter
/** An anchor at the top right-hand corner;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val TopRight: G.Anchor = G.Anchor.TopRight
/** An anchor at the middle of the left edge;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val CenterLeft: G.Anchor = G.Anchor.CenterLeft
/** An anchor at the middle;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val Center: G.Anchor = G.Anchor.Center
/** An anchor at the middle of the right edge;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val CenterRight: G.Anchor = G.Anchor.CenterRight
/** An anchor at the bottom left-hand corner;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val BottomLeft: G.Anchor = G.Anchor.BottomLeft
/** An anchor at the middle of the bottom edge;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val BottomCenter: G.Anchor = G.Anchor.BottomCenter
/** An anchor at the bottom right-hand corner;
  * this is a shortcut alias to [[o1.world.objects.Anchor$ `o1.world.objects.Anchor`]]. */
val BottomRight: G.Anchor = G.Anchor.BottomRight


/** A supertype for objects that have a position and a velocity in two-dimensional space;
  * this is a shortcut alias to `o1.world.objects`. */
type HasVelocity = W.objects.HasVelocity
/** A supertype for objects that have a location in two-dimensional space;
  * this is a shortcut alias to `o1.world.objects`. */
type HasPos = W.objects.HasPos
/** A supertype for objects that have a width and a height;
  * this is a shortcut alias to `o1.world.objects`. */
type HasSize = W.objects.HasSize
/** A supertype for objects that have an anchoring point;
  * this is a shortcut alias to `o1.world.objects`. */
type HasAnchor = W.objects.HasAnchor
/** A supertype for objects that take up a rectangular area and have an anchoring point;
  * this is a shortcut alias to `o1.world.objects`. */
type HasEdges = W.objects.HasEdges
/** A supertype for objects that have a mutable position and a velocity in two-dimensional space;
  * this is a shortcut alias to `o1.world.objects.mutable`. */
type MovingObject = W.objects.mutable.MovingObject
/** A supertype for objects that take up a rectangular area and have a velocity as
  * well as a mutable position that may be constrained by a larger object around them;
  * this is a shortcut alias to `o1.world.objects.mutable`. */
type MovingObjectInContainer = W.objects.mutable.MovingObjectInContainer




//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            O1.UTIL
//////////
//////////////////////////////////////////////////////////////////////////////////////////////

/** A label that can be used as an empty method body or other “no-op” block. */
val DoNothing: Unit = o1.util.DoNothing




//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            O1.GRID
//////////
//////////////////////////////////////////////////////////////////////////////////////////////

/** The `Grid` type represents rectangular grids; this is a shortcut alias to `o1.grid`. */
type Grid[Elem] = R.Grid[Elem]

/** The `GridPos` type represents coordinates in a grid-like structure;
  * this is a shortcut alias to `o1.grid`. */
type GridPos = R.GridPos
/** The `GridPos` type represents coordinates in a grid-like structure;
  * this is a shortcut alias to `o1.grid`. */
val GridPos: R.GridPos.type = R.GridPos


/** The `CompassDir` type represents the four main directions in a grid-like structure;
  * this is a shortcut alias to `o1.grid`. */
type CompassDir = R.CompassDir
/** The `CompassDir` type represents the four main directions in a grid-like structure;
  * this is a shortcut alias to `o1.grid`. */
val CompassDir: R.CompassDir.type = R.CompassDir


/** The northwardly compass direction in a grid; this is a shortcut alias
  * to [[o1.grid.CompassDir$ `o1.grid.CompassDir`]]. */
val North: CompassDir = R.CompassDir.North
/** The northwardly compass direction in a grid; this is a shortcut alias
  * to [[o1.grid.CompassDir$ `o1.grid.CompassDir`]]. */
val East: CompassDir = R.CompassDir.East
/** The northwardly compass direction in a grid; this is a shortcut alias
  * to [[o1.grid.CompassDir$ `o1.grid.CompassDir`]]. */
val South: CompassDir = R.CompassDir.South
/** The westwardly compass direction in a grid; this is a shortcut alias
  * to [[o1.grid.CompassDir$ `o1.grid.CompassDir`]]. */
val West: CompassDir = R.CompassDir.West







//////////////////////////////////////////////////////////////////////////////////////////////
//////////
//////////            O1.GUI.COLORS
//////////
//////////////////////////////////////////////////////////////////////////////////////////////

/** Represents a fully transparent (white) color. This is a shortcut alias to [[o1.gui.colors]]. */
val Transparent: G.Color = G.colors.Transparent


/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val AliceBlue: G.Color = G.colors.AliceBlue
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val AlizarinCrimson: Color = G.colors.AlizarinCrimson
/** A named color as per, um, some standard or other, maybe. This is a shortcut alias to [[o1.gui.colors]]. */
val Amethyst: G.Color = G.colors.Amethyst
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val AntiqueWhite: G.Color = G.colors.AntiqueWhite
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Aqua: G.Color = G.colors.Aqua
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Aquamarine: G.Color = G.colors.Aquamarine
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Azure: G.Color = G.colors.Azure
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Beige: G.Color = G.colors.Beige
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Bisque: G.Color = G.colors.Bisque
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Black: G.Color = G.colors.Black
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val BlanchedAlmond: G.Color = G.colors.BlanchedAlmond
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Blue: G.Color = G.colors.Blue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val BlueViolet: G.Color = G.colors.BlueViolet
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val BrightRed: Color = G.colors.BrightRed
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Brown: G.Color = G.colors.Brown
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val BurlyWood: G.Color = G.colors.BurlyWood
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val CadetBlue: G.Color = G.colors.CadetBlue
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val CadmiumYellow: Color = G.colors.CadmiumYellow
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Chartreuse: G.Color = G.colors.Chartreuse
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Chocolate: G.Color = G.colors.Chocolate
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Coral: G.Color = G.colors.Coral
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val CornflowerBlue: G.Color = G.colors.CornflowerBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val CornSilk: G.Color = G.colors.CornSilk
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Crimson: G.Color = G.colors.Crimson
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Cyan: G.Color = G.colors.Cyan
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkBlue: G.Color = G.colors.DarkBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkCyan: G.Color = G.colors.DarkCyan
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkGoldenrod: G.Color = G.colors.DarkGoldenrod
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkGray: G.Color = G.colors.DarkGray
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkGreen: G.Color = G.colors.DarkGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkGrey: G.Color = G.colors.DarkGrey
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkKhaki: G.Color = G.colors.DarkKhaki
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkMagenta: G.Color = G.colors.DarkMagenta
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkOliveGreen: G.Color = G.colors.DarkOliveGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkOrange: G.Color = G.colors.DarkOrange
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkOrchid: G.Color = G.colors.DarkOrchid
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkRed: G.Color = G.colors.DarkRed
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkSalmon: G.Color = G.colors.DarkSalmon
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkSeaGreen: G.Color = G.colors.DarkSeaGreen
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkSienna: Color = G.colors.DarkSienna
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkSlateBlue: G.Color = G.colors.DarkSlateBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkSlateGray: G.Color = G.colors.DarkSlateGray
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkSlateGrey: G.Color = G.colors.DarkSlateGrey
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkTurquoise: G.Color = G.colors.DarkTurquoise
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DarkViolet: G.Color = G.colors.DarkViolet
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DeepPink: G.Color = G.colors.DeepPink
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DeepSkyBlue: G.Color = G.colors.DeepSkyBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DimGray: G.Color = G.colors.DimGray
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DimGrey: G.Color = G.colors.DimGrey
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val DodgerBlue: G.Color = G.colors.DodgerBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val FireBrick: G.Color = G.colors.FireBrick
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val FloralWhite: G.Color = G.colors.FloralWhite
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val ForestGreen: G.Color = G.colors.ForestGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Fuchsia: G.Color = G.colors.Fuchsia
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Gainsboro: G.Color = G.colors.Gainsboro
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val GhostWhite: G.Color = G.colors.GhostWhite
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Gold: G.Color = G.colors.Gold
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Goldenrod: G.Color = G.colors.Goldenrod
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Gray: G.Color = G.colors.Gray
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Green: G.Color = G.colors.Green
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val GreenYellow: G.Color = G.colors.GreenYellow
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Grey: G.Color = G.colors.Grey
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Honeydew: G.Color = G.colors.Honeydew
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val HotPink: G.Color = G.colors.HotPink
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val IndianRed: G.Color = G.colors.IndianRed
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val IndianYellow: Color = G.colors.IndianYellow
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Indigo: G.Color = G.colors.Indigo
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Ivory: G.Color = G.colors.Ivory
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Khaki: G.Color = G.colors.Khaki
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Lavender: G.Color = G.colors.Lavender
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LavenderBlush: G.Color = G.colors.LavenderBlush
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LawnGreen: G.Color = G.colors.LawnGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LemonChiffon: G.Color = G.colors.LemonChiffon
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightBlue: G.Color = G.colors.LightBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightCoral: G.Color = G.colors.LightCoral
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightCyan: G.Color = G.colors.LightCyan
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightGoldenrodYellow: G.Color = G.colors.LightGoldenrodYellow
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightGray: G.Color = G.colors.LightGray
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightGreen: G.Color = G.colors.LightGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightGrey: G.Color = G.colors.LightGrey
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightPink: G.Color = G.colors.LightPink
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightSalmon: G.Color = G.colors.LightSalmon
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightSeaGreen: G.Color = G.colors.LightSeaGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightSkyBlue: G.Color = G.colors.LightSkyBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightSlateGray: G.Color = G.colors.LightSlateGray
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightSlateGrey: G.Color = G.colors.LightSlateGrey
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightSteelBlue: G.Color = G.colors.LightSteelBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LightYellow: G.Color = G.colors.LightYellow
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Lime: G.Color = G.colors.Lime
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val LimeGreen: G.Color = G.colors.LimeGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Linen: G.Color = G.colors.Linen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Magenta: G.Color = G.colors.Magenta
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Maroon: G.Color = G.colors.Maroon
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumAquamarine: G.Color = G.colors.MediumAquamarine
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumBlue: G.Color = G.colors.MediumBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumOrchid: G.Color = G.colors.MediumOrchid
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumPurple: G.Color = G.colors.MediumPurple
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumSeaGreen: G.Color = G.colors.MediumSeaGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumSlateBlue: G.Color = G.colors.MediumSlateBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumSpringGreen: G.Color = G.colors.MediumSpringGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumTurquoise: G.Color = G.colors.MediumTurquoise
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MediumVioletRed: G.Color = G.colors.MediumVioletRed
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MidnightBlack: Color = G.colors.MidnightBlack
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MidnightBlue: G.Color = G.colors.MidnightBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MintCream: G.Color = G.colors.MintCream
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val MistyRose: G.Color = G.colors.MistyRose
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Moccasin: G.Color = G.colors.Moccasin
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val NavajoWhite: G.Color = G.colors.NavajoWhite
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Navy: G.Color = G.colors.Navy
/** A named color as per [[http://montypython.50webs.com/scripts/Series_1/53.htm  the  MP  standard]]. This is a shortcut alias to [[o1.gui.colors]]. */
lazy val NorwegianBlue: G.Color = G.colors.NorwegianBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val OldLace: G.Color = G.colors.OldLace
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Olive: G.Color = G.colors.Olive
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val OliveDrab: G.Color = G.colors.OliveDrab
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Orange: G.Color = G.colors.Orange
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val OrangeRed: G.Color = G.colors.OrangeRed
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Orchid: G.Color = G.colors.Orchid
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PaleGoldenrod: G.Color = G.colors.PaleGoldenrod
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PaleGreen: G.Color = G.colors.PaleGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PaleTurquoise: G.Color = G.colors.PaleTurquoise
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PaleVioletRed: G.Color = G.colors.PaleVioletRed
/** A named color [[https://en.wikipedia.org/wiki/Pantone_448_C   famous  for  being  really  ugly]]. */
val Pantone448C: G.Color = G.colors.Pantone448C
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PapayaWhip: G.Color = G.colors.PapayaWhip
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PeachPuff: G.Color = G.colors.PeachPuff
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Peru: G.Color = G.colors.Peru
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PhthaloBlue: Color = G.colors.PhthaloBlue
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PhthaloGreen: Color = G.colors.PhthaloGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Pink: G.Color = G.colors.Pink
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Plum: G.Color = G.colors.Plum
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PowderBlue: G.Color = G.colors.PowderBlue
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val PrussianBlue: Color = G.colors.PrussianBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Purple: G.Color = G.colors.Purple
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val RebeccaPurple: G.Color = G.colors.RebeccaPurple
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Red: G.Color = G.colors.Red
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val RosyBrown: G.Color = G.colors.RosyBrown
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val RoyalBlue: G.Color = G.colors.RoyalBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SaddleBrown: G.Color = G.colors.SaddleBrown
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Salmon: G.Color = G.colors.Salmon
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SandyBrown: G.Color = G.colors.SandyBrown
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SapGreen: Color = G.colors.SapGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SeaGreen: G.Color = G.colors.SeaGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SeaShell: G.Color = G.colors.SeaShell
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Sienna: G.Color = G.colors.Sienna
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Silver: G.Color = G.colors.Silver
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SkyBlue: G.Color = G.colors.SkyBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SlateBlue: G.Color = G.colors.SlateBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SlateGray: G.Color = G.colors.SlateGray
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SlateGrey: G.Color = G.colors.SlateGrey
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Snow: G.Color = G.colors.Snow
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SpringGreen: G.Color = G.colors.SpringGreen
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val SteelBlue: G.Color = G.colors.SteelBlue
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Tan: G.Color = G.colors.Tan
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Teal: G.Color = G.colors.Teal
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Thistle: G.Color = G.colors.Thistle
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val TitaniumHwite: Color = G.colors.TitaniumHwite
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Tomato: G.Color = G.colors.Tomato
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Turquoise: G.Color = G.colors.Turquoise
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val VanDykeBrown: Color = G.colors.VanDykeBrown
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Violet: G.Color = G.colors.Violet
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Wheat: G.Color = G.colors.Wheat
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val White: G.Color = G.colors.White
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val WhiteSmoke: G.Color = G.colors.WhiteSmoke
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val Yellow: G.Color = G.colors.Yellow
/** A named color as per [[https://www.w3.org/TR/css-color-4/ W3C’s list of colors]]. This is a shortcut alias to [[o1.gui.colors]]. */
val YellowGreen: G.Color = G.colors.YellowGreen
/** A named color as per [[https://thomaspark.co/2015/11/bob-ross-color-palette-in-css/ Bob Ross]]. This is a shortcut alias to [[o1.gui.colors]]. */
val YellowOchre: Color = G.colors.YellowOchre


/** A grayscale color that contains 10% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black10: G.Color = G.colors.Black10
/** A grayscale color that contains 20% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black20: G.Color = G.colors.Black20
/** A grayscale color that contains 30% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black30: G.Color = G.colors.Black30
/** A grayscale color that contains 40% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black40: G.Color = G.colors.Black40
/** A grayscale color that contains 50% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black50: G.Color = G.colors.Black50
/** A grayscale color that contains 60% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black60: G.Color = G.colors.Black60
/** A grayscale color that contains 70% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black70: G.Color = G.colors.Black70
/** A grayscale color that contains 80% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black80: G.Color = G.colors.Black80
/** A grayscale color that contains 90% black. This is a shortcut alias to [[o1.gui.colors]]. */
val Black90: G.Color = G.colors.Black90
/** A grayscale color that contains 10% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White10: G.Color = G.colors.White10
/** A grayscale color that contains 20% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White20: G.Color = G.colors.White20
/** A grayscale color that contains 30% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White30: G.Color = G.colors.White30
/** A grayscale color that contains 40% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White40: G.Color = G.colors.White40
/** A grayscale color that contains 50% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White50: G.Color = G.colors.White50
/** A grayscale color that contains 60% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White60: G.Color = G.colors.White60
/** A grayscale color that contains 70% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White70: G.Color = G.colors.White70
/** A grayscale color that contains 80% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White80: G.Color = G.colors.White80
/** A grayscale color that contains 90% white. This is a shortcut alias to [[o1.gui.colors]]. */
val White90: G.Color = G.colors.White90

