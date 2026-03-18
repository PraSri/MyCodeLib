package sort_and_search;

import java.util.Arrays;

public class TwoSumLessThanK {

    public int twoSumLessThanK(int[] nums, int k) {

        // Replace this placeholder return statement with your code

        Arrays.sort(nums);

        int ans = -1;

        int n = nums.length;
        for(int i =0; i<n; i++) {
            int j = binarySearch(nums, k - nums[i], i + 1, n);
            if(i < j) {
                ans = Math.max(ans, nums[i]+nums[j]);
            }
        }

        return ans;
    }

    private int binarySearch(int[] nums, int target, int l, int r) {
        int mid;
        while(l<r) {
            mid = l + (r-l)/2;
            if(nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

}
