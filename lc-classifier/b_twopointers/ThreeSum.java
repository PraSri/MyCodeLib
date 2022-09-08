package b_twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // https://leetcode.com/problems/3sum/

    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int l = i + 1, h = n - 1, sum = target - nums[i];
                while (l < h) {
                    if (nums[l] + nums[h] == sum) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[h]));
                        while (l < h && nums[l] == nums[l + 1]) l++;
                        while (l < h && nums[h] == nums[h - 1]) h--;
                        l++;
                        h--;
                    } else if (nums[l] + nums[h] < sum) l++;
                    else h--;
                }
            }
        }

        return res;
    }
}
