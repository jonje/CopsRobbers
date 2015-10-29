package copsandrobbers.randomgeneration;

import copsandrobbers.Coordinate;

/**
 * Created by devin on 10/28/15.
 */
public class TrueRandomGenerator implements RandomNumberGenerator {
    private int max;
    private HotBitsHelper helper;

    public TrueRandomGenerator(int max) {
        this.max = max;
        this.helper = new HotBitsHelper(128);
    }

    @Override
    public Coordinate getNextCoordinate() {
        return null;
    }

    // I dont think im doing this right, but its two and im tired. Fix later.
    @Override
    public int getRandomNumber(int max) {
        byte info = helper.nextByte();

        /*
         * Use rejection sampling to find a number
         * in the specified range.
         * Note: This implementation is wasteful
         * find a better way to do this.
         */
        while (info >= max + 1 || info < 0) {
            info = helper.nextByte();
        }

        return info;
    }

    private int nextPowerOf2(int a) {
        int b = 1;
        while (b < a)
        {
            b <<= 1;
        }

        return b;
    }
}
