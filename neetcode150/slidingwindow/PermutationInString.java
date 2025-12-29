package slidingwindow;

public class PermutationInString {

    /**
     * 
     * Minimum Window Substring
https://leetcode.com/problems/minimum-window-substring/

Find All Anagrams in a String
https://leetcode.com/problems/find-all-anagrams-in-a-string/
     * 
    */

   //Fixed size ki sliding window use karo, 
   // aur har position pe check karo ki window 
   // ke andar s1 jaisa character frequency 
   // match ho raha hai ya nahi.

        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length()) {
                return false;
            }

            int[] s1Count = new int[26];
            int[] s2Count = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                s1Count[s1.charAt(i) - 'a']++;
                s2Count[s2.charAt(i) - 'a']++;
            }

            int matches = 0;
            for (int i = 0; i < 26; i++) {
                if (s1Count[i] == s2Count[i]) {
                    matches++;
                }
            }

            int l = 0;
            for (int r = s1.length(); r < s2.length(); r++) {
                if (matches == 26) {
                    return true;
                }
                //Naya Character Add Karo (Right Side)
                // Jab right pointer aage badhta hai:

                // Naya character ka count increment karo
                // Agar ab frequency match ho gayi → matches++
                // Agar pehle match thi, ab unmatch ho gayi (1 zyada ho gaya) → matches--
                int index = s2.charAt(r) - 'a';
                s2Count[index]++;
                if (s1Count[index] == s2Count[index]) {
                    matches++;
                } else if (s1Count[index] + 1 == s2Count[index]) {
                    matches--;
                }
                //Purana Character Hatao (Left Side)
                // Jab left pointer piche badhta hai:

                // Purana character ka count decrement karo
                // Agar ab frequency match ho gayi → matches++
                // Agar pehle match thi, ab unmatch ho gayi (1 zyada ho gaya) → matches--
                index = s2.charAt(l) - 'a';
                s2Count[index]--;
                if (s1Count[index] == s2Count[index]) {
                    matches++;
                } else if (s1Count[index] - 1 == s2Count[index]) {
                    matches--;
                }
                l++;
            }
            return matches == 26;
        }

    /**
     * <a href="https://leetcode.com/problems/minimum-window-substring/">LeetCode - Minimum Window Substring</a>
     */
    public static class MinimumWindowSubstring {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">LeetCode - Find All Anagrams in a String</a>
     */
    public static class FindAllAnagramsInAString {
        // placeholder
    }
}
