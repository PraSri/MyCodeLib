package package2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {

  static class Pair {
    int sum, i, j;
    public Pair(int sum, int i, int j) {
      this.sum = sum;
      this.i = i;
      this.j = j;
    }
  }

  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

    List<List<Integer>> result = new ArrayList<>();
    int listLength = nums1.length;
    PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> (a.sum - b.sum));

    for(int i = 0;i<Math.min(k, listLength); i++) {
      int sumValue = nums1[i] + nums2[0];
      int listIndex = i;
      int valueIndex = 0;
      minHeap.offer(new Pair(sumValue, listIndex, valueIndex));
    }

    int counter = 1;
    while(!minHeap.isEmpty() && counter<=k) {
      Pair pair = minHeap.poll();
      int i = pair.i;
      int j = pair.j;
      result.add(Arrays.asList(nums1[i], nums2[j]));
      int nextElement = j+1;
      if(nums2.length > nextElement) {
        minHeap.offer(new Pair(nums1[i] + nums2[nextElement], i, nextElement));
      }
      counter++;
    }

    return result;

  }
}
