package sliding_window;

import java.util.*;

public class SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return subArrayWithKAtmost(nums, k) - subArrayWithKAtmost(nums, k-1);
    }

    private int subArrayWithKAtmost(int[] nums, int k) {
        int count = 0;
        int left = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int right = 0;right<nums.length;right++) {
            frequencyMap.put(nums[right], frequencyMap.getOrDefault(nums[right], 0) + 1);
            if(frequencyMap.get(nums[right]) == 1) {
                k--;
            }
            while(k < 0) {
                frequencyMap.put(nums[left], frequencyMap.getOrDefault(nums[left], 0) - 1);
                if(frequencyMap.get(nums[left]) == 0) {
                    k++;
                }
                left++;
            }
            count += (right-left+1);
        }
        return count;
    }
}
