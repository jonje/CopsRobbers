package copsandrobbers.randomgeneration;

import copsandrobbers.Coordinate;

import java.util.Random;

/**
 * Created by gh255013 on 10/28/15.
 */
public class JavaNativeRandomNumber implements RandomNumberGenerator {
    private int max;
    private Random rand;

    public JavaNativeRandomNumber(int max) {
        this.max = max;
        rand = new Random();
    }

    @Override
    public int getRandomNumber(int max) {
        return (int) rand.nextDouble() * max;
    }

    @Override
    public Coordinate getNextCoordinate() {
        int x = (int) Math.round(rand.nextDouble() * max);
        int y = (int) Math.round(rand.nextDouble() * max);
        return new Coordinate(x, y);
    }
}
