package oneddynamicprogramming;

import java.util.Arrays;

public class CoinChange {

    int[] dp;
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;

        dp = new int[amount+1];

        Arrays.fill(dp, -1);

        int min = dfs(coins, amount);

        return (min >= 1e9) ? -1 : min;
    }

    private int dfs(int[] coins, int amount) {
        if(dp[amount]!= - 1) {
            return dp[amount];
        }
        if(amount == 0) {
            return 0;
        }
        int res = (int) 1e9;
        for(int coin: coins) {
            if(amount - coin >= 0) {
                res = Math.min(res, 1 + dfs(coins, amount - coin));
            }
        }
        dp[amount] = res;
        return res;
    }

}
