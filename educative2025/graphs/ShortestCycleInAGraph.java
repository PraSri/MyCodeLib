package graphs;

import java.util.*;

public class ShortestCycleInAGraph {

    public int bfs(List<List<Integer>> graph, int start, int[] visited) {
        int n = visited.length;
        // Initialize distances to all nodes as -1 (meaning unvisited)
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        // Queue holds pairs of (current_node, parent_node)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, -1});

        // Mark the start node as visited and set its distance to 0
        visited[start] = 0;
        dist[start] = 0;

        // Variable to keep track of the shortest cycle length found during this BFS
        int minCycle = Integer.MAX_VALUE;

        // Perform BFS traversal
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int parent = curr[1];
            int currentDist = dist[node];

            // Explore all neighbors of the current node
            for (int neighbor : graph.get(node)) {
                // Skip the neighbor if it is the parent node to avoid trivial cycle
                if (neighbor == parent) continue;

                // If neighbor was visited before, a cycle is detected
                if (dist[neighbor] != -1) {
                    // Calculate cycle length: distance to neighbor + distance to current node + 1 (for connecting edge)
                    int cycleLength = dist[neighbor] + dist[node] + 1;
                    // Update shortest cycle length if this one is smaller
                    minCycle = Math.min(minCycle, cycleLength);
                } else {
                    // If neighbor not visited, set distance and add to queue for further exploration
                    dist[neighbor] = currentDist + 1;
                    queue.offer(new int[]{neighbor, node});
                }
            }
        }

        // Return the shortest cycle length found starting from this node
        return minCycle;
    }

    public int findShortestCycle(int n, int[][] edges) {
        // Create adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the adjacency list (undirected graph)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Keep track of visited nodes to avoid redundant BFS traversals
        int[] visited = new int[n];
        Arrays.fill(visited, -1);

        // Variable to track the global shortest cycle length
        int result = Integer.MAX_VALUE;

        // Run BFS from each unvisited node to find the shortest cycle in the graph
        for (int i = 0; i < n; i++) {
            if (visited[i] == -1) {
                int cycleLength = bfs(graph, i, visited);
                // Update global shortest cycle length if a smaller one is found
                result = Math.min(result, cycleLength);
            }
        }

        // Return -1 if no cycle found; otherwise return the shortest cycle length
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] testCases = {
                {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
                {{0, 1}},
                {{0, 1}, {2, 3}},
                {{0, 1}, {1, 2}, {2, 3}, {3, 0}, {1, 3}, {3, 4}, {4, 5}},
                {{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 3}, {2, 3}, {3, 6}, {6, 0}}
        };

        int[] nodeCounts = {4, 2, 5, 6, 7};

        for (int i = 0; i < testCases.length; i++) {
            int n = nodeCounts[i];
            int[][] edges = testCases[i];
            System.out.println((i + 1) + ".\tInput: n = " + n + ", edges = " + Arrays.deepToString(edges));
            ShortestCycleInAGraph sol = new ShortestCycleInAGraph();
            System.out.println("\tShortest Cycle Length = " + sol.findShortestCycle(n, edges));
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
        }
    }
}