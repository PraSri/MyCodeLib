package mathandgeometry;

/**
 * Multiply Strings (Medium)
 * https://leetcode.com/problems/multiply-strings/
 * <p>
 * Add Binary (Easy)
 * https://leetcode.com/problems/add-binary/
 * <p>
 * Plus One Linked List (Medium)
 * https://leetcode.com/problems/plus-one-linked-list/
 * <p>
 * Add to Array-Form of Integer (Easy)
 * https://leetcode.com/problems/add-to-array-form-of-integer/
 * <p>
 * Minimum Operations to Reduce an Integer to 0 (Medium)
 * https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0/
 ***/

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }

    /**
     * Multiply Strings
     * https://leetcode.com/problems/multiply-strings
     */
    public static class MultiplyStrings {
    }

    /**
     * Add Binary
     * https://leetcode.com/problems/add-binary
     */
    public static class AddBinary {
    }

    /**
     * Plus One Linked List
     * https://leetcode.com/problems/plus-one-linked-list
     */
    public static class PlusOneLinkedList {
    }

    /**
     * Add to Array-Form of Integer
     * https://leetcode.com/problems/add-to-array-form-of-integer
     */
    public static class AddToArrayFormOfInteger {
    }

    /**
     * Minimum Operations to Reduce an Integer to 0
     * https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0
     */
    public static class MinimumOperationsToReduceAnIntegerTo0 {
    }
}
