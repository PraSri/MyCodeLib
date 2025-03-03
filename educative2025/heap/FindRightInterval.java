package package2;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindRightInterval {

  public int[] findRightInterval(int[][] intervals) {

    PriorityQueue<int[]> startMinHeap = new PriorityQueue<>((a,b) -> (a[0]-b[0]));
    PriorityQueue<int[]> endMinHeap = new PriorityQueue<>((a,b) -> (a[0]-b[0]));

    int i = 0;
    for(int[] interval: intervals) {
      int start = interval[0];
      int end = interval[1];
      startMinHeap.offer(new int[]{start, i});
      endMinHeap.offer(new int[]{end, i});
      i++;
    }

    int[] result = new int[intervals.length];
    Arrays.fill(result, -1);

    while(!endMinHeap.isEmpty()) {
      int[] end = endMinHeap.poll();
      while(!startMinHeap.isEmpty() && startMinHeap.peek()[0] < end[0]) {
        startMinHeap.poll();
      }
      if(!startMinHeap.isEmpty()) {
        result[end[1]] = startMinHeap.peek()[1];
      }
    }

    return result;

  }
}
