package twoddynamicprogramming;

import java.util.Arrays;

/****Maximum Value of K Coins From Piles (Hard)
 https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/

 Number of Ways to Earn Points (Hard)
 https://leetcode.com/problems/number-of-ways-to-earn-points/

 Count of Sub-Multisets With Bounded Sum (Hard)
 https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/

 Length of the Longest Subsequence That Sums to Target (Medium)
 https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target/

 The Number of Ways to Make the Sum (Medium)
 https://leetcode.com/problems/the-number-of-ways-to-make-the-sum/

 Inverse Coin Change (Medium)
 https://leetcode.com/problems/inverse-coin-change/*/

public class CoinChangeII {

    //Tumhe:
    //
    //coins[] diya hai ? har coin ki unlimited supply
    //
    //amount diya hai ? target sum
    //
    //Task:
    //Batana hai kitne distinct combinations hain jisse amount ban sake.
    //
    //Distinct combinations ka matlab?
    //
    //Order matter nahi karta.

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        Arrays.sort(coins);
        for (int[] it : dp) {
            Arrays.fill(it, -1);
        }
        return dfsMemo(0, coins, amount, dp);
    }

    private int dfs(int i, int[] coins, int amount) {
        if (amount == 0) {
            return 1;
        }
        if (i >= coins.length) {
            return 0;
        }
        int res = 0;
        if (amount >= coins[i]) {
            res = dfs(i, coins, amount - coins[i]);
            res += dfs(i + 1, coins, amount);
        }

        return res;
    }

    private int dfsMemo(int i, int[] coins, int amount, int[][] dp) {
        if (amount == 0) {
            return 1;
        }
        if (i >= coins.length) {
            return 0;
        }
        if (dp[i][amount] != -1) {
            return dp[i][amount];
        }
        int res = 0;
        if (amount >= coins[i]) {
            res = dfsMemo(i, coins, amount - coins[i], dp);// pick
            res += dfsMemo(i + 1, coins, amount, dp);//skip
        }

        return dp[i][amount] = res;
    }


    /**
     * Maximum Value of K Coins From Piles
     * https://leetcode.com/problems/maximum-value-of-k-coins-from-piles
     */
    public static class MaximumValueOfKCoinsFromPiles {
    }

    /**
     * Number of Ways to Earn Points
     * https://leetcode.com/problems/number-of-ways-to-earn-points
     */
    public static class NumberOfWaysToEarnPoints {
    }

    /**
     * Count of Sub-Multisets With Bounded Sum
     * https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum
     */
    public static class CountOfSubMultisetsWithBoundedSum {
    }

    /**
     * Length of the Longest Subsequence That Sums to Target
     * https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target
     */
    public static class LengthOfTheLongestSubsequenceThatSumsToTarget {
    }

    /**
     * The Number of Ways to Make the Sum
     * https://leetcode.com/problems/the-number-of-ways-to-make-the-sum
     */
    public static class TheNumberOfWaysToMakeTheSum {
    }

    /**
     * Inverse Coin Change
     * https://leetcode.com/problems/inverse-coin-change
     */
    public static class InverseCoinChange {
    }
}
