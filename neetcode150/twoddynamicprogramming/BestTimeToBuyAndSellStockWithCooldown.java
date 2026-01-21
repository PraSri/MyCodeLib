package twoddynamicprogramming;

/**
 * Best Time to Buy and Sell Stock (Easy)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * Best Time to Buy and Sell Stock II (Medium)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */

public class BestTimeToBuyAndSellStockWithCooldown {


    // Problem Recap (Short)
    //
    //Tumhare paas prices[] hai, jahan prices[i] = ith day ka price
    //
    //Tum buy ? sell ? cooldown (1 day) follow kar sakte ho
    //
    //Ek time pe sirf 1 stock hold kar sakte ho
    //
    //Goal: maximum profit


    public int maxProfit(int[] prices) {
        // return dfs(0, prices, true);
        int[][] dp = new int[prices.length + 1][2];
        for (int i = 0; i <= prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = -1;
            }
        }
        return dfsMemo(0, prices, 1, dp);
    }

    private int dfs(int i, int[] p, boolean buy) {
        if (i >= p.length) {
            return 0;
        }
        if (buy) {
            return Math.max((-p[i] + dfs(i + 1, p, false)), (0 + dfs(i + 1, p, true)));
        }
        return Math.max((p[i] + dfs(i + 2, p, true)), (0 + dfs(i + 1, p, true)));
    }

    //Har day pe decision lete ho: buy / sell / skip
    //
    //Cooldown handle kiya by i+2
    private int dfsMemo(int i, int[] p, int buy, int[][] dp) {
        if (i >= p.length) {
            return 0;
        }
        if (dp[i][buy] != -1) {
            return dp[i][buy];
        }
        if (buy == 1) {
            int profitWhenBuyAllowed = Math.max(
                    -p[i] + dfsMemo(i + 1, p, 0, dp),   // buy today toh next day sell kr skte ho sirf, toh set param buy = 0
                    0 + dfsMemo(i + 1, p, 1, dp)           // skip today toh kal bhi buy kr skte ho, toh set param buy = 1
            );
            return dp[i][buy] = profitWhenBuyAllowed;
        }
        // buy == 0
        // sell allowed
        //
        int profitWhenSellAllowed = Math.max(
                p[i] + dfsMemo(i + 2, p, 1, dp),   // sell today + cooldown(i.e. why i+2)
                dfsMemo(i + 1, p, 0, dp)          // skip today - Next day bhi sell allowed
        );
        return dp[i][buy] = profitWhenSellAllowed;
    }



    /**
     * Best Time to Buy and Sell Stock
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock
     */
    public static class BestTimeToBuyAndSellStock {
    }

    /**
     * Best Time to Buy and Sell Stock II
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
     */
    public static class BestTimeToBuyAndSellStockIi {
    }
}
