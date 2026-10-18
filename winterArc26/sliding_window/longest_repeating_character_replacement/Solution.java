package sliding_window.longest_repeating_character_replacement;

public class Solution {

    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int maxFreq = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(r) - 'A']);
            if (r - l + 1 - maxFreq > k) {
                count[s.charAt(l++) - 'A']--;
            }
        }
        return s.length() - l;
    }
}
