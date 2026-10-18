package dp_two_sequences.distinct_subsequences;

public class Solution {

    public int numDistinct(String s, String t) {
        int n = t.length();
        int[] ways = new int[n + 1];
        ways[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            for (int j = Math.min(i, n); j >= 1; j--) {
                if (c == t.charAt(j - 1)) {
                    ways[j] += ways[j - 1];
                }
            }
        }
        return ways[n];
    }
}
