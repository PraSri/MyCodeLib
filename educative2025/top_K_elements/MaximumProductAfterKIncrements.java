package top_K_elements;

import java.util.*;

public class MaximumProductAfterKIncrements {

    // Function to maximize product after k increments
    public int maximumProduct(int[] nums, int k) {
        int MOD = 1_000_000_007;

        // Min-heap using PriorityQueue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all elements to the heap
        for (int num : nums) {
            minHeap.offer(num);
        }

        // Perform k increments on the smallest elements
        for (int i = 0; i < k; i++) {
            int smallest = minHeap.poll();
            minHeap.offer(smallest + 1);
        }

        // Compute the final product modulo 10^9 + 7
        long result = 1;
        while (!minHeap.isEmpty()) {
            result = (result * minHeap.poll()) % MOD;
        }

        return (int) result;
    }

    // Driver code
    public static void main(String[] args) {
        List<int[]> numsList = Arrays.asList(
                new int[]{3, 3, 3, 3, 0},
                new int[]{1, 2, 3},
                new int[]{0, 0, 0, 0, 0},
                new int[]{1, 5, 1, 1},
                new int[]{2, 2, 2, 2}
        );

        int[] kValues = {1, 3, 10, 3, 4};

        for (int i = 0; i < numsList.size(); i++) {
            int[] nums = numsList.get(i);
            int k = kValues[i];
            MaximumProductAfterKIncrements obj = new MaximumProductAfterKIncrements();
            System.out.printf("%d.\tnums = %s, k = %d\n", i + 1, Arrays.toString(nums), k);
            int result = obj.maximumProduct(nums, k);
            System.out.printf("\tMaximum Product: %d\n", result);
            System.out.println("-".repeat(100));
        }
    }
}
