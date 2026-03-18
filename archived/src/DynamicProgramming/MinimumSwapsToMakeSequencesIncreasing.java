package DynamicProgramming;

import java.util.Arrays;

// HARD : https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/

public class MinimumSwapsToMakeSequencesIncreasing {

    public static void main(String[] args) {
        int ans = minSwap(new int[]{1,3,5,4}, new int[]{1,2,3,7});
        System.out.println(ans);
    }

    static int[][] dp;
    public static int minSwap(int[] nums1, int[] nums2) {
        dp = new int[100005][2];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        int n = nums1.length;
        return dfs(nums1, nums2, 0, n, -1, -1, 0);
    }

    private static int dfs(int[] a, int[] b, int i, int n, int prev1, int prev2, int swap) {

        // base case
        if(i==n)
            return 0;

        if (dp[i][swap]!=-1)
            return dp[i][swap];

        int ans = Integer.MAX_VALUE;

        // no swap
        if(a[i]>prev1 && b[i]>prev2) {
            int q1 = dfs(a, b, i+1, n, a[i], b[i], 0);
            ans = Math.min(ans, q1);
        }

        // swap done
        if(a[i]>prev2 && b[i]>prev1) {
            int q2 = 1 + dfs(a, b, i+1, n, b[i], a[i], 1);
            ans = Math.min(ans, q2);
        }

        // cache

        return dp[i][swap] = ans;

    }
}
