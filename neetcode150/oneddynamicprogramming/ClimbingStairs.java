package oneddynamicprogramming;

/**
 * Min Cost Climbing Stairs (Easy)
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * <p>
 * Fibonacci Number (Easy)
 * https://leetcode.com/problems/fibonacci-number/
 * <p>
 * N-th Tribonacci Number (Easy)
 * https://leetcode.com/problems/n-th-tribonacci-number/
 * <p>
 * Minimum Rounds to Complete All Tasks (Medium)
 * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
 * <p>
 * Count Number of Ways to Place Houses (Medium)
 * https://leetcode.com/problems/count-number-of-ways-to-place-houses/
 * <p>
 * Number of Ways to Reach a Position After Exactly k Steps (Medium)
 * https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/
 * <p>
 * Count Ways To Build Good Strings (Medium)
 * https://leetcode.com/problems/count-ways-to-build-good-strings/
 * <p>
 * Frog Jump II (Medium)
 * https://leetcode.com/problems/frog-jump-ii/
 * <p>
 * Find Number of Ways to Reach the K-th Stair (Hard)
 * https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/
 * <p>
 * The Number of Ways to Make the Sum (Medium)
 * https://leetcode.com/problems/the-number-of-ways-to-make-the-sum/
 */

public class ClimbingStairs {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];

        // initialize with -1
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        return solve(n, dp);
    }

    private int solve(int n, int[] dp) {
        if (n == 0) return 1;
        if (n < 0) return 0;

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
        return dp[n];
    }

    /**
     * Min Cost Climbing Stairs
     * https://leetcode.com/problems/min-cost-climbing-stairs
     */
    public static class MinCostClimbingStairs {
    }

    /**
     * Fibonacci Number
     * https://leetcode.com/problems/fibonacci-number
     */
    public static class FibonacciNumber {
    }

    /**
     * N-th Tribonacci Number
     * https://leetcode.com/problems/n-th-tribonacci-number
     */
    public static class NThTribonacciNumber {
    }

    /**
     * Minimum Rounds to Complete All Tasks
     * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks
     */
    public static class MinimumRoundsToCompleteAllTasks {
    }

    /**
     * Count Number of Ways to Place Houses
     * https://leetcode.com/problems/count-number-of-ways-to-place-houses
     */
    public static class CountNumberOfWaysToPlaceHouses {
    }

    /**
     * Number of Ways to Reach a Position After Exactly k Steps
     * https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps
     */
    public static class NumberOfWaysToReachAPositionAfterExactlyKSteps {
    }

    /**
     * Count Ways To Build Good Strings
     * https://leetcode.com/problems/count-ways-to-build-good-strings
     */
    public static class CountWaysToBuildGoodStrings {
    }

    /**
     * Frog Jump II
     * https://leetcode.com/problems/frog-jump-ii
     */
    public static class FrogJumpIi {
    }

    /**
     * Find Number of Ways to Reach the K-th Stair
     * https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair
     */
    public static class FindNumberOfWaysToReachTheKThStair {
    }

    /**
     * The Number of Ways to Make the Sum
     * https://leetcode.com/problems/the-number-of-ways-to-make-the-sum
     */
    public static class TheNumberOfWaysToMakeTheSum {
    }
}
