package dp_linear.climbing_stairs;

public class Solution {

    public int climbStairs(int n) {
        int prev = 1; // ways(i - 2)
        int cur = 1;  // ways(i - 1)
        for (int i = 2; i <= n; i++) {
            int next = prev + cur;
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
