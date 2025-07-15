package dynamic_programming;

import java.util.Arrays;

// tc: O(n*m + m*T) sc: O(n*m + 26*T)
public class NumberOfWaysToFormTargetStringGivenADictionary {
    public int numWays(String[] words, String target) {
        int n = words.length;
        int m = words[0].length();
        int mod = 1_000_000_007;
        int[][] frequency = new int[m][26];
        for (String word : words) {
            for (int i = 0; i < m; i++) {
                frequency[i][word.charAt(i) - 'a']++;
            }
        }
        int[][] dp = new int[m][target.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return recurse(0, 0, target, frequency, dp, mod);
    }

    private int recurse(int i, int j, String target, int[][] frequency, int[][] dp, int mod) {
        if (j == target.length()) return 1;
        if (i == frequency.length) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        long count = recurse(i + 1, j, target, frequency, dp, mod);
        count %= mod;
        count += ((long) frequency[i][target.charAt(j) - 'a'] * recurse(i + 1, j + 1, target, frequency, dp, mod));
        count %= mod;
        return dp[i][j] = (int) count;
    }
}
