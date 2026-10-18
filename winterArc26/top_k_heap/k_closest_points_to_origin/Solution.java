package top_k_heap.k_closest_points_to_origin;

import java.util.PriorityQueue;

public class Solution {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(dist(b), dist(a)));
        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.toArray(new int[0][]);
    }

    private static long dist(int[] p) {
        return (long) p[0] * p[0] + (long) p[1] * p[1];
    }
}
