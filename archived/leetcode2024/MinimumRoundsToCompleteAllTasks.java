package leetcode2024;

import java.util.HashMap;
import java.util.Map;

public class MinimumRoundsToCompleteAllTasks {


  public static void main(String[] args) {

  }

  // same question
  // https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
  // https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/description/

  public int minimumRounds(int[] tasks) {

    // lookup for task level & its count

    Map<Integer, Integer> freq = new HashMap<>();

    for(int t: tasks) {
      freq.put(t, freq.getOrDefault(t, 0) + 1);
    }

    // if freq is 1 -> return -1
    // if freq is 3K -> return k batches
    // if freq is 3K+1 -> return k + 1 batches
    // if freq is 3K+2 -> returns k+1 batches   ==> for 5 = (3(1) + 2) => ### ## => 2 => k + 1

    // so if freq % 3 => total += freq/3
    // else total += freq/3 + 1

    int total = 0;

    for(int f: freq.values()) {
      if(f == 1) return -1;
      if(f%3==0) total += f/3;
      if(f%3!=0) total += f/3 + 1;
    }

    return total;

  }


}
