package heappriorityqueue;

import java.util.*;

public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int x: nums) {
            pq.add(x);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

}
