package union_find.number_of_connected_components_in_undirected_graph;

public class Solution {

    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int components = n;
        for (int[] e : edges) {
            int ra = find(parent, e[0]);
            int rb = find(parent, e[1]);
            if (ra != rb) {
                parent[ra] = rb;
                components--;
            }
        }
        return components;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
