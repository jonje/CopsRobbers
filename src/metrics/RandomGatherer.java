package metrics;

import copsandrobbers.randomgeneration.JavaNativeRandomNumber;
import copsandrobbers.randomgeneration.RandomNumberAdvanced;
import copsandrobbers.randomgeneration.RandomNumberGenerator;
import copsandrobbers.randomgeneration.TrueRandomGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjensen on 10/28/15.
 */
public class RandomGatherer {
    public static void main(String[] args) {
        List<Integer> randomNumbers = new ArrayList<>();

        int max = 10000;
        int timesRun = 1000;
        int duplicates = 0;
        int count = 0;
        RandomNumberGenerator generator = new JavaNativeRandomNumber(max);
        long start = System.currentTimeMillis();
        for (int i = 0; i < timesRun; i++) {
//            int temp = generator.getRandomNumber(max);
//            count += temp;
//            if(randomNumbers.contains(temp)) {
//                duplicates++;
//            }
//            randomNumbers.add(temp);
            System.out.println(generator.getRandomNumber(max));
        }

//        long end = System.currentTimeMillis();
//
//        System.out.println("Duplicates found: " + duplicates);
//        System.out.println("Mean for" + randomNumbers.size() + " is: " + (count / randomNumbers.size()));
//        System.out.println("Time taken in millis to run " + max + " times: " + (end - start));
    }
}
