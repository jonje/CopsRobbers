package copsandrobbers.forecaster;

import copsandrobbers.Coordinate;
import copsandrobbers.Forecaster;

import java.util.Iterator;
import java.util.List;

/**
 * Created by gh255013 on 10/28/15.
 */
public class ConcreteForecaster implements Forecaster{
    List<Prediction> previousPredictions;

    public ConcreteForecaster() {

    }

    @Override
    public Coordinate getNextPrediction() {
        return null;
    }
}
