package copsandrobbers.randomgeneration;

import copsandrobbers.Coordinate;

import java.nio.ByteBuffer;

/**
 * Created by devin on 10/28/15.
 */
public class TrueRandomGenerator implements RandomNumberGenerator {
    private static final int NUM_OF_BYTES_IN_INT = 4;
    private static final int MAX_HOTBITS_REQUEST = 128;
    private HotBitsHelper helper;

    public TrueRandomGenerator() {
        this.helper = new HotBitsHelper(MAX_HOTBITS_REQUEST);
    }

    @Override
    public Coordinate getNextCoordinate() {
        return new Coordinate(this.getRandomNumber(1000), this.getRandomNumber(1000));
    }

//    @Override
//    public int getRandomNumber(int max) {
//        int range = 0;
//
//        while (max >= range) {
//            int t = + helper.nextByte();
//            range = (range * 256) + t;
//        }
//
//        int acceptRange = (range / max) - 1;
//        int multiple = range / (acceptRange + 1);
//
//        /*
//         * Use rejection sampling to find a number
//         * in the specified range.
//         * Note: This implementation is wasteful
//         * find a better way to do this.
//         */
//        int sample = acceptRange + 1;
//
//        while (sample >= acceptRange || sample < 0) {
//            sample = helper.nextByte();
//        }
//
//        int nextByte = helper.nextByte();
//
//        int result = sample % multiple;
//        return sample % multiple;
//    }

    @Override
    public int getRandomNumber(int max) {
        byte[] randomBytes = new byte[NUM_OF_BYTES_IN_INT]; //size of int

        for(int i = 0; i < NUM_OF_BYTES_IN_INT; i++) {
            randomBytes[i] = helper.nextByte();
        }

        int randomInt = ByteBuffer.wrap(randomBytes).getInt();
        System.out.println(randomInt);
        int randomIntInRange = Math.abs(randomInt % max);

        return randomIntInRange;
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
