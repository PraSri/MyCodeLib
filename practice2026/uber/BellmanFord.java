package uber;

import java.util.Arrays;
import java.util.List;

public class BellmanFord {
    public int[] bellmanFord(int V, List<Edge> edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.wt < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.wt;
                }
            }
        }

        // detect negative cycle
        for (Edge e : edges) {
            if (dist[e.u] != Integer.MAX_VALUE &&
                    dist[e.u] + e.wt < dist[e.v]) {
                System.out.println("negative cycle exsits");
            }
        }

        return dist;
    }

    static class Edge {
        int u, v, wt;

        Edge(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }
    }
}
