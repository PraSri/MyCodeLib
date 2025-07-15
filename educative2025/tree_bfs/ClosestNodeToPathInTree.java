package tree_bfs;

import java.util.*;

public class ClosestNodeToPathInTree {

    public int[] closestNode(int n, int[][] edges, int[][] query) {
        // Construct the adjacency list to represent the tree
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            tree.get(u).add(v);  // Add edge u -> v
            tree.get(v).add(u);  // Add edge v -> u (since the graph is undirected)
        }

        List<Integer> answer = new ArrayList<>();  // Stores the final answer for each query

        for (int[] q : query) {
            int start = q[0], end = q[1], node = q[2];

            // Use BFS to find the path from 'start' to 'end'
            int[] parents = new int[n];             // Keeps track of each node's parent
            Arrays.fill(parents, -1);
            Queue<Integer> q1 = new LinkedList<>(); // Queue for BFS
            boolean[] visited = new boolean[n];     // Tracks visited nodes
            q1.offer(start);
            visited[start] = true;

            // Standard BFS loop to find path from start to end
            while (!q1.isEmpty()) {
                int curr = q1.poll();

                // Stop once 'end' is reached
                if (curr == end)
                    break;

                // Visit all unvisited neighbors and update their parents
                for (int nei : tree.get(curr)) {
                    if (!visited[nei]) {
                        visited[nei] = true;
                        parents[nei] = curr;  // Record how we reached 'nei'
                        q1.offer(nei);
                    }
                }
            }

            // Reconstruct the actual path from 'end' to 'start' using the parent pointers
            List<Integer> path = new ArrayList<>();
            int curr = end;
            while (curr != -1) {
                path.add(curr);             // Add current node to the path
                curr = parents[curr];       // Move to its parent
            }

            // The path is currently from end to start, so reverse it
            Collections.reverse(path);

            // Perform BFS from 'node' to find the shortest distance to all nodes
            int[] dist = new int[n];                // Distance array
            Arrays.fill(dist, Integer.MAX_VALUE);   // Initialized to infinity
            Queue<Integer> q2 = new LinkedList<>();
            dist[node] = 0;                         // Distance to itself is 0
            q2.offer(node);

            while (!q2.isEmpty()) {
                int u = q2.poll();

                // Visit all unvisited neighbors and update their distances
                for (int v : tree.get(u)) {
                    if (dist[v] == Integer.MAX_VALUE) {   // If 'v' is not visited yet
                        dist[v] = dist[u] + 1;            // Update its distance
                        q2.offer(v);                      // Enqueue for further processing
                    }
                }
            }

            // Find the node on the path with the smallest distance to 'node'
            int minDist = Integer.MAX_VALUE;
            int answerNode = -1;
            for (int p : path) {
                // If a shorter distance is found, or same distance but smaller node index
                if (dist[p] < minDist || (dist[p] == minDist && p < answerNode)) {
                    minDist = dist[p];
                    answerNode = p;
                }
            }

            // Append the closest node on the path to the result
            answer.add(answerNode);
        }

        // Convert result list to array
        return answer.stream().mapToInt(i -> i).toArray();
    }

    // Driver code
    public static void main(String[] args) {
        ClosestNodeToPathInTree sol = new ClosestNodeToPathInTree();

        int[] nArr = {3, 6, 7, 6, 9};

        int[][][] edgesArr = {
                {{0, 1}, {1, 2}},
                {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}},
                {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}},
                {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}},
                {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {2, 5}, {5, 6}, {6, 7}, {6, 8}}
        };

        int[][][] queryArr = {
                {{0, 2, 1}},
                {{1, 5, 2}, {2, 3, 4}},
                {{3, 4, 6}},
                {{0, 5, 3}},
                {{0, 4, 8}}
        };

        for (int i = 0; i < nArr.length; ++i) {
            System.out.println((i + 1) + ".\tn: " + nArr[i]);
            System.out.print("\tedges: ");
            System.out.println(Arrays.deepToString(edgesArr[i]));
            System.out.print("\tquery: ");
            System.out.println(Arrays.deepToString(queryArr[i]));

            int[] result = sol.closestNode(nArr[i], edgesArr[i], queryArr[i]);

            System.out.print("\n\tanswer: " + Arrays.toString(result));
            System.out.println("\n" + "-".repeat(100));
        }
    }
}