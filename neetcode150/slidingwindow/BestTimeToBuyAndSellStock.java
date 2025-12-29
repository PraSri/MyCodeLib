package slidingwindow;

public class BestTimeToBuyAndSellStock {

    /**
     * Maximum Subarray
     * <a href="https://leetcode.com/problems/maximum-subarray/">...</a>
     * <p>
     * Best Time to Buy and Sell Stock II
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/">...</a>
     * <p>
     * Best Time to Buy and Sell Stock III
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/">...</a>
     * <p>
     * Best Time to Buy and Sell Stock IV
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/">...</a>
     * <p>
     * Best Time to Buy and Sell Stock with Cooldown
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">...</a>
     * <p>
     * Sum of Beauty in the Array
     * <a href="https://leetcode.com/problems/sum-of-beauty-in-the-array/">...</a>
     * <p>
     * Maximum Difference Between Increasing Elements
     * <a href="https://leetcode.com/problems/maximum-difference-between-increasing-elements/">...</a>
     * <p>
     * Maximum Profit From Trading Stocks
     * <a href="https://leetcode.com/problems/maximum-profit-from-trading-stocks/">...</a>
     * <p>
     * Best Time to Buy and Sell Stock V
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/">...</a>
     */

    //Input: prices = [10,1,5,6,7,1]
    //
    //Output: 6
    public int maxProfit(int[] prices) {

        int l = 0, r = l + 1;

        int maxP = 0;

        while (r < prices.length) {

            if (prices[l] < prices[r]) {

                int profit = prices[r] - prices[l];
                maxP = Math.max(maxP, profit);

            } else {

                l = r;

            }

            r++;
        }
        return maxP;
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-subarray/">LeetCode - Maximum Subarray</a>
     */
    public static class MaximumSubarray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/">LeetCode - Best Time to Buy and Sell Stock II</a>
     */
    public static class BestTimeToBuyAndSellStockII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/">LeetCode - Best Time to Buy and Sell Stock III</a>
     */
    public static class BestTimeToBuyAndSellStockIII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/">LeetCode - Best Time to Buy and Sell Stock IV</a>
     */
    public static class BestTimeToBuyAndSellStockIV {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">LeetCode - Best Time to Buy and Sell Stock with Cooldown</a>
     */
    public static class BestTimeToBuyAndSellStockWithCooldown {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/sum-of-beauty-in-the-array/">LeetCode - Sum of Beauty in the Array</a>
     */
    public static class SumOfBeautyInTheArray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-difference-between-increasing-elements/">LeetCode - Maximum Difference Between Increasing Elements</a>
     */
    public static class MaximumDifferenceBetweenIncreasingElements {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-profit-from-trading-stocks/">LeetCode - Maximum Profit From Trading Stocks</a>
     */
    public static class MaximumProfitFromTradingStocks {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/">LeetCode - Best Time to Buy and Sell Stock V</a>
     */
    public static class BestTimeToBuyAndSellStockV {
        // placeholder
    }
}
