package package2;

import java.util.PriorityQueue;

public class FindMedianDataStream {

  public static class MedianFinder {

    private PriorityQueue<Integer> minHeapForLargeNum;
    private PriorityQueue<Integer> maxHeapForSmallNum;

    public MedianFinder() {
      this.minHeapForLargeNum = new PriorityQueue<>(
          (a, b) -> (a - b)); // its root represent the smallest number for the larger numbers
      this.maxHeapForSmallNum = new PriorityQueue<>(
          (a, b) -> (b - a)); // its root represent the largest number for the smaller numbers
    }

    public void addNum(int num) {
      if (maxHeapForSmallNum.isEmpty() || maxHeapForSmallNum.peek() >= num) {
        maxHeapForSmallNum.offer(num);
      } else {
        minHeapForLargeNum.offer(num);
      }

      // either both heaps will have same size OR max heap will have one more than min heap
      if (maxHeapForSmallNum.size() > minHeapForLargeNum.size() + 1) {
        minHeapForLargeNum.offer(maxHeapForSmallNum.poll());
      } else if (maxHeapForSmallNum.size() < minHeapForLargeNum.size()) {
        maxHeapForSmallNum.offer(minHeapForLargeNum.poll());
      }
    }

    public double findMedian() {
      if (minHeapForLargeNum.size() == maxHeapForSmallNum.size()) {
        return minHeapForLargeNum.peek() / 2.0 + maxHeapForSmallNum.peek() / 2.0;
      }
      return maxHeapForSmallNum.peek();
    }
  }

}
