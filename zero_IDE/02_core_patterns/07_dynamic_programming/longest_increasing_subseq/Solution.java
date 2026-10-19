import java.util.*;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] tails = new int[nums.length];
        int size = 0;

        for (int x : nums) {
            int lo = 0, hi = size;
            // Binary search for insertion index (first element >= x)
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] >= x) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            tails[lo] = x;
            if (lo == size) {
                size++;
            }
        }

        return size;
    }
}
