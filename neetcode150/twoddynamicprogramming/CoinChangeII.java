package twoddynamicprogramming;

import java.util.Arrays;

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
        int[][] dp = new int[coins.length+1][amount+1];
        Arrays.sort(coins);
        for(int[] it: dp) {
            Arrays.fill(it, -1);
        }
        return dfsMemo(0, coins, amount, dp);
    }

    private int dfs(int i, int[] coins, int amount) {
        if(amount == 0) {
            return 1;
        }
        if(i >= coins.length) {
            return 0;
        }
        int res = 0;
        if(amount >= coins[i]) {
            res = dfs(i, coins, amount - coins[i]);
            res += dfs(i+1, coins, amount);
        }

        return res;
    }

    private int dfsMemo(int i, int[] coins, int amount, int[][] dp) {
        if(amount == 0) {
            return 1;
        }
        if(i >= coins.length) {
            return 0;
        }
        if(dp[i][amount] != -1) {
            return dp[i][amount];
        }
        int res = 0;
        if(amount >= coins[i]) {
            res = dfsMemo(i, coins, amount - coins[i], dp);// pick
            res += dfsMemo(i+1, coins, amount, dp);//skip
        }

        return dp[i][amount] = res;
    }

}
