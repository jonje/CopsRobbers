package metrics;

import copsandrobbers.RandomNumberAdvanced;
import copsandrobbers.randomgeneration.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jjensen on 10/28/15.
 */
public class RandomGatherer {
    public static void main(String[] args) {
        List<Integer> randomNumbers = new ArrayList<>();

        int max = 1000;
        int duplicates = 0;
        RandomNumberGenerator generator = new RandomNumberAdvanced(0, 1000);
        for (int i = 0; i < max; i++) {
            int temp = generator.getRandomNumber(1000);
            if(randomNumbers.contains(temp)) {
                duplicates++;
            }
            randomNumbers.add(temp);
        }

        Iterator<Integer> iterator = randomNumbers.iterator();
        List<Integer> temps = new ArrayList<>();
        temps.addAll(randomNumbers);


        System.out.println("Duplicates found: " + duplicates);
    }
}
