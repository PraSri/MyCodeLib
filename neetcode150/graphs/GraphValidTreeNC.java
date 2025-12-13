package graphs;

import java.util.*;

public class GraphValidTreeNC {

        public boolean validTree(int n, int[][] edges) {
            if(edges.length > n-1) {
                return false;
            }
            List<List<Integer>> adj = new ArrayList<>();
            for(int i = 0;i<n;i++) {
                adj.add(new ArrayList<>());
            }
            for(int[] edge: edges) {
                int parent = edge[0];
                int child = edge[1];
                adj.get(parent).add(child);
                adj.get(child).add(parent);
            }
            Set<Integer> visited = new HashSet<>();

            if(!dfs(0, -1, visited, adj)) {
                return false;
            }

            return visited.size() == n;
        }

        private boolean dfs(int node, int parent, Set<Integer> visited,
                            List<List<Integer>> adj) {
            if(visited.contains(node)) {
                return false;
            }
            visited.add(node);
            for(int nei: adj.get(node)) {
                if(nei == parent) {
                    continue;
                }
                if(!dfs(nei, node, visited, adj)) {
                    return false;
                }
            }
            return true;
        }

}
