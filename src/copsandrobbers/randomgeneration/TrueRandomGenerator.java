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

    @Override
    public int getRandomNumber(int max) {
        int range = 0;

        while (max >= range) {
            int t = + helper.nextByte();
            range = (range * 256) + t;
        }

        int aceptRange = (range / max) - 1;
        int multiple = range / (aceptRange + 1);

        /*
         * Use rejection sampling to find a number
         * in the specified range.
         * Note: This implementation is wasteful
         * find a better way to do this.
         */
        int sample = aceptRange + 1;

        while (sample >= aceptRange || sample < 0) {
            sample = helper.nextByte();
        }

        return sample % multiple;
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
