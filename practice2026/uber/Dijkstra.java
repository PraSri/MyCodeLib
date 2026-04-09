package uber;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public int[] dijkstra(int V, List<List<Pair>> adj, int src) {

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            for (Pair nei : adj.get(u)) {
                int v = nei.node;
                int wt = nei.dist;
                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        return dist;

    }

    static class Pair {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

    }
}
