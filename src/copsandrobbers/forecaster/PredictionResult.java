package copsandrobbers.forecaster;

import copsandrobbers.Coordinate;

/**
 * Created by gh255013 on 10/28/15.
 */
public class PredictionResult {
    private Coordinate prediction;
    private Coordinate crime;
    private double xDifference;
    private double yDifference;

    public PredictionResult(Coordinate prediction, Coordinate crime) {
        this.prediction = prediction;
        this.crime = crime;
        this.xDifference = -1;
        this.yDifference = -1;
    }

    public double getXDifference() {
        if(xDifference == -1)
           xDifference = Math.abs(prediction.getX() - crime.getX());

        return xDifference;
    }

    public double getYDifference() {
        if(yDifference == -1)
            yDifference = Math.abs(prediction.getY() - crime.getY());

        return yDifference;
    }
}
