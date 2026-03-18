package slidingWindow;

public class LongestRepeatingCharacterReplacement {

    /**
     *
     * https://leetcode.com/problems/longest-repeating-character-replacement/
    * */

    public int characterReplacement(String st, int k) {
        int[] f = new int[26];
        int mostFreqLetter = 0;
        int s = 0;
        int e = 0;
        int n = st.length();
        int max = 0;

        while(e < n) {
            f[st.charAt(e)-'A']++;
            mostFreqLetter = Math.max(mostFreqLetter, f[st.charAt(e)-'A']);
            int changes = (e-s+1)-mostFreqLetter;
            if(changes > k) {
                f[st.charAt(s) - 'A']--;
                s++;
            }
            max = Math.max(max, (e-s+1));
            e++;
        }
        return max;
    }
}
