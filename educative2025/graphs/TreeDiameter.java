package graphs;

import java.util.*;


public class TreeDiameter {

    private static int dfs(int node, Map<Integer, Set<Integer>> graph, boolean[] visited, int[] diameter) {
        //Returns the longest path from 'node' to its leaf nodes
        visited[node] = true;
        int top1 = 0, top2 = 0; // Two longest distances

        for (int neighbor : graph.getOrDefault(node, new HashSet<>())) {
            if (!visited[neighbor]) {
                int dist = 1 + dfs(neighbor, graph, visited, diameter);

                if (dist > top1) {
                    top2 = top1;
                    top1 = dist;
                } else if (dist > top2) {
                    top2 = dist;
                }
            }
        }
        // Update diameter with the sum of the two longest distances
        diameter[0] = Math.max(diameter[0], top1 + top2);
        return top1;
    }

    public static int treeDiameter(int[][] edges) {
        // Edge case: No edges, diameter is 0
        if (edges.length == 0) return 0;

        // Build adjacency list representation
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] diameter = new int[1]; // Using array to store diameter as reference
        boolean[] visited = new boolean[edges.length + 1];

        // Start DFS from any node (0 in this case)
        dfs(0, graph, visited, diameter);
        return diameter[0];
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] testCases = {
                {{0, 1}},
                {{0, 1}, {0, 2}},
                {{0, 1}, {1, 2}, {1, 3}, {3, 4}, {4, 5}, {4, 6}},
                {{0, 1}, {1, 2}, {2, 3}, {3, 4}},
                {{0, 1}, {1, 2}, {1, 3}, {3, 4}, {3, 5}}
        };

        int i = 0;
        for (int[][] edges : testCases) {
            System.out.print(" " + (i + 1) + ".\tEdges: {");
            for (int[] edge : edges) {
                System.out.print("{" + edge[0] + ", " + edge[1] + "}");
            }
            System.out.println("}");
            System.out.println("\tTree Diameter: " + treeDiameter(edges));
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
            i++;
        }
    }
}
