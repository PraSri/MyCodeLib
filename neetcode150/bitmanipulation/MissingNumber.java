package bitmanipulation;

/**
 * First Missing Positive (Hard)
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * Single Number (Easy)
 * https://leetcode.com/problems/single-number/
 * <p>
 * Find the Duplicate Number (Medium)
 * https://leetcode.com/problems/find-the-duplicate-number/
 * <p>
 * Couples Holding Hands (Hard)
 * https://leetcode.com/problems/couples-holding-hands/
 * <p>
 * Find Unique Binary String (Medium)
 * https://leetcode.com/problems/find-unique-binary-string/
 * <p>
 * Find the Largest Almost Missing Integer (Easy)
 * https://leetcode.com/problems/find-the-largest-almost-missing-integer/
 **/
public class MissingNumber {

    //So if every number from 0..n appears twice (once from index, once from array), they cancel out
    // except the missing one

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int xorr = n;
        for (int i = 0; i < n; i++) {
            xorr ^= i ^ nums[i];
        }
        return xorr;
    }


    /**
     * First Missing Positive
     * https://leetcode.com/problems/first-missing-positive
     */
    public static class FirstMissingPositive {
    }

    /**
     * Single Number
     * https://leetcode.com/problems/single-number
     */
    public static class SingleNumber {
    }

    /**
     * Find the Duplicate Number
     * https://leetcode.com/problems/find-the-duplicate-number
     */
    public static class FindTheDuplicateNumber {
    }

    /**
     * Couples Holding Hands
     * https://leetcode.com/problems/couples-holding-hands
     */
    public static class CouplesHoldingHands {
    }

    /**
     * Find Unique Binary String
     * https://leetcode.com/problems/find-unique-binary-string
     */
    public static class FindUniqueBinaryString {
    }

    /**
     * Find the Largest Almost Missing Integer
     * https://leetcode.com/problems/find-the-largest-almost-missing-integer
     */
    public static class FindTheLargestAlmostMissingInteger {
    }
}
