package arraysandhashing;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

    public static int longestConsecutiveV2(int[] nums) {
        Map<Integer, Integer> lengthMap = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            if (!lengthMap.containsKey(num)) {
                int leftLength = lengthMap.getOrDefault(num - 1, 0);
                int rightLength = lengthMap.getOrDefault(num + 1, 0);
                int length = leftLength + rightLength + 1;
                lengthMap.put(num, length);
                lengthMap.put(num - leftLength, length);
                lengthMap.put(num + rightLength, length);
                res = Math.max(res, length);
            }
        }

        System.out.println(lengthMap);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 20, 4, 10, 3, 4, 5};
        System.out.println(longestConsecutiveV2(nums));
    }

    /**
     *
     * Binary Tree Longest Consecutive Sequence
     * <a href="https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/">...</a>
     * <p>
     * Find Three Consecutive Integers That Sum to a Given Number
     * <a href="https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number/">...</a>
     * <p>
     * Maximum Consecutive Floors Without Special Floors
     * <a href="https://leetcode.com/problems/maximum-consecutive-floors-without-special-floors/">...</a>
     * <p>
     * Length of the Longest Alphabetical Continuous Substring
     * <a href="https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/">...</a>
     * <p>
     * Find the Maximum Number of Elements in Subset
     * <a href="https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/">...</a>
     *
     */

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            if (!mp.containsKey(num)) {
                mp.put(num, mp.getOrDefault(num - 1, 0) + mp.getOrDefault(num + 1, 0) + 1);
                mp.put(num - mp.getOrDefault(num - 1, 0), mp.get(num));
                mp.put(num + mp.getOrDefault(num + 1, 0), mp.get(num));
                res = Math.max(res, mp.get(num));
            }
        }
        return res;
    }

    /**
     * <a href="https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/">LeetCode - Binary Tree Longest Consecutive Sequence</a>
     */
    public static class BinaryTreeLongestConsecutiveSequence {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number/">LeetCode - Find Three Consecutive Integers That Sum to a Given Number</a>
     */
    public static class FindThreeConsecutiveIntegersThatSumToAGivenNumber {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-consecutive-floors-without-special-floors/">LeetCode - Maximum Consecutive Floors Without Special Floors</a>
     */
    public static class MaximumConsecutiveFloorsWithoutSpecialFloors {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/">LeetCode - Length of the Longest Alphabetical Continuous Substring</a>
     */
    public static class LengthOfTheLongestAlphabeticalContinuousSubstring {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/">LeetCode - Find the Maximum Number of Elements in Subset</a>
     */
    public static class FindTheMaximumNumberOfElementsInSubset {
        // placeholder
    }
}
