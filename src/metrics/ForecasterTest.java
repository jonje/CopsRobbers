package metrics;

import copsandrobbers.Coordinate;
import copsandrobbers.Forecaster;
import copsandrobbers.RandomNumberAdvanced;
import copsandrobbers.forecaster.ConcreteForecaster;
import copsandrobbers.forecaster.PredictionResult;
import copsandrobbers.randomgeneration.RandomNumberGenerator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by gh255013 on 10/27/15.
 */
public class ForecasterTest {
    public static void main(String[] args) {
        RandomNumberGenerator rng = new RandomNumberAdvanced(0, 1000);
        Forecaster forecaster = new ConcreteForecaster();
        int numOfCrimes = 1000;

        Iterator<PredictionResult> predictionResultIterator = runTest(rng, forecaster, numOfCrimes);
        System.out.println(String.format("Avg distance is %.2f", averageDistanceFromCrime(predictionResultIterator)));
    }

    public static Iterator<PredictionResult> runTest(RandomNumberGenerator rng, Forecaster forecaster, int numOfCrimes) {
        PredictionResult[] predictionResults = new PredictionResult[numOfCrimes];

        for(int i = 0; i < numOfCrimes; i++) {
            Coordinate crime = rng.getNextCoordinate();
            Coordinate prediction = forecaster.getNextPrediction();

            PredictionResult predictionResult = new PredictionResult(prediction, crime);
            predictionResults[i] = predictionResult;

            forecaster.receiveCrimeCoordinate(crime);
        }

        return Arrays.asList(predictionResults).iterator();
    }

    public static double averageDistanceFromCrime(Iterator<PredictionResult> it) {
        double totalDistance = 0;
        double numOfCrimes = 0;

        while(it.hasNext()) {
            PredictionResult result = it.next();
            double x = result.getXDifference() * result.getXDifference();
            double y = result.getYDifference() * result.getYDifference();
            double distance = Math.sqrt((x + y));

            totalDistance += distance;
            ++numOfCrimes;
        }

        double avg = totalDistance / numOfCrimes;
        return avg;
    }
}
