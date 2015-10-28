package copsandrobbers;

import copsandrobbers.randomgeneration.RandomNumberGenerator;

import java.util.Date;

/**
 * Created by jjensen on 10/26/15.
 */
public class RandomNumberAdvanced implements RandomNumberGenerator {

    private int min;
    private int max;
    private boolean debug = false;

    public RandomNumberAdvanced(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Coordinate getNextCoordinate() {

        return new Coordinate(getRandomNumber(max), getRandomNumber(max));
    }

    @Override
    public int getRandomNumber(int max) {
        long seed = (System.nanoTime());

        long random_seed = seed * 110 + 12345;
        long firstMath = (random_seed / 65536) % 32768;
        int randomNumber = (int) firstMath % max;
        double sin = Math.sin(randomNumber);
//        randomNumber = (int) Math.round(Math.sin(randomNumber) * (max / 2) + (max / 2));

        if (debug) {
//            System.out.println("Seed: " + seed);
//            System.out.println("Sin: " + sin);
            System.out.println(randomNumber + " : " + random_seed);

        }

        return Math.abs(randomNumber);
    }


}
