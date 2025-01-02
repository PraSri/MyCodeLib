package leetcode2024;

import java.util.*;

public class LongestIncreasingSubsequence {

  public static void main(String[] args) {

    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    System.out.println(lis.lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println(lis.lis(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));

    System.out.println(lis.printLis(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println(lis.lisBinarySearch(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));


  }

  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n + 1];
    Arrays.stream(dp).forEach(i -> Arrays.fill(i, -1));
    int curr = 0;
    int prev = -1;
    return len(nums, curr, prev, dp);
  }

  private int len(int[] nums, int curr, int prev, int[][] dp) {

    //  base case
    if (curr == nums.length) {
      return 0;
    }

    if (dp[curr][prev + 1] != -1) {
      return dp[curr][prev + 1];
    }

    // not pick
    int ex = len(nums, curr + 1, prev, dp);
    int in = Integer.MIN_VALUE;
    // pick
    if (prev == -1 || nums[curr] > nums[prev]) {
      in = 1 + len(nums, curr + 1, curr, dp);
    }

    return dp[curr][prev + 1] = Math.max(ex, in);

  }

  private int lis(int[] a) {
    int n = a.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int max = 1;
    for (int i = 0; i < n; i++) {
      for (int prev = 0; prev < i; prev++) {
        if (a[prev] < a[i]) {
          dp[i] = Math.max(dp[i], 1 + dp[prev]);
        }
      }
      max = Math.max(dp[i], max);
    }
    return max;
  }

  private int printLis(int[] a) {
    int n = a.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int[] hash = new int[n];
    Arrays.fill(hash, 1);
    for (int i = 0; i < n; i++) {
      hash[i] = i;
      for (int prev = 0; prev < i; prev++) {
        if (a[prev] < a[i] && 1 + dp[prev] > dp[i]) {
          dp[i] = 1 + dp[prev];
          hash[i] = prev;
        }
      }
    }
    int ans = -1;
    int lastIndex = -1;
    for (int i = 0; i < n; i++) {
      if (dp[i] > ans) {
        ans = dp[i];
        lastIndex = i;
      }
    }
    List<Integer> temp = new ArrayList<>();
    temp.add(a[lastIndex]);
    while (hash[lastIndex] != lastIndex) {
      lastIndex = hash[lastIndex];
      temp.add(a[lastIndex]);
    }

    Collections.reverse(temp);

    System.out.println(temp);

    return ans;
  }

  private int lisBinarySearch(int[] a) {
    int n = a.length;
    List<Integer> temp = new ArrayList<>();
    temp.add(a[0]);
    int len = 1;
    for (int i = 1; i < n; i++) {
      if (a[i] > temp.get(temp.size() - 1)) {
        temp.add(a[i]);
        len++;
      } else {
        int ind = Collections.binarySearch(temp, a[i]);
        if (ind < 0) {
          ind = -ind - 1;
        }
        temp.set(ind, a[i]);
      }
    }
    return len;
  }

}
