package union_find.graph_valid_tree;

public class Solution {

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] e : edges) {
            int ra = find(parent, e[0]);
            int rb = find(parent, e[1]);
            if (ra == rb) {
                return false;
            }
            parent[ra] = rb;
        }
        return true;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
