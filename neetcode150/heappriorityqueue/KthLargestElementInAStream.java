package heappriorityqueue;

import java.util.*;

public class KthLargestElementInAStream {

    private final int k;
    private final PriorityQueue<Integer> minHeap;
    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for(int x: nums) {
            add(x);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if(minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

}
