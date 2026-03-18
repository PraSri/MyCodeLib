package easy;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] sumIndex = new int[2];

        Map<Integer, Integer> lookup = new HashMap<>();

        int index = 0;

        for (int x : nums) {
            if (lookup.containsKey(x)) {
                sumIndex[0] = lookup.get(x);
                sumIndex[1] = index;
                break;
            }
            lookup.put(target - x, index);
            index++;
        }

        return sumIndex;
    }
}
