package bitmanipulation;

/**
 * Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 * <p>
 * Power of Two
 * https://leetcode.com/problems/power-of-two/
 * <p>
 * Counting Bits
 * https://leetcode.com/problems/counting-bits/
 * <p>
 * Binary Watch
 * https://leetcode.com/problems/binary-watch/
 * <p>
 * Hamming Distance
 * https://leetcode.com/problems/hamming-distance/
 * <p>
 * Binary Number with Alternating Bits
 * https://leetcode.com/problems/binary-number-with-alternating-bits/
 * <p>
 * Prime Number of Set Bits in Binary Representation
 * https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
 * <p>
 * Convert Date to Binary
 * https://leetcode.com/problems/convert-date-to-binary/
 */
public class NumberOfOneBits {

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += (n & 1) == 1 ? 1 : 0;
            n >>= 1;
        }
        return res;
    }

    /**
     * Reverse Bits
     * https://leetcode.com/problems/reverse-bits
     */
    public static class ReverseBits {
    }

    /**
     * Power of Two
     * https://leetcode.com/problems/power-of-two
     */
    public static class PowerOfTwo {
    }

    /**
     * Counting Bits
     * https://leetcode.com/problems/counting-bits
     */
    public static class CountingBits {
    }

    /**
     * Binary Watch
     * https://leetcode.com/problems/binary-watch
     */
    public static class BinaryWatch {
    }

    /**
     * Hamming Distance
     * https://leetcode.com/problems/hamming-distance
     */
    public static class HammingDistance {
    }

    /**
     * Binary Number with Alternating Bits
     * https://leetcode.com/problems/binary-number-with-alternating-bits
     */
    public static class BinaryNumberWithAlternatingBits {
    }

    /**
     * Prime Number of Set Bits in Binary Representation
     * https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation
     */
    public static class PrimeNumberOfSetBitsInBinaryRepresentation {
    }

    /**
     * Convert Date to Binary
     * https://leetcode.com/problems/convert-date-to-binary
     */
    public static class ConvertDateToBinary {
    }
}
