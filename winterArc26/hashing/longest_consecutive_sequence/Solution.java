package hashing.longest_consecutive_sequence;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int best = 0;
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue; // not a sequence head
            }
            int length = 1;
            long next = (long) num + 1;
            while (next <= Integer.MAX_VALUE && set.contains((int) next)) {
                length++;
                next++;
            }
            best = Math.max(best, length);
        }
        return best;
    }
}
