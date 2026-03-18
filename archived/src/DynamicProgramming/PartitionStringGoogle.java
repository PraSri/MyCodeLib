package DynamicProgramming;

import java.util.Arrays;

public class PartitionStringGoogle {

    static int mod = (int) (1e9 + 7);

    /**
     * https://leetcode.com/discuss/interview-question/2257966/Google-OA
     * Partition String
     * You are given a string S of lenght N of digits 0 - 9. You need to partiton strings into K substrings such that
     *
     * Each substring has a minimum lenght of M
     * Substring must start with even digit and ends with odd digit number
     * Determine the number of ways to partitioin the strings which satisfy the above condition
     * You should find answer modulo 1e9 + 7
     *
     * constraints :
     * 1 <= n<= 2x10^3
     * 1<= m<= n
     * 1<=k<=n
     *
     * Test cases
     *
     * n = 9
     * m= 2
     * k = 3
     * s = '232387421'
     * So there are total 3 ways possible 
     *
     *
     * 232387421
     * 23 23 87421
     * 2323 87 421
     * 23 2387 421
     *
     */

    public static void main(String[] args) {
        String s = "232387421";
        int m = 2;
        int k = 3;
        int[][] dp = new int[s.length()][k + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        int ans = solve(s, 0, m, k, dp);

        System.out.print(ans + " ");
    }

    static int solve(String s, int idx, int m, int k, int[][] dp) {

        if (idx == s.length()) {
            if (k == 0) {
                return 1;
            }
            return 0;
        }

        if (k < 0) {
            return 0;
        }

        if (dp[idx][k] != -1) {
            return dp[idx][k];
        }

        int res = 0;

        StringBuilder temp = new StringBuilder();

        for (int i = idx; i < s.length(); i++) {

            temp.append(s.charAt(i));

            if (
                    temp.length() >= m &&
                            ((temp.charAt(0) - '0') % 2 == 0) &&
                            ((temp.charAt(temp.length() - 1) - '0') % 2) == 1
            ) {
                res = (res + solve(s, i + 1, m, k - 1, dp)) % mod;
            }
        }

        return dp[idx][k] = res;
    }

}
