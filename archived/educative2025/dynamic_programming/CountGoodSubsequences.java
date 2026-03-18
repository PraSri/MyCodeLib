package dynamic_programming;

public class CountGoodSubsequences {

    //static attributes to hold the data
    public static long[] factorials;
    public static long[] inverses;
    public static final int MOD = 1000000007;

    //method to count the good subsequences
    public static int countGoodSubsequences(String s) {

        //Initializing factorials and inverses array of size N + 1
        final int N = s.length() + 1;
        factorials = new long[N];
        inverses = new long[N];

        //assigning 1 to 0th index of both arrays,
        //since factorial and inverse of 1 is 1
        factorials[0] = 1;
        inverses[0] = 1;

        //calculating the factorial and inverse of all numbers from 1 to N
        //instead of calculatng factorial of a number again and again
        //we will store the factorial of a number i
        //and use to calculate the factorial of a number i+1, since
        //the factorial of a number i+1 is factorial of i-1 * i
        for (int i = 1; i < N; ++i) {
            factorials[i] = factorials[i - 1] * i % MOD;
            inverses[i] = quickModularInverse(factorials[i], MOD - 2, MOD);
        }

        //Initializing an array of size 26 to hold the frequency
        //of each character from 'a' to 'z'
        int[] frequencyCount = new int[26];

        //Initializing a variable to keep record of maximum frequency
        int maxCount = 1;

        //calculating the frequency of each character and
        //keeping record of maximum frequency
        for (int i = 0; i < s.length(); ++i) {
            maxCount = Math.max(maxCount, ++frequencyCount[s.charAt(i) - 'a']);
        }

        //initializing a variable to store the count of good subsequences
        long finalCount = 0;

        //nested loop to calculate the combination
        //from 1 to the maximum frequency
        for (int i = 1; i <= maxCount; ++i) {
            long count = 1;

            for (int j = 0; j < 26; ++j) {
                //counting only if count of frequency of a character is
                //greater of equal than the i
                if (frequencyCount[j] >= i) {
                    count = count * (combination(frequencyCount[j], i) + 1) % MOD;
                }
            }
            //adding the count to the final count after subtracting 1
            //and taking the mod
            finalCount = (finalCount + count - 1) % MOD;
        }

        //returning the final count after casting it to integer
        return (int) finalCount;

    }

    //method to find the modular inverse of a number
    public static long quickModularInverse(long base, long exponent, long modulus) {
        // Initialize result to 1, as 1 is the identity element for multiplication modulo modulus
        long result = 1;

        while (exponent != 0) {
            // If exponent is odd, multiply result by base and take modulo modulus
            if ((exponent & 1) == 1) {
                result = result * base % modulus;
            }
            // Right shift exponent by 1 (equivalent to dividing exponent by 2)
            exponent >>= 1;
            // Square base and take modulo modulus to reduce base in terms of modulus
            base = base * base % modulus;
        }

        return result;
    }

    //calculating the combination (n choose k)
    public static long combination(int n, int k) {
        return (factorials[n] * inverses[k] % MOD) * inverses[n - k] % MOD;
    }

    // Driver code
    public static void main(String[] args) {
        String[] inputList = {"aqw", "aabbcc", "aaa", "abbc", "abbb"};

        for (int i = 0; i < inputList.length; i++) {
            System.out.print(i + 1);

            System.out.println(".\tInput string: " + inputList[i]);
            System.out.println("\tNumber of good subsequences: " + countGoodSubsequences(inputList[i]));

            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

}