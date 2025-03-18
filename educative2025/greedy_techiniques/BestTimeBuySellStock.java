class BestTimeBuySellStock {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0;i<prices.length;i++) {
            if(prices[i] < min) {
                min = prices[i]; 
            }
            if(prices[i] - min > res) {
                    res = prices[i] - min;
                }
        }
        return res;
    }
}
