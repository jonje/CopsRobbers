package copsandrobbers;

import java.util.Iterator;

/**
 * Created by jjensen on 10/26/15.
 */
public class Forecaster {
    Iterator<Coordinate> crimes;

    public Forecaster(Iterator<Coordinate> crimes) {
        this.crimes = crimes;
    }

    public Coordinate getNextPrediction(){
        return null;
    }
}
