package copsandrobbers.randomgeneration;

import copsandrobbers.Coordinate;

import java.security.SecureRandom;

/**
 * Created by stephen on 11/4/15.
 */
public class JavaSecureRandomNumber implements RandomNumberGenerator {

    private int max;
    private SecureRandom random;

    public JavaSecureRandomNumber( int max ) {
        this.max = max;
        this.random = new SecureRandom();
    }

    @Override
    public Coordinate getNextCoordinate() {
        int x = (int) Math.round(random.nextDouble() * max);
        int y = (int) Math.round(random.nextDouble() * max);
        return new Coordinate(x, y);
    }

    @Override
    public int getRandomNumber( int max ) {
        return (int) ( random.nextDouble() * max );
    }
}
