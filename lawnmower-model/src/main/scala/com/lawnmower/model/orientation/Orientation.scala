package com.lawnmower.model.orientation

/**
  * Enumeration of the well-known orientations.
  * @see [[Enumeration]]
  */
object Orientation extends Enumeration {

  /**
    * Alias used to improve this enumeration's type readability.
    */
  type Orientation = Value

  val NORTH = Value("N")
  val EAST = Value("E")
  val SOUTH = Value("S")
  val WEST = Value("W")

  /**
    * Given the source Orientatioon, rotate it by 90 degree to the left.
    *
    * @param source the cardinal point to be rotated.
    * @return the rotation result.
    */
  def rotateLeft(source: Orientation) = source match {
    case NORTH => WEST
    case EAST => NORTH
    case SOUTH => EAST
    case WEST => SOUTH
  }

  /**
    * Given the source Orientatioon, rotate it by 90 degree to the right.
    *
    * @param source the cardinal point to be rotated.
    * @return the rotation result.
    */
  def rotateRight(source: Orientation) = source match {
    case NORTH => EAST
    case EAST => SOUTH
    case SOUTH => WEST
    case WEST => NORTH
  }

}
