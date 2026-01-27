package bitmanipulation;

/**
 * Number of 1 Bits
 * https://leetcode.com/problems/number-of-1-bits/
 * <p>
 * Sum of Values at Indices With K Set Bits
 * https://leetcode.com/problems/sum-of-values-at-indices-with-k-set-bits/
 * <p>
 * Find the K-or of an Array
 * https://leetcode.com/problems/find-the-k-or-of-an-array/
 */
public class CountingBits {

    // Power of 2 numbers (1, 2, 4, 8, )
    // ke binary me sirf ek 1 hota hai

    //the number of set bits in i is:
    //
    //1 (for the highest power of two) + number of set bits in the remainder

    // 5/2 = 2  (1 remainder)
    // offset = 1 (tracks the most recent power of two)
    // toh 5 ka nearest 4 hai
    // 1 + (4 me jitne ones hai)

    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        int offset = 1; //offset = 1 (tracks the most recent power of two)
        for (int i = 1; i <= n; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }

    /**
     * Number of 1 Bits
     * https://leetcode.com/problems/number-of-1-bits
     */
    public static class NumberOf1Bits {
    }

    /**
     * Sum of Values at Indices With K Set Bits
     * https://leetcode.com/problems/sum-of-values-at-indices-with-k-set-bits
     */
    public static class SumOfValuesAtIndicesWithKSetBits {
    }

    /**
     * Find the K-or of an Array
     * https://leetcode.com/problems/find-the-k-or-of-an-array
     */
    public static class FindTheKOrOfAnArray {
    }
}
