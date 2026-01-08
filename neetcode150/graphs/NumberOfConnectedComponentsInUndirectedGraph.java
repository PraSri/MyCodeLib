package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph Valid Tree
 * https://leetcode.com/problems/graph-valid-tree/
 * <p>
 * LeetCode
 * <p>
 * Number of Provinces
 * https://leetcode.com/problems/number-of-provinces/
 */

public class NumberOfConnectedComponentsInUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        // adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        // track visited
        boolean[] visited = new boolean[n];

        // fill graph
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // result tracker
        int res = 0;
        for (int i = 0; i < n; i++) {
            // not visited node pe dfs chalao
            if (!visited[i]) {
                dfs(adj, visited, i);
                res++;
            }
        }
        return res;
    }

    private void dfs(List<List<Integer>> adj, boolean[] visited, int i) {
        visited[i] = true;
        for (int nei : adj.get(i)) {
            if (!visited[nei]) {
                dfs(adj, visited, nei);
            }
        }
    }


    /**
     * Graph Valid Tree
     * https://leetcode.com/problems/graph-valid-tree
     */
    public static class GraphValidTree {
    }

    /**
     * LeetCode
     */
    public static class Leetcode {
    }

    /**
     * Number of Provinces
     * https://leetcode.com/problems/number-of-provinces
     */
    public static class NumberOfProvinces {
    }
}
