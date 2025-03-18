package package3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RearrangingFruits {

  public long minCost(int[] basket1, int[] basket2) {

    int[] combinedBasket = new int[basket1.length + basket2.length];

    System.arraycopy(basket1, 0, combinedBasket, 0, basket1.length);
    System.arraycopy(basket2, 0, combinedBasket, basket1.length, basket2.length);

    Map<Integer, Integer> combinedCounter = new HashMap<>();
    for(int fruit: combinedBasket) {
      combinedCounter.put(fruit, combinedCounter.getOrDefault(fruit,0) + 1);
    }

    for(int count: combinedCounter.values()) {
      if(count%2!=0) {
        return -1;
      }
    }

    Map<Integer, Integer> counter1 = new HashMap<>();
    Map<Integer, Integer> counter2 = new HashMap<>();

    for(int fruit : basket1) {
      counter1.put(fruit, counter1.getOrDefault(fruit, 0) + 1);
    }

    for(int fruit : basket2) {
      counter2.put(fruit, counter2.getOrDefault(fruit, 0) + 1);
    }

    List<Integer> excess1 = new ArrayList<>();
    List<Integer> excess2 = new ArrayList<>();

    for(int fruit: combinedCounter.keySet()) {
      int diff = counter1.getOrDefault(fruit, 0) - counter2.getOrDefault(fruit, 0);
      if(diff > 0) {
        for(int i = 0;i<diff/2;i++) {
          excess1.add(fruit);
        }
      }else if(diff<0) {
        for(int i =0 ;i<(-diff)/2;i++) {
          excess2.add(fruit);
        }
      }
    }

    Collections.sort(excess1);
    excess2.sort(Collections.reverseOrder());

    int minFruitCost = Integer.MAX_VALUE;
    for(int fruit: combinedBasket) {
      minFruitCost = Math.min(minFruitCost, fruit);
    }

    long totalCost = 0;
    for(int  i = 0;i<excess1.size();i++) {
      totalCost += Math.min(2*minFruitCost , Math.min(excess1.get(i), excess2.get(i)));
    }

    return totalCost;

  }

}
