package twoddynamicprogramming;

import java.util.Arrays;

// Minimum Cost to Merge Stones - https://leetcode.com/problems/minimum-cost-to-merge-stones/

public class BurstBalloons {

    //Direct order me balloons burst karna confusing hota hai, kyunki neighbors change hote rehte hain.
    //
    //Smart trick:
    //Instead of deciding kaunsa pehle burst kare, hum decide karte hain
    //
    //kaunsa LAST me burst hoga
    //
    //Last burst hone par:
    //
    //Uske left aur right neighbor fixed hote hain
    //
    //Coin calculation stable ho jaata hai

    public int maxCoins(int[] nums) {
        int n = nums.length;
        // Padding with 1s (Very Important)
        int[] a = new int[n + 2];
        a[0] = 1;
        a[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            a[i + 1] = nums[i];
        }
        //dp[i][j] = max coins we can get
        //           by bursting balloons from index i to j
        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(a, 1, a.length - 2, dp);
    }

    private int dfs(int[] a, int i, int j, int[][] dp) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        dp[i][j] = 0;
        //For range [i ... j], try each k as LAST balloon:
        for (int k = i; k <= j; k++) {
            int coins = a[i - 1] * a[k] * a[j + 1];
            //Hum maan rahe hain ki k balloon LAST me burst hoga in range [i..j]
            //
            //Us waqt:
            //
            //Left side ke balloons = [i .. k-1]
            //
            //Right side ke balloons = [k+1 .. j]
            //
            //Ye dono sides independent ho jaate hain
            //Balloon k ke left side ke sab balloons ko best possible way me burst karo
            //aur maximum coins laao
            //Balloon k ke right side ke sab balloons ko best way me burst karo
            coins += dfs(a, i, k - 1, dp) + dfs(a, k + 1, j, dp);
            //Total coins =
            //(last burst k coins)
            //+ (best coins from left side)
            //+ (best coins from right side)
            dp[i][j] = Math.max(dp[i][j], coins);
        }
        //dp[i][j] stores best answer for subarray i..j
        //Small ranges ? solved first
        //Bigger ranges ? reuse answers
        return dp[i][j];
    }


    /**
     * Minimum Cost to Merge Stones
     * https://leetcode.com/problems/minimum-cost-to-merge-stones/
     */
    public static class MinimumCostToMergeStones {
    }
}
