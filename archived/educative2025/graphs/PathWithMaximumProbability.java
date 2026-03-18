package graphs;

import java.util.*;

public class PathWithMaximumProbability {

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        // Build the graph from the given edges and probabilities
        Map<Integer, List<double[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // For each edge, store the probability for both directions because the graph is undirected
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            double prob = succProb[i];

            // Add edge u -> v with probability succProb[i]
            graph.get(u).add(new double[]{v, prob});

            // Add edge v -> u with probability succProb[i]
            graph.get(v).add(new double[]{u, prob});
        }

        // Initialize a list to store the maximum probability for each node
        double[] maxProb = new double[n];

        // Set the probability of the start node to 1 as there 100% probability of reaching the starting node
        maxProb[start] = 1.0;

        // Use priority queue (heap) to always process the node with the highest probability
        // The heap stores tuples of (probability, node) so that the largest probability comes first
        // Start with the start node having probability 1.0
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        pq.offer(new double[]{1.0, start});

        while (!pq.isEmpty()) {

            // Pop the node with the highest probability from the priority queue
            double[] cur = pq.poll();

            double curProb = cur[0];
            int curNode = (int) cur[1];

            // If we have reached the end node, return the probability
            if (curNode == end) {
                return curProb;
            }

            // Explore neighbors of the current node
            for (double[] neighbor : graph.get(curNode)) {
                int nextNode = (int) neighbor[0];
                double edgeProb = neighbor[1];

                // Calculate the new probability for the neighbor by multiplying the current probability with the edge probability
                double newProb = curProb * edgeProb;

                // If the new probability is greater than the previously stored one, update it
                if (newProb > maxProb[nextNode]) {

                    // Update the probability for the neighbor
                    maxProb[nextNode] = newProb;

                    // Push the neighbor into the queue with the new probability
                    pq.offer(new double[]{newProb, nextNode});
                }
            }
        }

        // If there's no valid path from start to end, return 0.0 (no probability)
        return 0.0;
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] edges = {{{0, 1}, {1, 2}, {0, 2}}, {{0, 1}, {1, 2}, {0, 2}}, {{0, 1}, {1, 2}, {2, 3}, {0, 3}}, {{0, 1}, {1, 2}, {2, 3}, {3, 4}}, {{0, 1}}, {{0, 1}, {1, 2}}};

        double[][] succProb = {{0.5, 0.5, 0.2}, {0.5, 0.5, 0.3}, {0.5, 0.6, 0.7, 0.2}, {0.8, 0.6, 0.7, 0.9}, {0.1}, {0.5, 0.0}};

        int[] n = {3, 3, 4, 5, 2, 3};
        int[] start = {0, 0, 0, 0, 0, 0};
        int[] end = {2, 2, 3, 4, 1, 2};

        for (int i = 0; i < n.length; i++) {
            System.out.println((i + 1) + ".\tNumber of nodes: " + n[i]);
            System.out.println("\tEdges: " + Arrays.deepToString(edges[i]));
            System.out.println("\tSuccess Probabilities: " + Arrays.toString(succProb[i]));
            System.out.println("\tStart: " + start[i] + ", End: " + end[i]);

            double result = maxProbability(n[i], edges[i], succProb[i], start[i], end[i]);
            System.out.println("\n\tOutput: " + result);
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
        }
    }
}