package intervals.minimum_interval_to_include_each_query;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Integer[] order = new Integer[queries.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(queries[a], queries[b]));
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // {size, right}
        int[] answer = new int[queries.length];
        int i = 0;
        for (int qi : order) {
            int q = queries[qi];
            while (i < intervals.length && intervals[i][0] <= q) {
                heap.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][1]});
                i++;
            }
            while (!heap.isEmpty() && heap.peek()[1] < q) {
                heap.poll();
            }
            answer[qi] = heap.isEmpty() ? -1 : heap.peek()[0];
        }
        return answer;
    }
}
