package leetcode2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTickets {

  /**
   * You have planned some train traveling one year in advance.
   * The days of the year in which you will travel are given as an integer array days.
   * Each day is an integer from 1 to 365.
   * <p>
   * Train tickets are sold in three different ways:
   * <p>
   * a 1-day pass is sold for costs[0] dollars,
   * a 7-day pass is sold for costs[1] dollars, and
   * a 30-day pass is sold for costs[2] dollars.
   * The passes allow that many days of consecutive travel.
   * <p>
   * For example, if we get a 7-day pass on day 2,
   * then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
   * Return the minimum number of dollars you need to travel every day in the given list of days.
   */

  /**
   * Example 1:
   * <p>
   * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
   * Output: 11
   * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
   * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
   * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
   * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
   * In total, you spent $11 and covered all the days of your travel.
   **/

  private Set<Integer> isTravel = new HashSet<>();

  public static void main(String[] args) {
    MinimumCostForTickets obj = new MinimumCostForTickets();
    int[] days = new int[] {1, 4, 6, 7, 8, 20};
    int[] costs = new int[] {2, 7, 15};
    System.out.println(obj.mincostTickets(days, costs));
  }

  public int mincostTickets(int[] days, int[] costs) {

    int n = days.length;
    int lastDay = days[n - 1];
    int[] dp = new int[lastDay + 1];
    Arrays.fill(dp, -1);

    // mark the days for which travel is needed

    for (int d : days) {
      isTravel.add(d);
    }

    int currDay = 1;

    return solve(dp, days, costs, currDay);

  }

  private int solve(int[] dp, int[] days, int[] costs, int currDay) {

    // base cases
    // if we iterated over the days, return 0;
    if (currDay > days[days.length - 1]) {
      return 0;
    }

    // if current day is not found in isTravel itinerary, move on the next day
    if (!isTravel.contains(currDay)) {
      return solve(dp, days, costs, currDay + 1);
    }

    if (dp[currDay] != -1) {
      return dp[currDay];
    }

    // pick each case, solve it recursively, chose the min
    int oneDay = costs[0] + solve(dp, days, costs, currDay + 1);
    int sevenDay = costs[1] + solve(dp, days, costs, currDay + 7);
    int thirtyDay = costs[2] + solve(dp, days, costs, currDay + 30);

    dp[currDay] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));

    return dp[currDay];

  }

}
