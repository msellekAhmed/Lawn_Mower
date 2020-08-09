package com.lawnmower.model.mower

import com.lawnmower.model.lawn.Lawn
import com.lawnmower.position.Position
import com.lawnmower.model.orientation.Orientation
import com.lawnmower.model.orientation.Orientation.Orientation


/**
  * A mower, represented as a pair of position and orientation inside a parent lawn.
  *
  * @param position the mower's position.
  * @param orientation the mower's orientation.
  * @param lawn the lawn to be mown.
  */
class Mower private(var position: Position, var orientation: Orientation, val lawn: Lawn) {

  lawn.initializePosition(position)

  /**
    * Explore the mower's lawn from the given actions then return the current position and orientation.
    * @param commands an ordered [[List]] of actions to be executed.
    * @return a tuple (abscissa, ordinate, orientation).
    */
  def exploreLawn(commands: List[Char]) = {
    commands.foreach { case 'F' => position = lawn.checkAndBookNextPosition(position, orientation)
    case 'R' => orientation = Orientation.rotateRight(orientation)
    case 'L' => orientation = Orientation.rotateLeft(orientation)
    case _ => // Skip any other char
    }

    (position.abscissa, position.ordinate, orientation)
  }

}

/**
  * Companion object of the [[Mower]] class.
  */
object Mower {

  /**
    * Well-known apply method allowing an easy use of the primary constructor.
    * @param position the mower's position.
    * @param orientation the mower's orientation.
    * @param parent the lawn to be mown.
    * @return the created instance.
    */
  def apply(position: Position, orientation: Orientation, parent: Lawn) = new Mower(position, orientation, parent)
}
