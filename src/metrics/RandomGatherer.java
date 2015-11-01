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
        RandomNumberGenerator generator = new RandomNumberAdvanced(0, max);
        for (int i = 0; i < max; i++) {
            int temp = generator.getRandomNumber(max);
            if(randomNumbers.contains(temp)) {
                duplicates++;
            }
            randomNumbers.add(temp);
        }

        // Why is this iterator called?
        Iterator<Integer> iterator = randomNumbers.iterator();
        // Why aren't the 'temps' ever used? This is an incomplete sentence
        List<Integer> temps = new ArrayList<>();
        temps.addAll(randomNumbers);


        System.out.println("Duplicates found: " + duplicates);
    }
}
