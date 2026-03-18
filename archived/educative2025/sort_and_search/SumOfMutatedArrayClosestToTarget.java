package sort_and_search;

import java.util.Arrays;

public class SumOfMutatedArrayClosestToTarget {
    public static int findBestValue(int[] arr, int target) {
        // Step 1: Sort the array to process elements in increasing order
        Arrays.sort(arr);
        int n = arr.length;
        int remainingTarget = target;

        // Step 2: Iterate through the sorted array
        for (int i = 0; i < n; i++) {
            int num = arr[i];

            // Check if the remaining target can be distributed evenly among the remaining numbers
            if (remainingTarget <= num * (n - i)) {
                // Calculate the replacement value by dividing the remaining target by the remaining count
                double replacementValue = (double) remainingTarget / (n - i);

                // Handle tie cases: if the fractional part is exactly 0.5, choose the smaller integer
                if (replacementValue - (int) replacementValue == 0.5) {
                    return (int) replacementValue;
                }

                // Otherwise, round to the nearest integer
                return (int) Math.round(replacementValue);
            }

            // Subtract the current number from the remaining target since it's fully used
            remainingTarget -= num;
        }

        // Step 3: If the target exceeds the sum of the array, return the largest element in the array
        return arr[n - 1];
    }
}
