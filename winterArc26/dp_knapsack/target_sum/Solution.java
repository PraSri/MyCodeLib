package dp_knapsack.target_sum;

public class Solution {

    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (Math.abs(target) > total || ((total + target) & 1) == 1) {
            return 0;
        }
        int subset = (total + target) / 2;
        int[] ways = new int[subset + 1];
        ways[0] = 1;
        for (int num : nums) {
            for (int s = subset; s >= num; s--) {
                ways[s] += ways[s - num];
            }
        }
        return ways[subset];
    }
}
