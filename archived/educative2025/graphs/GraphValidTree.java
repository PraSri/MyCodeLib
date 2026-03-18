package graphs;

import java.util.*;

public class GraphValidTree {


    public static boolean validTree(int n, int[][] edges) {
        // Check if n - 1 edges exist
        if (edges.length != (n - 1)) {
            return false;
        }

        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacency.add(new ArrayList<>());
        }

        // Populate adjacency list with all the connected nodes
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            adjacency.get(x).add(y);
            adjacency.get(y).add(x);
        }

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        visited.add(0);
        stack.push(0);

        // do dfs -> TC=O(n) because each node is visited only once
        while (!stack.isEmpty()) {
            int node = stack.pop();

            // Iterate over the neighbors of the popped node
            for (int neighbor : adjacency.get(node)) {
                if (!visited.contains(neighbor)) {

                    // Add a neighbor to the visited set and stack if it doesn't already exist in the set
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }

        return visited.size() == n;
    }

    // Driver code
    public static void main(String[] args) {
        int[] n = {3, 4, 5, 5, 6};
        int[][][] edges = {{{0, 1}, {0, 2}, {1, 2}}, {{0, 1}, {0, 2}, {0, 3}}, {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {3, 4}}, {{0, 1}, {0, 2}, {0, 3}, {3, 4}}, {{0, 1}, {0, 2}, {1, 3}, {2, 4}, {0, 5}}};

        for (int i = 0; i < n.length; i++) {
            System.out.println((i + 1) + ". n = " + n[i]);
            System.out.println("   Edges = " + Arrays.deepToString(edges[i]));
            System.out.println("   Is the given graph a valid tree: " + validTree(n[i], edges[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}


