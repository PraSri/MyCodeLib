package sliding_window.longest_substring_without_repeating_characters;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int[] nextStart = new int[128]; // index just after the last occurrence
        int best = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            l = Math.max(l, nextStart[c]);
            best = Math.max(best, r - l + 1);
            nextStart[c] = r + 1;
        }
        return best;
    }
}
