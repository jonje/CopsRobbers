package edu.neumont.cs370;

import java.util.Iterator;

/**
 * Created by jjensen on 10/26/15.
 */
public class Forcaster {
    Iterator<Coordinate> crimes;

    public Forcaster(Iterator<Coordinate> crimes) {
        this.crimes = crimes;
    }

    public Coordinate getNextPrediction(){
        return null;
    }
}
