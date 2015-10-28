package copsandrobbers.forecaster;

import copsandrobbers.Coordinate;
import copsandrobbers.Forecaster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gh255013 on 10/28/15.
 */
public class ConcreteForecaster implements Forecaster{
    private List<PredictionResult> predictionResults;
    private Coordinate previousPrediction;

    public ConcreteForecaster() {
        predictionResults = new ArrayList<>();
    }


    @Override
    public Coordinate getNextPrediction() {
        //TODO
        //Actually implement predictions
        previousPrediction = new Coordinate(500, 500);

        return previousPrediction;
    }

    @Override
    public void receiveCrimeCoordinate(Coordinate crime) {
        predictionResults.add(new PredictionResult(previousPrediction, crime));
    }
}
