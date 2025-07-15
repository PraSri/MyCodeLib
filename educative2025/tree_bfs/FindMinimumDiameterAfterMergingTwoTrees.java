package tree_bfs;

import java.util.*;

public class FindMinimumDiameterAfterMergingTwoTrees {

    // Build adjacency list from edge list
    private Map<Integer, List<Integer>> buildAdjList(int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        return adj;
    }

    // Perform BFS to find the farthest node and its distance from a given start node
    private int[] bfsFarthestNode(int start, Map<Integer, List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited.add(start);

        int farthestNode = start;
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int dist = current[1];

            if (dist > maxDistance) {
                maxDistance = dist;
                farthestNode = node;
            }

            for (int neighbor : adj.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[]{neighbor, dist + 1});
                }
            }
        }

        return new int[]{farthestNode, maxDistance};
    }

    // Get the diameter of a tree using double BFS
    private int getDiameter(Map<Integer, List<Integer>> adj) {
        int nodeA = bfsFarthestNode(0, adj)[0];
        return bfsFarthestNode(nodeA, adj)[1];
    }

    // Main function to compute the minimal diameter after connecting two trees
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        Map<Integer, List<Integer>> adj1 = buildAdjList(edges1);
        Map<Integer, List<Integer>> adj2 = buildAdjList(edges2);

        int d1 = getDiameter(adj1);
        int d2 = getDiameter(adj2);

        int mergeDiameter = (int) Math.ceil(d1 / 2.0) + (int) Math.ceil(d2 / 2.0) + 1;
        return Math.max(d1, Math.max(d2, mergeDiameter));
    }

    // Print a 2D edge list nicely
    private static String edgeListToString(int[][] edges) {
        StringBuilder sb = new StringBuilder();
        for (int[] edge : edges) {
            sb.append("[").append(edge[0]).append(",").append(edge[1]).append("] ");
        }
        return sb.toString().trim();
    }

    // Driver code
    public static void main(String[] args) {
        FindMinimumDiameterAfterMergingTwoTrees sol = new FindMinimumDiameterAfterMergingTwoTrees();

        int[][][] testCases1 = {
                {{0, 1}, {1, 2}},
                {{0, 1}, {1, 2}},
                {{0, 1}, {1, 2}, {2, 3}, {3, 4}},
                {{0, 1}, {1, 2}, {2, 3}},
                {{0, 1}, {1, 2}, {1, 3}}
        };

        int[][][] testCases2 = {
                {{0, 1}, {1, 2}},
                {{0, 1}},
                {{0, 1}, {1, 2}},
                {{0, 1}},
                {{0, 1}, {1, 2}, {1, 3}}
        };

        for (int i = 0; i < testCases1.length; i++) {
            int[][] edges1 = testCases1[i];
            int[][] edges2 = testCases2[i];

            int result = sol.minimumDiameterAfterMerge(edges1, edges2);

            System.out.println((i + 1) + ".     edges1  : " + edgeListToString(edges1));
            System.out.println("       edges2  : " + edgeListToString(edges2));
            System.out.println("\n       Minimum possible diameter: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}