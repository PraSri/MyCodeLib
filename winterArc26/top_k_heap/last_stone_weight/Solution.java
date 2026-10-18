package top_k_heap.last_stone_weight;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        while (maxHeap.size() > 1) {
            int y = maxHeap.poll();
            int x = maxHeap.poll();
            if (y != x) {
                maxHeap.offer(y - x);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}
