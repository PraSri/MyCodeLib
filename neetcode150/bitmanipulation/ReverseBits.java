package bitmanipulation;

/***Reverse Integer (Medium)
 https://leetcode.com/problems/reverse-integer/

 Number of 1 Bits (Easy)
 https://leetcode.com/problems/number-of-1-bits/

 A Number After a Double Reversal (Easy)
 https://leetcode.com/problems/a-number-after-a-double-reversal/*/
public class ReverseBits {

    //Har bit ko extract karke uski mirror position pe place kar do.
    public int reverseBits(int n) {
        int res = 0;

        for (int i = 0; i < 32; i++) {

            // last bit isolate kr rhe
            int bit = (n >> i) & 1;

            // wo jo upar last bit nikala hai, usko
            // abb left shift krke starting me la rhe
            // yhi pe reverse hogi each bit
            res += (bit << (31 - i));

        }
        return res;
    }

    /**
     * Reverse Integer
     * https://leetcode.com/problems/reverse-integer
     */
    public static class ReverseInteger {
    }

    /**
     * Number of 1 Bits
     * https://leetcode.com/problems/number-of-1-bits
     */
    public static class NumberOf1Bits {
    }

    /**
     * A Number After a Double Reversal
     * https://leetcode.com/problems/a-number-after-a-double-reversal
     */
    public static class ANumberAfterADoubleReversal {
    }
}
