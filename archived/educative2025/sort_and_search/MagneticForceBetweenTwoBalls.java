package sort_and_search;

import java.util.Arrays;
import java.util.*;

public class MagneticForceBetweenTwoBalls {

    // Helper function to determine if it’s possible to place m balls such that
    // the minimum force between any two balls is at least x
    private static boolean canPlaceBalls(int x, int[] position, int m) {
        // Place the first ball at the first position
        int prev = position[0];

        // Initialize a counter for balls placed
        int balls = 1;

        // Iterate through the array
        for (int i = 1; i < position.length && balls < m; ++i) {
            int curr = position[i];

            // Check if we can place the ball at the current position
            if (curr - prev >= x) {
                balls += 1;

                // Update the last placed ball’s position
                prev = curr;
            }
        }

        // If all m balls are placed
        return balls == m;
    }

    public static int maxDistance(int[] position, int m) {
        // Sort the array
        Arrays.sort(position);

        // Variable to store the maximum possible minimum distance between balls
        int force = 0;

        // Define the binary search range
        int low = 1;
        int high = (position[position.length - 1] - position[0]) / (m - 1);

        // Perform binary search
        while (low <= high) {
            // Calculate the midpoint
            int mid = (low + high) / 2;

            // Check if we can place all balls having a gap at least mid
            if (canPlaceBalls(mid, position, m)) {
                force = mid;

                // Discard the left half search space
                low = mid + 1;
            } else {
                // Discard the right half search space
                high = mid - 1;
            }
        }

        return force;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] arrs = {{1, 2, 3, 7, 11}, {9, 8, 7, 3}, {1, 3, 7, 9, 14}, {1000, 1}, {5, 10, 15, 20, 25, 30}};
        int[] ms = {2, 3, 5, 2, 4};

        for (int i = 0; i < arrs.length; ++i) {
            System.out.print(i + 1 + ".\tposition: [");
            for (int j = 0; j < arrs[i].length; ++j) {
                System.out.print(arrs[i][j]);
                if (j < arrs[i].length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("\tk: " + ms[i]);
            System.out.println("\n\tMagnetic force: " + maxDistance(arrs[i], ms[i]));
            System.out.println(String.join("", Collections.nCopies(100, "-")));
        }
    }
}



