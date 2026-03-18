package graphs;

import java.util.*;

public class FindStarNodeOfGraph {
    public static int findCenter(int[][] edges) {

        // Get the first and second edges (connections between two nodes)
        int[] first = edges[0];
        int[] second = edges[1];

        // Check if the first node from the first edge is also in the second edge
        if (Arrays.stream(second).anyMatch(x -> x == first[0])) {
            return first[0]; // If it is, that means this node is the center of the star
        } else {
            return first[1]; // Otherwise, the second node from the first edge is the center
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] edgesList = {
                {{1, 2}, {2, 3}, {4, 2}},
                {{3, 5}, {3, 2}, {1, 3}, {3, 4}},
                {{4, 1}, {2, 4}, {4, 3}},
                {{6, 4}, {6, 3}, {2, 6}, {6, 1}, {5, 6}},
                {{1, 2}, {3, 1}, {1, 4}, {5, 1}, {1, 6}, {7, 1}}
        };

        for (int i = 0; i < edgesList.length; i++) {
            int[][] edges = edgesList[i];
            System.out.print((i + 1) + ".\tInput = ");
            for (int[] edge : edges) {
                System.out.print(Arrays.toString(edge) + " ");
            }
            System.out.println();
            System.out.println("\n\tCenter of Star Graph = " + findCenter(edges));
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
        }
    }
}
