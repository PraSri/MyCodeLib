package topological_sort;

import java.util.*;

public class LongestPathWithDifferentAdjacentCharacters {

    public int longestPath(int[] parent, String s) {
        int n = parent.length;

        // Step 1: Calculate the in-degree (number of children) for each node.
        int[] inDegree = new int[n];
        for (int node = 1; node < n; node++) {
            inDegree[parent[node]]++;
        }

        // Step 2: Initialize queue and longestChains array.
        int[][] longestChains = new int[n][2]; // Stores the two longest chains for each node.
        int longestPathLength = 1;
        Queue<Integer> queue = new LinkedList<>();

        // Step 3: Add all leaf nodes (in-degree = 0) to the queue.
        for (int node = 0; node < n; node++) {
            if (inDegree[node] == 0) {
                longestChains[node][0] = 1; // Leaf nodes have a chain length of 1.
                queue.offer(node); // Process leaf nodes first.
            }
        }

        // Step 4: Process nodes in topological order.
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            int par = parent[currentNode];

            // If the current node is not the root (it has a parent).
            if (par != -1) {
                // Calculate the longest chain from the current node.
                int longestChainFromCurrent = longestChains[currentNode][0];

                // Only update if characters are different between parent and child.
                if (s.charAt(currentNode) != s.charAt(par)) {
                    if (longestChainFromCurrent > longestChains[par][0]) {
                        longestChains[par][1] = longestChains[par][0];
                        longestChains[par][0] = longestChainFromCurrent;
                    } else if (longestChainFromCurrent > longestChains[par][1]) {
                        longestChains[par][1] = longestChainFromCurrent;
                    }
                }

                // Update the global longest path by considering both chains.
                longestPathLength = Math.max(longestPathLength, longestChains[par][0] + longestChains[par][1] + 1);

                // Decrease the in-degree of the parent and add it to the queue if it's now a leaf.
                inDegree[par]--;
                if (inDegree[par] == 0) {
                    longestChains[par][0]++;
                    queue.offer(par);
                }
            }
        }

        return longestPathLength;
    }
}
