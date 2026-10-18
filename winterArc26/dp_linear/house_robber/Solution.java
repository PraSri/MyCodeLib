package dp_linear.house_robber;

public class Solution {

    public int rob(int[] nums) {
        int prev = 0; // best up to i - 2
        int cur = 0;  // best up to i - 1
        for (int num : nums) {
            int next = Math.max(cur, prev + num);
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
