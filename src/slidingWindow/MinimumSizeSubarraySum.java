package slidingWindow;

public class MinimumSizeSubarraySum {
    // https://leetcode.com/problems/minimum-size-subarray-sum/

    public int minSubArrayLen(int target, int[] nums) {

        int n = nums.length;
        int sum = 0, s = 0, e = 0, min = Integer.MAX_VALUE;

        while(e<n) {

            sum += nums[e++]; // expanding

            while(sum>=target) {

                min = Math.min(min, e-s);

                sum -= nums[s++]; // contracting

            }

        }

        return min == Integer.MAX_VALUE ? 0 : min;

    }

}