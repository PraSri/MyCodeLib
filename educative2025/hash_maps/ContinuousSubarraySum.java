package hash_maps;

import java.util.*;

public class ContinuousSubarraySum {

    /**
     * This algorithm’s essence is identifying a subarray in nums whose sum is a multiple of k. We accomplish this by using cumulative sums and their remainders when divided by k. A hash map helps us quickly check if a particular remainder has been encountered before, allowing us to detect subarrays with the desired property.
     * <p>
     * Here’s why remainders are essential: when we divide a cumulative sum by k, the remainder reveals the part that isn’t divisible by k. If two cumulative sums yield the same remainder when divided by k, then the difference between these cumulative sums is divisible by k. This indicates that the subarray between these two indices has a sum that’s a multiple of k.
     * <p>
     * For instance, if we have cumulative sums at indices i and j where
     * ?
     * <
     * ?
     * i<j
     * , and the remainder at index i matches the remainder at index j, then:
     * <p>
     * cumulativeSum
     * [
     * ?
     * ]
     * ?
     * cumulativeSum
     * [
     * ?
     * ]
     * is divisible by
     * ?
     * .
     * cumulativeSum[j]?cumulativeSum[i] is divisible by k.
     * This difference equals the sum of the subarray between i + 1 and j, confirming that the subarray’s sum is a multiple of k.
     */

    public boolean checkSubarraySum(ArrayList<Integer> nums, int k) {

        // Initialize a map to store the first occurrence of each remainder
        Map<Integer, Integer> remainderMap = new HashMap<>();

        remainderMap.put(0, -1); // Remainder 0 with index -1 handles cases where the sum itself is a multiple of k

        int cumulativeSum = 0;

        for (int i = 0; i < nums.size(); i++) {
            cumulativeSum += nums.get(i);

            // Compute the remainder of the cumulative sum with k
            int remainder = cumulativeSum % k;

            // Adjust remainder to handle negative values if k is negative
            if (remainder < 0) remainder += k;

            // Check if the remainder has been seen before
            if (remainderMap.containsKey(remainder)) {
                // Ensure the subarray length is at least 2
                if (i - remainderMap.get(remainder) > 1) {
                    System.out.println(remainderMap);
                    return true;
                }
            } else {
                // Store the first occurrence of the remainder
                remainderMap.put(remainder, i);
            }
        }
        System.out.println(remainderMap);
        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum solution = new ContinuousSubarraySum();
        // Test cases with arrays of integers and their respective k values
        ArrayList<ArrayList<Integer>> testCases = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(23, 2, 4, 6, 7)),
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                new ArrayList<>(Arrays.asList(5, 0, 0, 3)),
                new ArrayList<>(Arrays.asList(0, 1)),
                new ArrayList<>(Arrays.asList(7, 3, 2, 4, 9))
        ));
        int[] kValues = {6, 7, 3, 7, 6};

        for (int i = 0; i < testCases.size(); i++) {
            ArrayList<Integer> nums = testCases.get(i);
            int k = kValues[i];
            boolean result = solution.checkSubarraySum(nums, k);

            // Display the results
            System.out.print((i + 1) + "\tnums: " + nums + "\n\tk: " + k + "\n\tResult: ");
            System.out.println(result ? "True" : "False");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
