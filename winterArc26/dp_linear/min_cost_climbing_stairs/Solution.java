package dp_linear.min_cost_climbing_stairs;

public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        int prev = 0; // min cost to reach i - 2
        int cur = 0;  // min cost to reach i - 1
        for (int i = 2; i <= cost.length; i++) {
            int next = Math.min(cur + cost[i - 1], prev + cost[i - 2]);
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
