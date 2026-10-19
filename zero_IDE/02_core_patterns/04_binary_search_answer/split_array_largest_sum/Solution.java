public class Solution {
    public int splitArray(int[] nums, int k) {
        long lo = 0;
        long hi = 0;
        for (int num : nums) {
            lo = Math.max(lo, num);
            hi += num;
        }

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (canSplit(nums, k, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return (int) lo;
    }

    private boolean canSplit(int[] nums, int k, long maxSubarraySum) {
        int count = 1;
        long currentSum = 0;

        for (int num : nums) {
            if (currentSum + num > maxSubarraySum) {
                count++;
                currentSum = num;
                if (count > k) return false;
            } else {
                currentSum += num;
            }
        }

        return true;
    }
}
