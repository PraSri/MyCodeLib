package stacks;

import java.util.Stack;

public class NumberOfValidSubarrays {

    /**
     * Given an integer array nums, count how many non-empty contiguous
     * subarrays exist where the first element of each subarray is
     * less than or equal to every other element within that subarray.
     */

    public static int validSubArrays(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int top = stack.pop();
                ans += i - top;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            ans += nums.length - top;
        }
        return ans;
    }

}
