package slidingWindow;

public class SubarrayProductLessThanK {

// https://leetcode.com/problems/subarray-product-less-than-k/

    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if(k<=1)
            return 0;

        int prod = 1, ans = 0, left = 0, right = 0, n = nums.length;

        while(right < n) {

            prod = prod * nums[right];
            while(prod >= k) {
                prod = prod / nums[left];
                left++; // window contacting
            }

            ans = ans + (right-left+1);

            right++; // winow expanding

        }

        return ans;

    }

}