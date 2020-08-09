package com.lawnmower.model.lawn

import com.lawnmower.model.orientation.Orientation
import com.lawnmower.model.orientation.Orientation.Orientation
import com.lawnmower.position.Position

import scala.collection.immutable.HashSet

/**
  * A lawn, represented as a grid of [[Position]]s from the bottom left corner (always (0,0)) to the top right one,
  * provided during the instantiation.
  * @param topRightAbscissa the top right point's abscissa.
  * @param topRightOrdinate the top right point's ordinate.
  */
class Lawn private(topRightAbscissa: Int, topRightOrdinate: Int) {

  if (topRightAbscissa == 0 && topRightOrdinate == 0) {
    throw new IllegalArgumentException("A lawn should have at least one point.")
  }

  if (topRightAbscissa < 0 || topRightOrdinate < 0) {
    throw new IllegalArgumentException("A lawn can't have top right corner with negative coordinate.")
  }

  val bottomLeftCorner = Position(0, 0)
  val topRightCorner = Position(topRightAbscissa, topRightOrdinate)
  private var bookedPositions = new HashSet[Position]

  /**
    * Initialize the given position if free and inside the lawn's bounds. Initialize means "booking the position without
    * releasing an existing one".
    * @param position the position to be initialized.
    * @throws IllegalArgumentException if the position is already booked or out of bounds.
    */
  def initializePosition(position: Position) {
    if (isBooked(position)) {
      throw new IllegalArgumentException("Position already booked: " + position)
    }

    if (isOutOfBounds(position)) {
      throw new IllegalArgumentException("Position out of bounds: " + position)
    }

    bookedPositions += position
  }

  /**
    * Book the next position from the given one, releasing it. If the next position is
    * out of bounds or booked, do nothing and return the given position.
    * @param currentPosition the source position.
    * @param orientation the source orientation.
    * @return the next available position if this one is valid, the current one otherwise.
    */
  def checkAndBookNextPosition(currentPosition: Position, orientation: Orientation) = orientation match {
    case Orientation.EAST => checkAndBookPosition(currentPosition, currentPosition.translate(Position(1, 0)))
    case Orientation.NORTH => checkAndBookPosition(currentPosition, currentPosition.translate(Position(0, 1)))
    case Orientation.SOUTH => checkAndBookPosition(currentPosition, currentPosition.translate(Position(0, -1)))
    case Orientation.WEST => checkAndBookPosition(currentPosition, currentPosition.translate(Position(-1, 0)))
  }

  /**
    * Book the next position, releasing the current one. If next position is out of bounds or
    * booked, do nothing and return the current position.
    * @param currentPosition the source and currently booked position.
    * @param nextPosition the position to be checked and booked
    * @return the next position if this one is valid, the current one otherwise.
    */
  private def checkAndBookPosition(currentPosition: Position, nextPosition: Position) = {
    if (isBooked(nextPosition) || isOutOfBounds(nextPosition)) currentPosition
    else bookPosition(currentPosition, nextPosition)
  }

  /**
    * Check if the given position is out of the lawn's bounds.
    * @param position the position to be checked.
    * @return the checking result.
    */
  private def isOutOfBounds(position: Position) = {
    val abscissas = bottomLeftCorner.abscissa.to(topRightCorner.abscissa)
    val ordinates = bottomLeftCorner.ordinate.to(topRightCorner.ordinate)
    !abscissas.contains(position.abscissa) || !ordinates.contains(position.ordinate)
  }

  /**
    * Book the given position releasing the current one.
    * @param currentPosition the current position to release.
    * @param positionToBook the position to book.
    * @return the booked position.
    */
  private def bookPosition(currentPosition: Position, positionToBook: Position) = {
    bookedPositions = bookedPositions + positionToBook - currentPosition
    positionToBook
  }

  /**
    * Check if the given position is booked on this lawn.
    * @param position the position to be checked.
    * @return the checking result.
    */
  def isBooked(position: Position) = bookedPositions.contains(position)

}

/**
  * Companion object of the [[Lawn]] class.
  */
object Lawn {

  /**
    * Well-known apply method allowing an easy use of the primary constructor.
    * @param topRightAbscissa the top right point's abscissa.
    * @param topRightOrdinate the top right point's ordinate.
    * @return the created instance.
    */
  def apply(topRightAbscissa: Int, topRightOrdinate: Int) = new Lawn(topRightAbscissa, topRightOrdinate)
}

