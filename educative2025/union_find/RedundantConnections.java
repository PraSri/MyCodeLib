package union_find;

public class RedundantConnections {

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind connections = new UnionFind(edges.length);
        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            if (!connections.union(v1, v2)) {
                return edge;
            }
        }
        return new int[0];
    }

    public static class UnionFind {
        public int[] parent;
        public int[] rank;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int v) {
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        public boolean union(int v1, int v2) {
            int p1 = find(v1);
            int p2 = find(v2);
            if (p1 == p2) return false;
            else if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
                rank[p1] = rank[p1] + rank[p2];
            } else {
                parent[p1] = p2;
                rank[p2] = rank[p1] + rank[p2];
            }
            return true;
        }
    }

}
