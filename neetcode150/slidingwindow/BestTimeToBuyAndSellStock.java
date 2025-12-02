package slidingwindow;

public class BestTimeToBuyAndSellStock {

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

}
