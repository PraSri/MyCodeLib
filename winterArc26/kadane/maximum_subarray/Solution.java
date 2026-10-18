package kadane.maximum_subarray;

public class Solution {

    public int maxSubArray(int[] nums) {
        int best = nums[0];
        int endingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            endingHere = Math.max(nums[i], endingHere + nums[i]);
            best = Math.max(best, endingHere);
        }
        return best;
    }
}
