package sort_and_search;

import java.util.*;


public class FindKthSmallestPairInArray {


    // Helper function to count the number of pairs that have a distance
    // less than or equal to a given distance d
    public static int countPairsWithDistance(int[] nums, int d) {
        // Initialize count with 0 to count the number of pairs
        int count = 0;

        // Initialize two pointers to use the sliding window technique to count the number of pairs
        // Iterate through the array using the right pointer
        for (int left = 0, right = 0; right < nums.length; ++right) {
            // Keep incrementing left until the distance between the elements at left and right
            // becomes less than or equal to d
            while (nums[right] - nums[left] > d)
                ++left;

            // Add the number of pairs to count
            count += right - left;
        }

        return count;
    }

    public static int smallestDistancePair(int[] nums, int k) {
        // Sort the array
        Arrays.sort(nums);

        // Define the binary search range using the variables low and high
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];

        // Perform binary search
        while (low < high) {
            // Calculate the midpoint, which represents a potential candidate for the k-th smallest distance
            int mid = (low + high) / 2;

            // For each midpoint, count how many pairs have a distance less than or equal to mid
            // using the helper function
            int count = countPairsWithDistance(nums, mid);

            // Adjust the binary search range
            if (count < k)
                low = mid + 1;
            else
                high = mid;
        }

        // Return low as the k-th smallest distance
        return low;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] arrs = {
                {1, 2, 3},
                {3, 3, 3},
                {1000, 0},
                {2, 4, 8, 12, 2},
                {500, 400, 300, 200, 100, 200, 300, 400, 500}
        };
        int[] ks = {1, 2, 1, 9, 36};

        for (int i = 0; i < arrs.length; ++i) {
            System.out.print((i + 1) + ".\tnums: [");
            for (int j = 0; j < arrs[i].length; ++j) {
                System.out.print(arrs[i][j]);
                if (j < arrs[i].length - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("\tk: " + ks[i]);
            System.out.println("\n\tSmallest distance: " + smallestDistancePair(arrs[i], ks[i]));
            System.out.println(String.join("", Collections.nCopies(100, "-")));
        }
    }
}
