package sliding_window.minimum_window_substring;

public class Solution {

    public String minWindow(String s, String t) {
        if (t.isEmpty() || s.length() < t.length()) {
            return "";
        }
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int missing = t.length();
        int bestStart = 0;
        int bestLen = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < s.length(); r++) {
            if (need[s.charAt(r)]-- > 0) {
                missing--;
            }
            while (missing == 0) {
                if (r - l + 1 < bestLen) {
                    bestLen = r - l + 1;
                    bestStart = l;
                }
                if (++need[s.charAt(l++)] > 0) {
                    missing++;
                }
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }
}
