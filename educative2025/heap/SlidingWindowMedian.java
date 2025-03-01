package package2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

  public double[] medianSlidingWindow(int[] nums, int k) {

    List<Double> medians = new ArrayList<>();
    Map<Integer, Integer> outGoingMap = new HashMap<>();
    PriorityQueue<Integer> minHeapLargeNum = new PriorityQueue<>((a, b) -> (a - b));
    PriorityQueue<Integer> maxHeapSmallNum = new PriorityQueue<>((a, b) -> (b - a));

    for (int i = 0; i < k; i++) {
      maxHeapSmallNum.offer(nums[i]);
    }

    for (int i = 0; i < k / 2; i++) {
      minHeapLargeNum.offer(maxHeapSmallNum.poll());
    }

    int balance = 0;
    int i = k;

    while (true) {
      if ((k & 1) == 1) {
        medians.add((double) maxHeapSmallNum.peek());
      } else {
        medians.add(
            (double) (((long) maxHeapSmallNum.peek() + (long) minHeapLargeNum.peek())) * 0.5);
      }
      if (i >= nums.length) {
        break;
      }
      int outGoingNum = nums[i - k];
      int incomingNum = nums[i];
      i++;

      if (outGoingNum <= maxHeapSmallNum.peek()) {
        balance--;
      } else {
        balance++;
      }

      if (outGoingMap.containsKey(outGoingNum)) {
        outGoingMap.put(outGoingNum, outGoingMap.get(outGoingNum) + 1);
      } else {
        outGoingMap.put(outGoingNum, 1);
      }

      if (maxHeapSmallNum.size() > 0 && incomingNum <= maxHeapSmallNum.peek()) {
        balance++;
        maxHeapSmallNum.offer(incomingNum);
      } else {
        balance--;
        minHeapLargeNum.offer(incomingNum);
      }

      if (balance < 0) {
        maxHeapSmallNum.offer(minHeapLargeNum.poll());
      } else if (balance > 0) {
        minHeapLargeNum.offer(maxHeapSmallNum.poll());
      }

      balance = 0;
      while (maxHeapSmallNum.size() > 0 && outGoingMap.containsKey(maxHeapSmallNum.peek()) &&
          outGoingMap.get(maxHeapSmallNum.peek()) > 0) {
        outGoingMap.put(maxHeapSmallNum.peek(), outGoingMap.get(maxHeapSmallNum.poll()) - 1);
      }
      while (minHeapLargeNum.size() > 0 && outGoingMap.containsKey(minHeapLargeNum.peek()) &&
          outGoingMap.get(minHeapLargeNum.peek()) > 0) {
        outGoingMap.put(minHeapLargeNum.peek(), outGoingMap.get(minHeapLargeNum.poll()) - 1);
      }

    }

    double[] arr = medians.stream().mapToDouble(Double::doubleValue).toArray();

    return arr;

  }

}
