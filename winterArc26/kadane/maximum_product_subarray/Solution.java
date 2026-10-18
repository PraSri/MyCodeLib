package kadane.maximum_product_subarray;

public class Solution {

    public int maxProduct(int[] nums) {
        int best = nums[0];
        int maxHere = nums[0];
        int minHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) {
                int t = maxHere;
                maxHere = minHere;
                minHere = t;
            }
            maxHere = Math.max(x, maxHere * x);
            minHere = Math.min(x, minHere * x);
            best = Math.max(best, maxHere);
        }
        return best;
    }
}
