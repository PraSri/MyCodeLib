package bitmanipulation;

public class SumOfTwoIntegers {

    // Hume do integers a aur b ka sum nikalna
    // hai without using + or - operators.

    // Intuition yaad rakhne ka trick
    //
    //XOR = sum without carry
    //
    //AND + shift = carry
    //
    //Jab tak carry hai, add karte raho
    public int getSum(int a, int b) {
        while(b!=0) {
            //a & b ? jahan dono bits 1 hain,
            // wahi carry aayega
            //
            //<< 1 ? carry ko next higher bit me
            // shift karna (binary addition rule)
            int carry = (a&b) << 1;// a = 1 , b = 2 , a = 001, b=010
            // a&b=000, carry = 0
            // XOR ka matlab: same bits ? 0, different bits ? 1
            //Ye hume sum without carry deta hai
            a ^= b;
            //Ab b me sirf carry bacha
            //
            //Next iteration me is carry ko add karenge
            b = carry;
        }
        // Jab b == 0, matlab koi carry nahi bacha
        //
        // Final answer a me mil chuka hai
        return a;
    }

}
