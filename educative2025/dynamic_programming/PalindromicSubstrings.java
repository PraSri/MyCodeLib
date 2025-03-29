package dynamic_programming;

public class PalindromicSubstrings {

    public static int countPalindromicSubstrings(String s) {
        int count = 0;

        // Initialize a lookup table of dimensions len(s) * len(s)
        boolean[][] dp = new boolean[s.length()][s.length()];

        // Base case: A string with one letter is always a palindrome
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            count++;
        }

        // Base case: Substrings of two letters
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            count += dp[i][i + 1] ? 1 : 0;
        }

        // Substrings of lengths greater than 2
        /**
         * After these base cases, we check all substrings of lengths greater than two.
         * However, we only compare the first and the last characters.
         * The rest of the string is checked using the lookup table.
         * For example, for a given string “zing”, we want to check whether “zin” is a palindrome.
         * We’ll only compare ‘z’ and ‘n’ and check the value of dp[1][1],
         * which will tell whether the remaining string “i”, represented by s[1..1], is a palindrome.
         * We’ll take the logical AND of these two results and store it at dp[0][2] because “zin” is represented
         * by the substring s[0..2].
         * This way, we’ll avoid redundant computations and check all possible substrings using the lookup table.*/

        for (int length = 3; length <= s.length(); length++) {
            for (int i = 0, j = length - 1; j < s.length(); i++, j++) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                count += dp[i][j] ? 1 : 0;
            }
        }

        return count;
    }

}
