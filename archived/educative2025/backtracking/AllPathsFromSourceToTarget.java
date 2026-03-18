package backtracking;

import java.util.*;

public class AllPathsFromSourceToTarget {

    // TC: O(2^n * n)

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int target = graph.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        dfs(graph, 0, new ArrayList<>(List.of(0)), result, target);
        return result;
    }

    private void dfs(int[][] graph, int src, ArrayList<Integer> path, List<List<Integer>> result, int target) {
        // base case
        if (src == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        // traverse neighbours
        for (int neighbour : graph[src]) {
            path.add(neighbour); // add neighbour to the current path
            dfs(graph, neighbour, path, result, target); // recursively call dfs for neighbour
            path.remove(path.size() - 1); // backtract: remove the last node to explore other paths
        }
    }

}
