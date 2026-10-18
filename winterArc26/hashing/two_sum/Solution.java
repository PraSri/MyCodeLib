package hashing.two_sum;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = indexOf.get(target - nums[i]);
            if (j != null) {
                return new int[]{j, i};
            }
            indexOf.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two-sum solution");
    }
}
