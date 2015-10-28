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
        int seed = (int) (System.nanoTime()/1000);

        int random_seed = seed * 1103515245 + 12345;
        int randomNumber = (random_seed / 65536) % 32768;
        double sin = Math.sin(randomNumber);
        randomNumber = (int) (Math.sin(randomNumber) * (max / 2) + (max / 2));

        if (debug) {
            System.out.println("Seed: " + seed);
            System.out.println("Sin: " + sin);

        }

        return randomNumber;
    }


}
