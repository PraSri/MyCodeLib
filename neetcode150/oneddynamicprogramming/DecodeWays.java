package oneddynamicprogramming;

import java.util.Arrays;

/**
 * Decode Ways II (Hard)
 * ? https://leetcode.com/problems/decode-ways-ii/
 * <p>
 * Number of Ways to Separate Numbers (Hard)
 * ? https://leetcode.com/problems/number-of-ways-to-separate-numbers/
 * <p>
 * Count Number of Texts (Medium)
 * ? https://leetcode.com/problems/count-number-of-texts/
 */

public class DecodeWays {

    public int numDecodings(String s) {

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);

        return dfs(0, s, dp);
    }

    private int dfs(int i, String s, int[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }

        if (i >= s.length()) {
            return 1;
        }

        if (s.charAt(i) == '0') {
            return 0;
        }

        int res = dfs(i + 1, s, dp);

        if (i < s.length() - 1) {
            if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                res += dfs(i + 2, s, dp);
            }
        }

        return dp[i] = res;
    }


    /**
     * Decode Ways II
     * https://leetcode.com/problems/decode-ways-ii
     */
    public static class DecodeWaysIi {
    }

    /**
     * Number of Ways to Separate Numbers
     * https://leetcode.com/problems/number-of-ways-to-separate-numbers
     */
    public static class NumberOfWaysToSeparateNumbers {
    }

    /**
     * Count Number of Texts
     * https://leetcode.com/problems/count-number-of-texts
     */
    public static class CountNumberOfTexts {
    }
}
