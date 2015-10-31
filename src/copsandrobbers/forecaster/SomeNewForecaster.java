package copsandrobbers.forecaster;// this is be coding my thoughts on the idea of double smoothing

import copsandrobbers.Coordinate;
import copsandrobbers.Forecaster;
import copsandrobbers.helpers.Tuple;

public class SomeNewForecaster implements Forecaster {

    // the higher this number, the more dramatic the guess. Allows for rapid change in the guessing pattern
    private final double alphaTolerance = 0.35;

    // 'Bt' is the BEST ESTIMATE of the random trend
    private final double gammaFactor = 0.1; // FIXME: this is not the proper assignment.

    private Coordinate initialXY = null;

    private int previousSmoothX = -1;
    private int previousSmoothY = -1;
    // what about
    private Coordinate previousCoordinate = null; // this has ".getX/Y()"...

    @Override
    public Coordinate getNextPrediction() {
        Coordinate retCoordinate = null;
        if ( previousCoordinate != null ) {
            Tuple<Double, Double> guessXY = sChangeT();
            // TODO: this is only single smoothness. Need double, or triple
            int retX = ( (int) Math.round(guessXY.item1) );
            int retY = ( (int) Math.round(guessXY.item2) );

            previousSmoothX = retX;
            previousSmoothY = retY;

            retCoordinate = new Coordinate(retX, retY);
        }
        else {
            // scratch: see next else block
        }

        return retCoordinate;
    }

    @Override
    public void receiveCrimeCoordinate( Coordinate crime ) {
        // this is where we turn our brains on
        // todo: use this video as a reference for the function
        if ( initialXY == null ) {
            previousCoordinate = crime;
            int x = previousCoordinate.getX();
            int y = previousCoordinate.getY();
            initialXY = new Coordinate(x, y);
        }
        else {
            // TODO: this comes into play when we double smooth
        }
    }

    private Tuple<Double, Double> sChangeT(/*Coordinate resultCoordinate*/) {
        if ( initialXY == null )
            throw new IllegalArgumentException("You can't call this function before assigning 'initialXY");

        int previousY = previousCoordinate.getY();
        int previousX = previousCoordinate.getX();

        double newXSmooth = alphaTolerance * previousX + ( 1 - alphaTolerance ) * previousSmoothX;
        double newYSmooth = alphaTolerance * previousY + ( 1 - alphaTolerance ) * previousSmoothY;

        return Tuple.create(newXSmooth, newYSmooth);
    }

}
