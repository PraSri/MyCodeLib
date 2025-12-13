package graphs;

import java.util.*;

public class NumberOfConnectedComponentsInUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        int res = 0;
        for(int i = 0;i<n;i++) {
            if(!visited[i]) {
                dfs(adj, visited, i);
                res++;
            }
        }
        return res;
    }

    private void dfs(List<List<Integer>> adj, boolean[] visited, int i) {
        visited[i] = true;
        for(int nei: adj.get(i)) {
            if(!visited[nei]) {
                dfs(adj, visited, nei);
            }
        }
    }

}
