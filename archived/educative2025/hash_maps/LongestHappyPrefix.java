package hash_maps;

public class LongestHappyPrefix {

    /**
     *
     * Why suddenly choosing r to be r + pow(128, i, mod) * ...?
     *
     * To calculate the hash for string "abc", in this algorithm, you would do,
     * ( ord[a]*128^2 + ord[b]*128^1 + ord[c]*128^0 ) % MOD.
     * Note it doesn't have to be 128 and you can change MOD to other values.
     *
     * Now think that the string a, b, c is giving to you in the forward order (as we calculate the prefix).
     * You would do
     * a comes: hash[a] = ord[a]
     * b comes: "ab": hash[ab] = hash[a] *128 + ord[b]
     * c comes: "abc": hash[abc] = hash[ab] * 128 + ord[c]
     *
     * Now think that the string a, b, c is given to you in the reverse order (as we calculate the suffix).
     * c comes: hash[c] = ord[c]
     * b comes: hash[bc] = ord[c] + ord[b]*128,
     * which can be written as r + pow(128, i, mod) * ord(b), and letter b is given to you as s[~i].
     * a comes: hash[abc] = ord[c] + ord[b]*128 + ord[a]*128^2
     * That is r + pow(128, i, mod)
     * I hope it clarifies.
     *
     */

    public String longestPrefix(String s) {

        // Base (b) and large prime modulus (M) for rolling hash
        int b = 31;
        long M = 1000000007;

        // Initialize hash values for prefix and suffix, and track power of base for suffix updates
        long prefixHash = 0;
        long suffixHash = 0;
        long powBase = 1;

        // This will store the length of the longest happy prefix found
        int length = 0;

        // Iterate through the string except the last character (since i goes to n-2)
        for (int i = 0; i < s.length() - 1; i++) {
            // Update the rolling hash for the prefix
            prefixHash = (prefixHash * b + s.charAt(i)) % M;

            // Update the rolling hash for the suffix (from the right end)
            suffixHash = (suffixHash + s.charAt(s.length() - 1 - i) * powBase) % M;

            // Update the power of the base for the next suffix character
            powBase = (powBase * b) % M;

            // If prefix and suffix hashes match, update the length of the longest happy prefix
            if (prefixHash == suffixHash) {
                length = i + 1;
            }
        }

        // Return the actual substring (the longest happy prefix)
        return s.substring(0, length);
    }

    public static void main(String[] args) {
        LongestHappyPrefix solution = new LongestHappyPrefix();

        String[] testCases = {
                "rider",
                "ababab",
                "abcabc",
                "aabaacaabaa",
                "xyz",
                "aaaaa"
        };

        for (int i = 0; i < testCases.length; i++) {
            String testCase = testCases[i];
            String result = solution.longestPrefix(testCase);
            System.out.println((i + 1) + ".\ts: " + testCase);
            System.out.println("\tLongest Happy Prefix: " + result);
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}
