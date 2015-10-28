Statistical Randomness
-
[Wikipedia Source](https://en.wikipedia.org/wiki/Statistical_randomness)

### Overview
<b>Definition</b>: not <em>necessarily</em> "true" random, but a sequence without any discernable patterns.

Random results should therefore be objective unpredictable


#### <em>an aside</em>
This could be potentially problematic, given that the ```Forecaster``` must be able to find the crime before it occurs.
- Given this, perhaps the best approach would be to attempt to guess the random seed
  - then seek to the random occurrence.
- From here, we can just call ```nextInt()``` or ```nextCoordinate()``` to match with the crime occurrence count
  - Ex:
    - Crimes have been witnessed, at seemingly random locations
      - Every time a crime is witnessed, our forecaster guesses a random seed
    - If it is the third crime, the forecaster rolls a random seed, then (with that seed) calls ```nextRandom()``` three times
    - A check then occurs, to see if the n-th random (in our case, third) is the same coordinate
    - If so, proceed to use that random seed to catch the next crime

This is a rough implementation idea
#### <em>End Aside</em>

"In the long run" sampling (what were going for) CAN - and arguably <b>should</b> - contain duplicate numbers.

Chaos theory argues that the genuine, true random is very attainable.

### Testing Randomness
- Frequency tests are generally the "go to" for testing a RNG
  - Tests vary slightly - depending on digit count
  - There is also a provided tolerance of frequency differences
