package shortest_path.network_delay_time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] t : times) {
            graph.get(t[0]).add(new int[]{t[1], t[2]});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        heap.offer(new int[]{k, 0});
        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            int u = top[0];
            if (top[1] > dist[u]) {
                continue;
            }
            for (int[] edge : graph.get(u)) {
                int nd = dist[u] + edge[1];
                if (nd < dist[edge[0]]) {
                    dist[edge[0]] = nd;
                    heap.offer(new int[]{edge[0], nd});
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, dist[i]);
        }
        return max;
    }
}
