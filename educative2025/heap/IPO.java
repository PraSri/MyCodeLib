package package2;

import java.util.PriorityQueue;

public class IPO {

  /**
   * https://leetcode.com/problems/ipo/description/
   * <p>
   * We’ll push all the capitals values in a min-heap to pick the required number of projects,
   * starting from the one with the lowest capital requirement.
   * <p>
   * Let’s identify all the projects whose capital requirements are less than or equal to
   * our current capital and push the profits yielded by these projects onto the max-heap
   * <p>
   * Now, we can select the most affordable project that yields the highest profit.
   * We’ll add the profit from every selected project to the previous capital to
   * calculate the maximum capital we can accumulate
   */

  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

    int n = capital.length;
    int currentCapital = w;

    PriorityQueue<int[]> capitalMinHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    PriorityQueue<int[]> profitMaxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

    for (int i = 0; i < n; i++) {
      capitalMinHeap.offer(new int[] {capital[i], i});
    }

    int i = 0;
    while (i < k) {

      while (!capitalMinHeap.isEmpty() && capitalMinHeap.peek()[0] <= currentCapital) {
        profitMaxHeap.offer(new int[] {profits[capitalMinHeap.poll()[1]]});
      }

      if (profitMaxHeap.isEmpty()) {
        break;
      }

      currentCapital += profitMaxHeap.poll()[0];

      i++;
    }

    return currentCapital;


  }

}
