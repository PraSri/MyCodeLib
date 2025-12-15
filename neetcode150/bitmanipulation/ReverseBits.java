package bitmanipulation;

public class ReverseBits {

    //“Har bit ko extract karke uski mirror position pe place kar do.”
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
}
