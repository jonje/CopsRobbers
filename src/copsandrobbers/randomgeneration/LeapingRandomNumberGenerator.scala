package copsandrobbers.randomgeneration

import copsandrobbers.Coordinate

import scala.util.Random

/**
 * Created by stephen on 10/27/15.
 */
class LeapingRandomNumberGenerator(val min: Int, val max: Int) extends RandomCoordinateGenerator {

  val randomRef = new Random(1000)

  val slopeX = getNextRandomInt

  val slopeY = getNextRandomInt

  var prevNumber = 0: Int

  override def getNextCoordinate: Coordinate = {
    val leapFactor = getNextRandomInt
    // generate next x-coordinate, by slope
    val coordinateX = prevNumber * slopeX * leapFactor
    // generate next y-coordinate, by slope
    val coordinateY = prevNumber * slopeY * leapFactor

    prevNumber = if (prevNumber > max) min else prevNumber + 1

    new Coordinate(coordinateX, coordinateY)
  }

  private def getNextRandomInt: Int = {
    randomRef.nextInt(max + min) + min
  }
}
