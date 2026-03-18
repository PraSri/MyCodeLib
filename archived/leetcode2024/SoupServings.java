package leetcode2024;

import java.util.HashMap;
import java.util.Map;

public class SoupServings {

  public static void main(String[] args) {

    SoupServings ss = new SoupServings();
    System.out.println(ss.soupServings(50));

  }

  /**
   * There are two types of soup: type A and type B. Initially, we have n ml of each type of soup.
   * There are four kinds of operations:
   * <p>
   * Serve 100 ml of soup A and 0 ml of soup B,
   * Serve 75 ml of soup A and 25 ml of soup B,
   * Serve 50 ml of soup A and 50 ml of soup B, and
   * Serve 25 ml of soup A and 75 ml of soup B.
   * When we serve some soup, we give it to someone, and we no longer have it.
   * Each turn, we will choose from the four operations with an equal probability 0.25.
   * If the remaining volume of soup is not enough to complete the operation,
   * we will serve as much as possible.
   * We stop once we no longer have some quantity of both types of soup.
   * <p>
   * Note that we do not have an operation where all 100 ml's of soup B are used first.
   * <p>
   * Return the probability that soup A will be empty first,
   * plus half the probability that A and B become empty at the same time.
   * Answers within 10-5 of the actual answer will be accepted.
   */

  /**
   * At first, one may notice that all soup amounts in all four operations are multiples of 25 ml.
   * This observation allows us to consider 25 ml as one serving.
   * In terms of servings, the operations will be as follows:
   * Serve four servings of soup A and 0 servings of soup B.
   * Serve three servings of soup A and one serving of soup B.
   * Serve two servings of soup A and two servings of soup B.
   * Serve one serving of soup A and three servings of soup B.
   * n milliliters of soup is ceil(n/25)
   * full servings plus n%25 milliliters. For example,
   * n=474 ml is 18 full servings plus 24 ml. In this case,
   * 474 ml will run out after 19 servings:
   * 18 full servings plus one more to serve the remaining 24 ml.
   */

  public double soupServings(int n) {

    // no. of servings
    int m = (int) Math.ceil(n / 25.0);

    // dp[i][j] = ans
    // i -> i servings of soup A
    // j -> j servings of soup B
    // dp[0][j] = 1
    // dp[i][0] = 0
    // dp[0][0] = 0.5

    Map<Integer, Map<Integer, Double>> dp = new HashMap<>();

    for (int k = 1; k <= m; k++) {
      // as m -> very large number, probability approach to 1,
      // this is by mathematical proof, no chance to prove it in real interview
      if (calDP(k, k, dp) > 1 - 1e-5) {
        return 1.0;
      }
    }

    return calDP(m, m, dp);

  }

  private double calDP(int i, int j, Map<Integer, Map<Integer, Double>> dp) {

    if (i <= 0 && j <= 0) {
      return 0.5;
    }

    if (i <= 0) {
      return 1.0;
    }
    if (j <= 0) {
      return 0.0;
    }

    if (dp.containsKey(i) && dp.get(i).containsKey(j)) {
      return dp.get(i).get(j);
    }

    double ans = (calDP(i - 4, j, dp) + calDP(i - 3, j - 1, dp) + calDP(i - 2, j - 2, dp) +
        calDP(i - 1, j - 3, dp)) / 4.0;

    dp.computeIfAbsent(i, k -> new HashMap<>()).put(j, ans);

    return ans;
  }

}
