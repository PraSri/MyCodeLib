package leetcode2024;

public class NumberOfWaysReachPositionAfterExactlyKSteps {

  long mod = 1000000007;

  public static void main(String[] args) {

  }

  public int numberOfWays(int startPos, int endPos, int k) {

    // not possible return 0

    if (Math.abs(endPos - startPos) > k || (startPos - endPos - k) % 2 != 0) {
      return 0;
    }

    int[][] dp = new int[3000][k + 1];

    for (int i = 0; i < 3000; i++) {
      for (int j = 0; j <= k; j++) {
        dp[i][j] = -1;
      }
    }

    return helper(startPos, endPos, k, dp);

  }

  private int helper(int a, int b, int k, int[][] dp) {

    // base conditions

    if (a == b && k == 0) {
      return 1;
    }

    if (k == 0) {
      return 0;
    }

    // if already solved, return ans
    if (dp[a + 1000][k] != -1) {
      return dp[a + 1000][k];
    }

    // move 1 step left, now ans space if limited in range a-1,k-1
    int left = helper(a - 1, b, k - 1, dp);

    int right = helper(a + 1, b, k - 1, dp);

    int ans = (int) ((left + right) % mod);

    dp[a + 1000][k] = ans;

    return ans;

  }

}
