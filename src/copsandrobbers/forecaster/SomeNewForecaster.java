package copsandrobbers.forecaster;// this is be coding my thoughts on the idea of double smoothing

import copsandrobbers.Coordinate;
import copsandrobbers.Forecaster;
import copsandrobbers.helpers.Tuple;

/**
 * Utilizes the double exponential smoothing operation, named "Brown's double exponential smoothing"
 * Source: https://en.wikipedia.org/wiki/Exponential_smoothing#Optimization
 */
public class SomeNewForecaster implements Forecaster {

    private static final int GRID_MAXIMUM = 1000;
    // the higher this number, the more dramatic the guess. Allows for rapid change in the guessing pattern
    private final double alphaTolerance = 0.35;

    private Coordinate initialXY = null;

    private int previousSmoothX = -1;
    private int previousSmoothY = -1;

    private int previousSmoothPrimeX = -1;
    private int previousSmoothPrimeY = -1;

    private Coordinate previousCoordinate;

    @Override
    public Coordinate getNextPrediction() {
        Coordinate retCoordinate;
        if ( initialXY != null ) {
            retCoordinate = getNextForecast();
            checkPointSize(retCoordinate);
        }
        else {
            // got to start somewhere. Almost guaranteed to be wrong
            retCoordinate = new Coordinate(0, 0); // 1 in a million chance of correctness
        }

        return retCoordinate;
    }

    private Coordinate getNextForecast() {
        Coordinate retCoordinate;
        Tuple<Double, Double> sChange_T = sChangeT();
        // smoothnessAtTime_t = sChangeT // at the coordinate occurrence time 't'
        previousSmoothX = ( (int) Math.floor(sChange_T.item1) );
        previousSmoothY = ( (int) Math.floor(sChange_T.item2) );

        // smoothnessPrimeAtTime_t = sPrimeChangeT // at calculation of smoothnessAtTime_t of some time 't'
        Tuple<Double, Double> smoothChange_T = sPrimeChangeT(sChange_T);

        previousSmoothPrimeX = ( (int) Math.floor(smoothChange_T.item1) );
        previousSmoothPrimeY = ( (int) Math.floor(smoothChange_T.item2) );

        //Forecast = estimateAtTime_t + increasingInt * betaToleranceAtTime_t
        Tuple<Double, Double> forecastTuple = forecastWithMScalar(1, sChange_T, smoothChange_T);

        int retX = (int) Math.floor(forecastTuple.item1);
        int retY = (int) Math.floor(forecastTuple.item2);

        retCoordinate = new Coordinate(retX, retY);
        return retCoordinate;
    }

    @Override
    public void receiveCrimeCoordinate( Coordinate crime ) {
        if ( initialXY == null ) {
            initialXY = new Coordinate(crime.getX(), crime.getY());
            initializeSmoothness();
        }
        else {
            // scratch: I believe this is already handled.
            // The initial tests (as of Oct 31, 2015)
            /* For the avg distance tests in ForecasterTest.main()
            1) 43.20,
            2) 32.80,
            3) 37.35,
            4) 34.31,
            5) 47.05,
            6) 29.26,
            7) 27.91,
            8) 37.74,
            9) 43.74,
            10) 44.78,
            11) 39.50,
            12) 44.38

Statistics: Mean = 38.50, Std = 6.3756, Variance = 40.64862, pStd = 6.1042
8:00PM MDST, Stephen's comment: "I think these numbers can be better"
             */
        }

        previousCoordinate = crime;
    }

    private Tuple<Double, Double> sChangeT() {
        if ( initialXY == null )
            throw new IllegalArgumentException("You can't call this function before assigning 'initialXY");

        int previousY = previousCoordinate.getY();
        int previousX = previousCoordinate.getX();

        double newXSmooth = alphaTolerance * previousX + ( 1 - alphaTolerance ) * previousSmoothX;
        double newYSmooth = alphaTolerance * previousY + ( 1 - alphaTolerance ) * previousSmoothY;

        return Tuple.create(newXSmooth, newYSmooth);
    }

    private Tuple<Double, Double> sPrimeChangeT( Tuple<Double, Double> sChangeT ) {

        double newXSmoothPrime = alphaTolerance * sChangeT.item1 + ( 1 - alphaTolerance ) * previousSmoothPrimeX;
        double newYSmoothPrime = alphaTolerance * sChangeT.item2 + ( 1 - alphaTolerance ) * previousSmoothPrimeY;

        return Tuple.create(newXSmoothPrime, newYSmoothPrime);
    }

    /**
     * A second method, referred to as either Brown's linear exponential smoothing (LES) or Brown's
     * double exponential smoothing works as follows. http://www.duke.edu/~rnau/411avg.htm
     *
     * @param mScalar        "m" in F_(t+m) = a_t + (m * b_t)
     * @param sChangeT       the "s'_t" in 2s'_t - s''_t and s'_t = estimate(coordinate_t) + (1 - alphaTolerance) * s'_(t-1)
     * @param smoothChange_t the "s''_t" in the preceding equations' supplement
     * @return forecasts of 'x' and 'y' coordinates, using the F_(t+m) = ... equation
     */
    private Tuple<Double, Double> forecastWithMScalar( int mScalar, Tuple<Double, Double> sChangeT, Tuple<Double, Double> smoothChange_t ) {

        double estimateX = 2 * sChangeT.item1 - smoothChange_t.item1;
        double estimateY = 2 * sChangeT.item2 - smoothChange_t.item2;

        double betaX = ( alphaTolerance / ( 1 - alphaTolerance ) ) * ( sChangeT.item1 - smoothChange_t.item1 );
        double betaY = ( alphaTolerance / ( 1 - alphaTolerance ) ) * ( sChangeT.item2 - smoothChange_t.item2 );

        double predictionX = Math.floor(estimateX + Math.round(mScalar * betaX));
        double predictionY = Math.floor(estimateY + Math.round(mScalar * betaY));

        return Tuple.create(predictionX, predictionY);
    }

    private void initializeSmoothness() {
        this.previousSmoothX = initialXY.getX();
        this.previousSmoothPrimeX = initialXY.getX();

        this.previousSmoothY = initialXY.getY();
        this.previousSmoothPrimeY = initialXY.getY();
    }

    private void checkPointSize( Coordinate retCoordinate ) {

        int x = retCoordinate.getX();
        if ( x > GRID_MAXIMUM ) {
            System.out.println(String.format("Coordinate generated had 'too high' X-value: %d. Reducing...", x));
            retCoordinate.setX(GRID_MAXIMUM);
        }

        int y = retCoordinate.getY();
        if ( y > GRID_MAXIMUM ) {
            System.out.println(String.format("Coordinate generated had 'too high' Y-value: %d. Reducing...", y));
            retCoordinate.setY(GRID_MAXIMUM);
        }
    }

}
