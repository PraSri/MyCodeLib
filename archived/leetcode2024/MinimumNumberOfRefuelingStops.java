package leetcode2024;

public class MinimumNumberOfRefuelingStops {

  public static void main(String[] args) {

    MinimumNumberOfRefuelingStops s = new MinimumNumberOfRefuelingStops();
    int ans = s.minRefuelStops(100, 10, new int[][] {{10, 60}, {20, 30}, {30, 30}, {60, 40}});
    System.out.println(ans);
  }

  /**
   * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
   * Output: 2
   * Explanation: We start with 10 liters of fuel.
   * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
   * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
   * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
   * We made 2 refueling stops along the way, so we return 2.
   */

  public int minRefuelStops(int target, int startFuel, int[][] stations) {
    int n = stations.length;
    int[] dp = new int[n + 1];
    // dp[i] -> max distance i can reach using i fuel stations
    dp[0] = startFuel; // i can reach startFuel distance using 0 stations

    for (int i = 1; i <= n; i++) {
      // traverse for all stations
      // i can go to each j stations from i to 0, to get myself best result
      for (int j = i; j >= 0; j--) {
        // if i can reach next station, i can use it
        if (dp[j] >= stations[i - 1][0]) {
          dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i - 1][1]);
        }
      }
    }

    for (int i = 0; i <= n; i++) {
      System.out.print(dp[i] + ", ");
    }

    System.out.println();

    // search best means minimum j
    for (int j = 0; j <= n; j++) {
      if (dp[j] >= target) {
        return j;
      }
    }

    return -1;

  }

}
