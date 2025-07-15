package graphs;

import java.util.*;

public class LongestCycleInAGraph {

    public int longestCycle(int[] edges) {
        int n = edges.length;

        // Tracks the global timestamp at which each node was first visited.
        // -1 indicates the node hasn't been visited yet.
        int[] visitedTime = new int[n];
        Arrays.fill(visitedTime, -1);

        int time = 0; // Global timestamp counter
        int maxCycle = -1; // To keep track of the maximum cycle length found

        // Start a traversal from each node
        for (int i = 0; i < n; i++) {
            // Skip nodes we've already processed in a previous traversal
            if (visitedTime[i] != -1) {
                continue;
            }

            // Temporary map to store the local timestamp of each node visited
            // during the current traversal starting from node i
            Map<Integer, Integer> nodeToTime = new HashMap<>();

            int current = i; // Start from node i

            // Traverse through the graph as long as:
            // - We haven't reached a dead end (current != -1)
            // - The node hasn't been globally visited yet
            while (current != -1 && visitedTime[current] == -1) {
                // Mark the current node with the current global time
                nodeToTime.put(current, time);
                visitedTime[current] = time;
                time++;

                // Move to the next node in the path
                current = edges[current];

                // If we encounter a node we've already seen in this traversal,
                // we've found a cycle!
                if (nodeToTime.containsKey(current)) {
                    // Calculate the length of the cycle
                    int cycleLength = time - nodeToTime.get(current);

                    // Update the maximum cycle length found so far
                    maxCycle = Math.max(maxCycle, cycleLength);
                    break; // No need to go further in this path
                }
            }
        }

        return maxCycle;
    }

    // Driver method
    public static void main(String[] args) {
        int[][] testCases = {
            {3, 3, 4, 2, 3},
            {2, -1, 3, 1},
            {1, 2, 3, 4, 0},
            {2, -1, 3, 4, 5, 3},
            {1, 2, 3, 4, 5, 6, 3}
        };

        LongestCycleInAGraph solution = new LongestCycleInAGraph();
        for (int i = 0; i < testCases.length; i++) {
            int[] edges = testCases[i];
            System.out.print((i + 1) + ".\tedges: [");
            for (int j = 0; j < edges.length; j++) {
                System.out.print(edges[j]);
                if (j != edges.length - 1) System.out.print(", ");
            }
            System.out.println("]");

            int output = solution.longestCycle(edges);
            System.out.println("\tOutput: " + output);
            System.out.println(new String(new char[100]).replace('\0', '-'));

        }
    }
}