package hashing.contains_duplicate;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>(nums.length * 2);
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
