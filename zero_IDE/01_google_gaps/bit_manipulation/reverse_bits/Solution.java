public class Solution {
    // Treat n as an unsigned 32-bit integer
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1; // Unsigned logical right shift
        }
        return result;
    }
}
