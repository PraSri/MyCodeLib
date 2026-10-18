package expand_around_center.longest_palindromic_substring;

public class Solution {

    public String longestPalindrome(String s) {
        int bestStart = 0;
        int bestLen = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            int l = center / 2;
            int r = l + center % 2;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            int len = r - l - 1;
            if (len > bestLen) {
                bestLen = len;
                bestStart = l + 1;
            }
        }
        return s.substring(bestStart, bestStart + bestLen);
    }
}
