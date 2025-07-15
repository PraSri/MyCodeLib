package top_K_elements;

import java.util.*;

public class FindTheKSumOfAnArray {
    public static long kSum(int[] nums, int k) {
        // Step 1: Calculate the maximum possible subsequence sum
        // and convert all elements to their absolute values (to represent potential losses)
        long maxSum = 0L;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxSum += nums[i];  // Add positive values to the maxSum
            } else {
                nums[i] = -nums[i];  // Convert negative values to positive for loss tracking
            }
        }

        // Step 2: Sort the array of absolute values to process smaller losses first
        Arrays.sort(nums);

        // Step 3: Initialize a min heap with a starting sum of 0 and index 0
        // The heap stores tuples of (current loss sum, index in nums)
        PriorityQueue<long[]> minHeap = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        minHeap.offer(new long[]{0L, 0L});  // current sum, index

        // Step 4: Generate the (k - 1) smallest loss sums using the heap
        for (int i = 0; i < k - 1; i++) {
            long[] top = minHeap.poll();
            long currentSum = top[0];
            int index = (int) top[1];

            if (index < nums.length) {
                // Push the sum that includes nums[index] (i.e., expand the current subset)
                minHeap.offer(new long[]{currentSum + nums[index], index + 1});

                if (index > 0) {
                    // Push the sum where the last included number is replaced by the current one
                    // This explores a new combination path in the subset space
                    minHeap.offer(new long[]{currentSum + nums[index] - nums[index - 1], index + 1});
                }
            }
        }

        // Step 5: The k-th largest subsequence sum is the max sum minus the (k-1)th smallest loss
        return maxSum - minHeap.peek()[0];
    }

    // Driver code
    public static void main(String[] args) {
        int[][] testNums = {
                {3, 3, 3, 3, 0},
                {1, 2, 3},
                {0, 0, 0, 0, 0},
                {1, 5, 1, 1},
                {2, 2, 2, 2}
        };

        int[] testKs = {1, 3, 10, 3, 4};

        for (int i = 0; i < testNums.length; i++) {
            System.out.println((i + 1) + ".\tInput: nums = " + Arrays.toString(testNums[i]) + ", k = " + testKs[i]);
            long result = kSum(testNums[i].clone(), testKs[i]);
            System.out.println("\tK-th Largest Subsequence Sum: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));

        }
    }
}
