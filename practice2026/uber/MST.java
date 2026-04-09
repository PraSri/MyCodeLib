package uber;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MST {

    public int prims(int V, List<List<Pair>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        boolean[] visited = new boolean[V];
        pq.add(new Pair(0, 0));
        int sum = 0;
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            sum += curr.weight;
            for (Pair nei : adj.get(u)) {
                if (!visited[nei.node]) {
                    pq.add(nei);
                }
            }
        }
        return sum;
    }

    public int krushkal(int V, List<Edge> edges) {
        Collections.sort(edges, (a, b) -> a.wt - b.wt);
        DSU dsu = new DSU(V);
        int cost = 0;
        for (Edge e : edges) {
            if (dsu.find(e.u) != dsu.find(e.v)) {
                cost += e.wt;
                dsu.union(e.u, e.v);
            }
        }
        return cost;
    }

    static class Edge {
        int u, v, wt;

        Edge(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }
    }

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            }
            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[py] < rank[px]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    class Pair {
        int node, weight;

        Pair(int node, int w) {
            this.node = node;
            this.weight = w;
        }
    }

}
