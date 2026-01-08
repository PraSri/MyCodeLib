package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Number of Connected Components in an Undirected Graph
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * <p>
 * Redundant Connection
 * https://leetcode.com/problems/redundant-connection/
 * <p>
 * Course Schedule
 * https://leetcode.com/problems/course-schedule/
 * <p>
 * Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/
 */

//?Graph me cycle nahi honi chahiye aur saare nodes ek hi component me connected hone chahiye.?

public class GraphValidTreeNC {

    public boolean validTree(int n, int[][] edges) {
        if (edges.length > n - 1) {
            return false;
        }
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            adj.get(parent).add(child);
            adj.get(child).add(parent);
        }
        Set<Integer> visited = new HashSet<>();

        if (!dfs(0, -1, visited, adj)) {
            return false;
        }

        return visited.size() == n;
    }

    private boolean dfs(int node, int parent, Set<Integer> visited,
                        List<List<Integer>> adj) {
        if (visited.contains(node)) {
            return false;
        }
        visited.add(node);
        for (int nei : adj.get(node)) {
            if (nei == parent) {
                continue;
            }
            if (!dfs(nei, node, visited, adj)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Number of Connected Components in an Undirected Graph
     * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
     */
    public static class NumberOfConnectedComponentsInAnUndirectedGraph {
    }

    /**
     * Redundant Connection
     * https://leetcode.com/problems/redundant-connection
     */
    public static class RedundantConnection {
    }

    /**
     * Course Schedule
     * https://leetcode.com/problems/course-schedule
     */
    public static class CourseSchedule {
    }

    /**
     * Course Schedule II
     * https://leetcode.com/problems/course-schedule-ii
     */
    public static class CourseScheduleIi {
    }
}
