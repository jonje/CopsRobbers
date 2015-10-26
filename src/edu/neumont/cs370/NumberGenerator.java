package edu.neumont.cs370;

/**
 * Created by jjensen on 10/26/15.
 */
public class NumberGenerator {

    private int min;
    private int max;
    private int currentNumber = 0;

    public NumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
        currentNumber = min;
    }

    public Coordinate getNextCoordinate() {

        if(currentNumber > max) {
            currentNumber = min + 1;

        } else {
            currentNumber++;
        }

        return new Coordinate(currentNumber, currentNumber);
    }
}
