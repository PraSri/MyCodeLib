package package2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SuperUglyNumber {

  public int nthSuperUglyNumber(int n, int[] primes) {

    List<Integer> uglyNumbers = new ArrayList<>();
    uglyNumbers.add(1); // the first ugly number is always 1

    PriorityQueue<long[]> minHeap = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

    for (int prime : primes) {
      minHeap.offer(new long[] {prime, prime, 0}); // 0 is the index of 1st ugly number i.e. 1
    }

    while (uglyNumbers.size() < n) {
      long[] entry = minHeap.poll();
      long nextUgly = entry[0];
      long prime = entry[1];
      int index = (int) entry[2];

      // avoid dup by comparing last inserted ugly number
      if (nextUgly != uglyNumbers.get(uglyNumbers.size() - 1)) {
        uglyNumbers.add((int) nextUgly);
      }
      // push the next multiple of current prime into the heap
      minHeap.offer(new long[] {prime * uglyNumbers.get(index + 1), prime, index + 1});
    }

    return uglyNumbers.get(n - 1);

  }

}
