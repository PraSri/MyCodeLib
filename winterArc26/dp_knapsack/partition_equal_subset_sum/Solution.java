package dp_knapsack.partition_equal_subset_sum;

public class Solution {

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if ((total & 1) == 1) {
            return false;
        }
        int target = total / 2;
        boolean[] reachable = new boolean[target + 1];
        reachable[0] = true;
        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                reachable[s] |= reachable[s - num];
            }
            if (reachable[target]) {
                return true;
            }
        }
        return reachable[target];
    }
}
