import java.util.*;

/**
 * Dijkstra Shortest Path Template (Stale-Node Skip)
 *
 * Invariant:
 * 1. Distances are initialized to infinity (or Long.MAX_VALUE).
 * 2. Min-heap stores pairs: [currentDistance, nodeIndex].
 * 3. CRITICAL: Discard polled nodes where currDist > dist[u] (stale entries).
 * 4. Only valid for non-negative edge weights.
 */
public class DijkstraShortestPath {

    public static class Edge {
        int to;
        long weight;
        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static long[] shortestPath(int n, List<List<Edge>> adj, int source) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[source] = 0;

        // PriorityQueue comparing by distance using Long.compare to prevent overflow
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, source});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];

            // Invariant: stale node skip
            if (d > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                long w = edge.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{dist[v], v});
                }
            }
        }
        return dist;
    }
}
