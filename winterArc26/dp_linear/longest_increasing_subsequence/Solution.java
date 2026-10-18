package dp_linear.longest_increasing_subsequence;

public class Solution {

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int lo = 0;
            int hi = size;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (tails[mid] < num) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            tails[lo] = num;
            if (lo == size) {
                size++;
            }
        }
        return size;
    }
}
