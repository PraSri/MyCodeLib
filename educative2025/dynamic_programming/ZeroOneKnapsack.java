package dynamic_programming;

import java.util.Arrays;

public class ZeroOneKnapsack {

    // optimized approach

    public static int findMaxKnapsackProfit(int capacity, int[] weights, int[] values) {
        int n = weights.length;

        // previous (i-1)th row which will be used to fill up the current ith row
        int[] dp = new int[capacity + 1];
        Arrays.fill(dp, 0);

        // current ith row that will use the values of the previous (i-1)th row to fill itself
        int[] temp = new int[capacity + 1];
        Arrays.fill(temp, 0);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    temp[j] = Math.max(values[i - 1] + dp[j - weights[i - 1]], dp[j]);
                } else {
                    temp[j] = dp[j];
                }
            }

            // Setting the (i-1)th row equal to the ith row
            // When the inner loop ends all the sub-problems till ith item have been solved
            // can we do better? there is a cost in cloning the array
            // Interestingly, in the single-array solution, the values of the previous row have not yet been overwritten,
            // and instead of needing to look them up from a copy of this row, we can simply use the value of the cell itself.
            dp = temp.clone();
        }

        return dp[capacity];
    }

    public static int findMaxKnapsackProfitWithSingleArray(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            dp[i] = 0;
        }

        // We can do one final optimization. Instead of running the inner loop all the way to
        //0
        //0
        // and checking every time to see whether the current item weighs more than the current capacity,
        // we can simply stop the inner loop as soon as this condition fails.
        for (int i = 0; i < n; i++) {
            for (int j = capacity; j >= 0; j--) {
                dp[j] = Math.max(values[i] + dp[j - weights[i]], dp[j]);
            }
        }

        return dp[capacity];
    }

    public static int findMaxKnapsackProfitMostOptimized(int capacity, int [] weights, int [] values) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            dp[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nComputing solutions for " + (i + 1) + " item(s):");
            for (int j = capacity; j >= weights[i]; j--) {
                System.out.println("\tat capacity " + j + ", max of: " + dp[j] + " and " + (values[i] + dp[j- weights[i]]));
                dp[j] = Math.max(values[i] + dp[j - weights[i]], dp[j]);
            }

            System.out.println("Solutions for " + (i + 1) + " item(s): " + Arrays.toString(dp));
        }

        return dp[capacity];
    }

}
