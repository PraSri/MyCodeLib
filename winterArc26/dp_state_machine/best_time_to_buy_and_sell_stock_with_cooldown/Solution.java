package dp_state_machine.best_time_to_buy_and_sell_stock_with_cooldown;

public class Solution {

    public int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE / 2; // avoid overflow when adding a price
        int sold = 0;
        int rest = 0;
        for (int price : prices) {
            int prevSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(sold, rest);
    }
}
