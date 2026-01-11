package oneddynamicprogramming;

import java.util.Arrays;

/***Minimum Cost For Tickets
 https://leetcode.com/problems/minimum-cost-for-tickets/

 Maximum Value of K Coins From Piles
 https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/

 Minimum Number of Operations to Convert Time
 https://leetcode.com/problems/minimum-number-of-operations-to-convert-time/

 Minimum Cost to Split an Array
 https://leetcode.com/problems/minimum-cost-to-split-an-array/

 Count of Sub-Multisets With Bounded Sum
 https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/

 Length of the Longest Subsequence That Sums to Target
 https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target/

 Minimum Number of Coins to be Added
 https://leetcode.com/problems/minimum-number-of-coins-to-be-added/

 Most Expensive Item That Can Not Be Bought
 https://leetcode.com/problems/most-expensive-item-that-can-not-be-bought/

 Inverse Coin Change
 https://leetcode.com/problems/inverse-coin-change/
 **/

public class CoinChange {

    int[] dp;

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;

        dp = new int[amount + 1];

        Arrays.fill(dp, -1);

        int min = dfs(coins, amount);

        return (min >= 1e9) ? -1 : min;
    }

    private int dfs(int[] coins, int amount) {
        if (dp[amount] != -1) {
            return dp[amount];
        }
        if (amount == 0) {
            return 0;
        }
        int res = (int) 1e9;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                res = Math.min(res, 1 + dfs(coins, amount - coin));
            }
        }
        dp[amount] = res;
        return res;
    }


    /**
     * Minimum Cost For Tickets
     * https://leetcode.com/problems/minimum-cost-for-tickets
     */
    public static class MinimumCostForTickets {
    }

    /**
     * Maximum Value of K Coins From Piles
     * https://leetcode.com/problems/maximum-value-of-k-coins-from-piles
     */
    public static class MaximumValueOfKCoinsFromPiles {
    }

    /**
     * Minimum Number of Operations to Convert Time
     * https://leetcode.com/problems/minimum-number-of-operations-to-convert-time
     */
    public static class MinimumNumberOfOperationsToConvertTime {
    }

    /**
     * Minimum Cost to Split an Array
     * https://leetcode.com/problems/minimum-cost-to-split-an-array
     */
    public static class MinimumCostToSplitAnArray {
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
     * Minimum Number of Coins to be Added
     * https://leetcode.com/problems/minimum-number-of-coins-to-be-added
     */
    public static class MinimumNumberOfCoinsToBeAdded {
    }

    /**
     * Most Expensive Item That Can Not Be Bought
     * https://leetcode.com/problems/most-expensive-item-that-can-not-be-bought
     */
    public static class MostExpensiveItemThatCanNotBeBought {
    }

    /**
     * Inverse Coin Change
     * https://leetcode.com/problems/inverse-coin-change
     */
    public static class InverseCoinChange {
    }
}
