package leetcode2024;

import java.util.Arrays;

public class LastStoneWeightII {

  public static void main(String[] args) {
    LastStoneWeightII ob = new LastStoneWeightII();
    int ans = ob.lastStoneWeightII(new int[] {1,2,3});
    System.out.println(ans);
  }

  public int lastStoneWeightII(int[] stones) {

    // similar to classic dp problems of knapsack, subset sum, etc.

    /***
     * stones = [a,b,c,d]
     * ans = (a+b)-(c+d)
     * OR
     * ans = a - (b+c+d)
     * or
     * ans = (a+c) - (b+d)
     * so on..
     * basically we have to divide the array into two subsets whose diff is minimum
     * let them be s1 ans s2
     * s is total sum
     * s1+s2=s
     * s1-s2=d
     * d = s - 2*s1
     * for d to minimum, s1 has to be maximum
     * max possible value of s1 is s/2
     * from kanpsack perspective
     * capacity = s/2
     * w[] = stones[i]
     * v[] = stones[i]
     * */

    int n = stones.length;
    int total = Arrays.stream(stones).sum();
    int sum = total / 2;
    int[][] dp = new int[n + 1][sum + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= sum; j++) {
        // possible case
        if (stones[i - 1] <= j) {
          int include = dp[i - 1][j - stones[i - 1]] + stones[i - 1];
          int exclude = dp[i - 1][j];
          dp[i][j] = Math.max(include, exclude);
        } else {
          // exclude
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    int maxSumPossibleForSubset = dp[n][sum];

    return total - 2 * (maxSumPossibleForSubset);

  }

}
