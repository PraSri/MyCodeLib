package leetcode2024;

public class CountWaysToPlaceHouses {

  long MOD = 1000000007;

  public static void main(String[] args) {

    CountWaysToPlaceHouses countWaysToPlaceHouses = new CountWaysToPlaceHouses();
    int n = 3;
    int ans = countWaysToPlaceHouses.countHousePlacements(n);
    System.out.println(ans);

  }

  public int countHousePlacements(int n) {

    // we have ways for nth place, if its empty or if there is house
    long waysOneSide = (count(n, false) + count(n, true)) % MOD;

    long ans = (waysOneSide * waysOneSide) % MOD;

    return (int) ans;


  }

  private long count(int n, boolean isEmpty) {
    if (n == 1) {
      return 1L;
    }
    // if nth plot is empty, means our result will come from n-1 plots with 2 cases - either we place the house or not place house
    if (isEmpty) {
      return (count(n - 1, false) % MOD + count(n - 1, true) % MOD) % MOD;
    }
    // if not empty, we only have one choice, of answer from n-1 plots
    return count(n - 1, true) % MOD;
  }

  public int countHousePlacementsFiboApproach(int n) {

    long[] dp = new long[n + 10];

    dp[0] = 1;
    dp[1] = 2;
    for (int i = 2; i <= n; i++) {
      // ith ans depends on i-1 th plot when no house or i-2 when there is house on ith plot
      // equivalent to fibonacci
      dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
    }

    return (int)((dp[n] * dp[n]) % MOD);

  }


}
