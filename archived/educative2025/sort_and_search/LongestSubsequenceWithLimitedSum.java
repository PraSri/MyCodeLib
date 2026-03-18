package sort_and_search;

import java.util.*;

public class LongestSubsequenceWithLimitedSum {

    // Step 1: Define the answerQueries method
    public static int[] answerQueries(int[] nums, int[] queries) {
        // Step 1: Sort the array in ascending order
        Arrays.sort(nums);

        // Step 2: Compute prefix sums
        // Initialize the prefix_sum array with the same length as nums
        int[] prefixSum = new int[nums.length];
        // The first element of prefixSum is the same as the first element of nums
        prefixSum[0] = nums[0];
        // Compute the cumulative sum for the rest of the elements
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        // Step 3: Define a helper function to perform binary search
        // Perform binary search to find the maximum index where prefixSum[index] <= target
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            int index = binarySearch(prefixSum, q);
            answer[i] = index;
        }

        // Step 4: Return the array of answers for each query
        return answer;
    }

    // Binary search helper method
    public static int binarySearch(int[] prefixSum, int target) {
        int low = 0, high = prefixSum.length - 1;
        // Perform binary search to find the maximum index where prefixSum[index] <= target
        while (low <= high) {
            int mid = (low + high) / 2;
            if (prefixSum[mid] <= target) {
                low = mid + 1;  // Move low to find larger sums <= target
            } else {
                high = mid - 1;  // Move high to find smaller sums
            }
        }
        return low;  // 'low' will be the count of elements with sum <= target
    }

    public static void main(String[] args) {
        int[][] numsList = {
                {4, 5, 2, 1},
                {2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {10, 20, 30},
                {7, 3, 9, 2}
        };

        int[][] queriesList = {
                {3, 10, 21},
                {1},
                {10, 15, 5},
                {25, 50, 5},
                {5, 10, 20}
        };

        for (int i = 0; i < numsList.length; i++) {
            int[] nums = numsList[i];
            int[] queries = queriesList[i];
            int[] result = answerQueries(nums, queries);
            System.out.println((i + 1) + ".\tnums: " + Arrays.toString(nums));
            System.out.println("\tqueries: " + Arrays.toString(queries));
            System.out.println("\n\tResult: " + Arrays.toString(result));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}