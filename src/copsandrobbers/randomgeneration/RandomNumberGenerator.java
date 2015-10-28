package copsandrobbers.randomgeneration;

import copsandrobbers.Coordinate;

/**
 * Created by stephen on 10/27/15.
 */
public interface RandomNumberGenerator {
    Coordinate getNextCoordinate();
    int getRandomNumber(int max);
}
