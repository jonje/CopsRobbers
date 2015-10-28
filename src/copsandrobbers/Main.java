package copsandrobbers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjensen on 10/26/15.
 */
public class Main {
    public static void main(String[] args) {
        List<Coordinate> crimesCommited = new ArrayList<>();
        RandomNumberAdvanced coordinateGenerator = new RandomNumberAdvanced(0, 1000);
        Coordinate coordinate = coordinateGenerator.getNextCoordinate();
        System.out.println(coordinate.getX() + " " + coordinate.getY());
    }
}
