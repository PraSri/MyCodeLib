package dp_unbounded_knapsack.coin_change;

import java.util.Arrays;

public class Solution {

    public int coinChange(int[] coins, int amount) {
        int inf = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a && dp[a - coin] + 1 < dp[a]) {
                    dp[a] = dp[a - coin] + 1;
                }
            }
        }
        return dp[amount] == inf ? -1 : dp[amount];
    }
}
