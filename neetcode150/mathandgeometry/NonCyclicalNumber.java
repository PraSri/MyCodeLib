package mathandgeometry;


/***Linked List Cycle (Easy)
 https://leetcode.com/problems/linked-list-cycle/

 Add Digits (Easy)
 https://leetcode.com/problems/add-digits/

 Ugly Number (Easy)
 https://leetcode.com/problems/ugly-number/

 Sum of Digits of String After Convert (Easy)
 https://leetcode.com/problems/sum-of-digits-of-string-after-convert/

 Minimum Addition to Make Integer Beautiful (Medium)
 https://leetcode.com/problems/minimum-addition-to-make-integer-beautiful/

 Smallest Value After Replacing With Sum of Prime Factors (Medium)
 https://leetcode.com/problems/smallest-value-after-replacing-with-sum-of-prime-factors/

 Count the Digits That Divide a Number (Easy)
 https://leetcode.com/problems/count-the-digits-that-divide-a-number/*/

public class NonCyclicalNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = ss(n);
        while (slow != fast) {
            slow = ss(slow);
            fast = ss(ss(fast));
        }
        return fast == 1;
    }

    private int ss(int n) {
        int res = 0;
        while (n != 0) {
            int digit = n % 10;
            res = res + (digit * digit);
            n = n / 10;
        }
        return res;
    }

    /**
     * Linked List Cycle
     * https://leetcode.com/problems/linked-list-cycle
     */
    public static class LinkedListCycle {
    }

    /**
     * Add Digits
     * https://leetcode.com/problems/add-digits
     */
    public static class AddDigits {
    }

    /**
     * Ugly Number
     * https://leetcode.com/problems/ugly-number
     */
    public static class UglyNumber {
    }

    /**
     * Sum of Digits of String After Convert
     * https://leetcode.com/problems/sum-of-digits-of-string-after-convert
     */
    public static class SumOfDigitsOfStringAfterConvert {
    }

    /**
     * Minimum Addition to Make Integer Beautiful
     * https://leetcode.com/problems/minimum-addition-to-make-integer-beautiful
     */
    public static class MinimumAdditionToMakeIntegerBeautiful {
    }

    /**
     * Smallest Value After Replacing With Sum of Prime Factors
     * https://leetcode.com/problems/smallest-value-after-replacing-with-sum-of-prime-factors
     */
    public static class SmallestValueAfterReplacingWithSumOfPrimeFactors {
    }

    /**
     * Count the Digits That Divide a Number
     * https://leetcode.com/problems/count-the-digits-that-divide-a-number
     */
    public static class CountTheDigitsThatDivideANumber {
    }
}
