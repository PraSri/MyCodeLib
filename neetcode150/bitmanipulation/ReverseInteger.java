package bitmanipulation;

/**
 * String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 * <p>
 * Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 * <p>
 * A Number After a Double Reversal
 * https://leetcode.com/problems/a-number-after-a-double-reversal/
 * <p>
 * Count Number of Distinct Integers After Reverse Operations
 * https://leetcode.com/problems/count-number-of-distinct-integers-after-reverse-operations/
 **/
public class ReverseInteger {

    public int reverse(int x) {
        int MIN = Integer.MIN_VALUE;
        int MAX = Integer.MAX_VALUE;
        int res = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            //Before multiplying by 10,
            // we ensure that the current value is
            // within safe bounds so that
            // adding the next digit does not cause integer overflow.
            if (res > MAX / 10 || (res == MAX / 10 && digit > MAX % 10)) {
                return 0;
            }

            if (res < MIN / 10 || (res == MIN / 10 && digit < MIN % 10)) {
                return 0;
            }
            res = (res * 10) + digit;
        }
        return res;
    }


    /**
     * String to Integer (atoi)
     * https://leetcode.com/problems/string-to-integer-atoi
     */
    public static class StringToIntegerAtoi {
    }

    /**
     * Reverse Bits
     * https://leetcode.com/problems/reverse-bits
     */
    public static class ReverseBits {
    }

    /**
     * A Number After a Double Reversal
     * https://leetcode.com/problems/a-number-after-a-double-reversal
     */
    public static class ANumberAfterADoubleReversal {
    }

    /**
     * Count Number of Distinct Integers After Reverse Operations
     * https://leetcode.com/problems/count-number-of-distinct-integers-after-reverse-operations
     */
    public static class CountNumberOfDistinctIntegersAfterReverseOperations {
    }
}
