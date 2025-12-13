package oneddynamicprogramming;

import java.util.Arrays;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {

        int[] memo = new int[cost.length];

        Arrays.fill(memo, -1);

        return Math.min(
                solve(cost, 0, memo),
                solve(cost, 1, memo)
        );
    }

    private int solve(int[] cost, int i, int[] memo) {
        if (i >= cost.length) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        memo[i] = cost[i] + Math.min(
                solve(cost, i + 1, memo),
                solve(cost, i + 2, memo)
        );

        return memo[i];
    }

}
