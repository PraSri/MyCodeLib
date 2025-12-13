package oneddynamicprogramming;

public class MaximumProductSubarray {

    //Input: nums = [1,2,-3,4]
    //
    //Output: 4

    public int maxProduct(int[] nums) {

        int curMax = nums[0]; // 1

        int preMax = nums[0]; // 1

        int preMin = nums[0]; // 1

        int ans = nums[0]; // 1

        for(int i = 1; i<nums.length; i++) {

            // 3 things we are tracking
            // 1. ith * preMax
            // 2. ith * preMin
            // 3. ith
            // max(1,2,3)
            curMax = Math.max(nums[i]*preMax, nums[i]*preMin);

            curMax = Math.max(curMax, nums[i]);

            int curMin = Math.min(nums[i]*preMax, nums[i]*preMin);
            curMin = Math.min(curMin, nums[i]);

            ans = Math.max(ans, curMax);

            preMax = curMax;

            preMin = curMin;

        }
        return ans;
    }

}
