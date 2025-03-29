package two_pointers.top_K_elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumCostHireKWorkers {

  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

    // create list of pairs with (wage/quality ratio, quality)
    List<double[]> workers = new ArrayList<>();
    for(int i = 0;i<quality.length;i++) {
      workers.add(new double[]{(double) wage[i] /quality[i], quality[i]});
    }

    // sort the workers by wage/quality ratio
    Collections.sort(workers, Comparator.comparingDouble(a -> a[0]));

    // max-heap to maintain k smallest quality
    PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    double totalQuality = 0;
    double minCost = Double.MAX_VALUE;

    // iterate through each worker sorted in ascending order by its wage/quality ratio
    for (double[] worker : workers) {
      double ratio = worker[0];
      double q = worker[1];
      maxHeap.add(q);
      totalQuality+=q;
      // if more than k, remove the maximum quality to maintain the k smallest quality
      if(maxHeap.size()>k) {
        totalQuality-=maxHeap.poll();
      }
      // if we have exactly k quality calculate the min cost
      if(maxHeap.size()==k) {
        minCost = Math.min(minCost, ratio * totalQuality);
      }
    }

    return minCost;
  }
}
