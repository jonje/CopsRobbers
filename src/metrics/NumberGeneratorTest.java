package metrics;

import copsandrobbers.Coordinate;
import copsandrobbers.RandomNumberAdvanced;
import copsandrobbers.randomgeneration.JavaNativeRandomNumber;
import copsandrobbers.randomgeneration.RandomNumberGenerator;

/**
 * Created by gh255013 on 10/27/15.
 *
 * Used Bill the Lizard's Monte Carlo Pi code as an outline
 * http://www.billthelizard.com/2009/05/how-do-you-test-random-number-generator.html
 */
public class NumberGeneratorTest {
    public static void main(String[] args) {
        //Instatiate number generator to test here
        RandomNumberGenerator rng = new RandomNumberAdvanced(0,1000);

        System.out.println("Custom");
        for(int i = 0; i < 10; i++) {
            int min = 0;
            int max = 1000;
            int numOfPoints = 10000;
            System.out.println(String.format("Error is %.2f%%", monteCarloPiTest(rng, min, max, numOfPoints) * 100));
        }

        System.out.println("\nJava's random");
        rng = new JavaNativeRandomNumber(1000);
        for(int i = 0; i < 10; i++) {
            int min = 0;
            int max = 1000;
            int numOfPoints = 10000;
            System.out.println(String.format("Error is %.2f%%", monteCarloPiTest(rng, min, max, numOfPoints) * 100));
        }

    }

    private static double monteCarloPiTest(RandomNumberGenerator rng, int min, int max, int numOfPoints) {
        int numInCircle = 0;
        double range = max - min;

        for(int i = 0; i < numOfPoints; i++) {
            Coordinate coordinate = rng.getNextCoordinate();
            double x = coordinate.getX() / range;
            double y = coordinate.getY() / range;
            double newRadius = (x * x) + (y * y);

            if(newRadius <= 1) {
                ++numInCircle;
            }
        }
        double generatedPi = closenessToPi(numInCircle, numOfPoints);

        return calculateError(generatedPi);
    }

    private static double closenessToPi(double numInCircle, double numOfPoints) {
        return numInCircle / numOfPoints * 4;
    }

    private static double calculateError(double generatedPi) {
        return (generatedPi - Math.PI) / Math.PI;
    }
}
