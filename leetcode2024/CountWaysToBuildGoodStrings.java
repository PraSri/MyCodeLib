package leetcode2024;

public class CountWaysToBuildGoodStrings {

  long mod = 1000000007;

  public static void main(String[] args) {

  }

  public int countGoodStrings(int low, int high, int zero, int one) {

    int[] dp = new int[high + 1];

    for (int i = 0; i <= high; i++) {
      dp[i] = -1;
    }

    int ans = 0;

    for (int i = low; i <= high; i++) {
      ans = (int) ((ans % mod + coinChange(i, zero, one, dp) % mod) % mod);
    }

    return ans;

  }

  public int countGoodStringsBottomUp(int low, int high, int zero, int one) {

    int[] dp = new int[high + 1];

    dp[0] = 1;

    int ans = 0;

    for (int i = 1; i <= high; i++) {
      if (i >= zero) {
        dp[i] = (int) ((dp[i] + dp[i - zero]) % mod);
      }
      if (i >= one) {
        dp[i] = (int) ((dp[i] + dp[i - one]) % mod);
      }
      if (i >= low) {
        ans = (int) ((ans + dp[i]) % mod);
      }
    }

    return ans;

  }

  private int coinChange(int target, int x, int y, int[] dp) {

    // base conditions

    // means empty target can be achieved
    if (target == 0) {
      return 1;
    }

    // negative string length not possible
    if (target < 0) {
      return 0;
    }

    // if already computed, return the ans
    if (dp[target] != -1) {
      return dp[target];
    }

    int sum = 0;

    sum = coinChange(target - x, x, y, dp) + coinChange(target - y, x, y, dp);

    return dp[target] = (int) (sum % mod);

  }


}
