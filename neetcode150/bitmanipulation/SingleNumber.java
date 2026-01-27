package bitmanipulation;

/**
 * Single Number II (Medium)
 * https://leetcode.com/problems/single-number-ii/
 * <p>
 * Single Number III (Medium)
 * https://leetcode.com/problems/single-number-iii/
 * <p>
 * Missing Number (Easy)
 * https://leetcode.com/problems/missing-number/
 * <p>
 * Find the Duplicate Number (Medium)
 * https://leetcode.com/problems/find-the-duplicate-number/
 * <p>
 * Find the Difference (Easy)
 * https://leetcode.com/problems/find-the-difference/
 * <p>
 * Find the XOR of Numbers Which Appear Twice (Easy)
 * https://leetcode.com/problems/find-the-xor-of-numbers-which-appear-twice/
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }


    /**
     * Single Number II
     * https://leetcode.com/problems/single-number-ii
     */
    public static class SingleNumberIi {
    }

    /**
     * Single Number III
     * https://leetcode.com/problems/single-number-iii
     */
    public static class SingleNumberIii {
    }

    /**
     * Missing Number
     * https://leetcode.com/problems/missing-number
     */
    public static class MissingNumber {
    }

    /**
     * Find the Duplicate Number
     * https://leetcode.com/problems/find-the-duplicate-number
     */
    public static class FindTheDuplicateNumber {
    }

    /**
     * Find the Difference
     * https://leetcode.com/problems/find-the-difference
     */
    public static class FindTheDifference {
    }

    /**
     * Find the XOR of Numbers Which Appear Twice
     * https://leetcode.com/problems/find-the-xor-of-numbers-which-appear-twice
     */
    public static class FindTheXorOfNumbersWhichAppearTwice {
    }
}
