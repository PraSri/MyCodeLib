package sort_and_search;

import java.util.*;

public class RangeSumOfSortedSubarraySums {
    static int mod = (int) 1e9 + 7;

    public static int rangeSum(int[] nums, int n, int left, int right) {
        // Calculate the sum of the first 'right' subarray sums minus the first 'left-1' subarray sums
        long result = (sumOfFirstK(nums, n, right) - sumOfFirstK(nums, n, left - 1)) % mod;

        // Ensure non-negative result by applying modulo again
        return (int) ((result + mod) % mod);
    }

    private static long sumOfFirstK(int[] nums, int n, int k) {
        int minSum = Arrays.stream(nums).min().orElse(0); // Smallest sum in the array
        int maxSum = Arrays.stream(nums).sum(); // Largest sum in the array
        int TLeft = minSum, TRight = maxSum;

        // Perform binary search to find the smallest subarray sum threshold that contains at least 'k' subarrays
        while (TLeft <= TRight) {
            int mid = TLeft + (TRight - TLeft) / 2;
            if (countAndSum(nums, n, mid)[0] >= k) { // If there are at least 'k' subarrays with sum <= mid
                TRight = mid - 1; // Narrow down the search to the left half
            } else {
                TLeft = mid + 1; // Narrow down the search to the right half
            }
        }

        // Calculate the total sum of the first 'k' subarray sums
        long[] result = countAndSum(nums, n, TLeft);
        long count = result[0];
        long totalSum = result[1];

        // Subtract the excess sum of subarrays beyond the kth subarray
        return totalSum - TLeft * (count - k);
    }

    private static long[] countAndSum(int[] nums, int n, int target) {
        int count = 0; // Total count of subarrays with sum <= target
        long currentSum = 0, totalSum = 0, windowSum = 0;

        // Iterate through each element of the nums array
        for (int j = 0, i = 0; j < n; ++j) {
            currentSum += nums[j]; // Add current element to the running sum
            windowSum += nums[j] * (j - i + 1); // Add the contribution of current element to window_sum

            // While the current sum exceeds the target, adjust the window size by moving 'i'
            while (currentSum > target) {
                windowSum -= currentSum; // Remove the current window sum
                currentSum -= nums[i++]; // Remove the element at the left pointer
            }

            // Count the number of valid subarrays in the current window
            count += j - i + 1;
            totalSum += windowSum; // Add the current window sum to total_sum
        }
        return new long[]{count, totalSum};
    }

    public static void main(String[] args) {
        int[][] numsArray = {
                {1, 2, 3, 4},
                {3, 7, 2},
                {5},
                {8, 1, 4, 2},
                {10, 20, 30}
        };
        int[] nArray = {4, 3, 1, 4, 3};
        int[] leftArray = {1, 2, 1, 4, 1};
        int[] rightArray = {5, 6, 1, 8, 4};

        for (int idx = 0; idx < numsArray.length; idx++) {
            int[] nums = numsArray[idx];
            int n = nArray[idx];
            int left = leftArray[idx];
            int right = rightArray[idx];

            int result = rangeSum(nums, n, left, right);
            System.out.println((idx + 1) + ".\tnums: " + Arrays.toString(nums));
            System.out.println("\tn: " + n + ", left: " + left + ", right: " + right);
            System.out.println("\tRange sum of subarrays: " + result);
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}