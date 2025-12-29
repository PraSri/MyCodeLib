package slidingwindow;

import java.util.*;

public class LongestRepeatingCharacterReplacement {

    /**
     * 
     * Longest Substring with At Most K Distinct Characters
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

Max Consecutive Ones III
https://leetcode.com/problems/max-consecutive-ones-iii/

Minimum Number of Operations to Make Array Continuous
https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/

Maximize the Confusion of an Exam
https://leetcode.com/problems/maximize-the-confusion-of-an-exam/

Longest Substring of One Repeating Character
https://leetcode.com/problems/longest-substring-of-one-repeating-character/
     * 
     * 
     * 
    */

    //Input: s = "XYYX", k = 2
    //
    //Output: 4

    //�Sabse zyada repeat hone wale character ko lock kar do,
    // aur baaki ko replace samjho.
    // Replace zyada ho jaye toh window shrink karo.�

    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> count = new HashMap<>();
        int res = 0;

        // maxf -> most frequent char count
        // iske alwa jo hai, unko replace krna hai
        int l = 0, maxf = 0;
        for (int r = 0; r < s.length(); r++) {
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
            maxf = Math.max(maxf, count.get(s.charAt(r)));
            // ye replace hum k se jada nhi kr skte
            // toh hum window choti krege
            while ((r - l + 1) - maxf > k) {
                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
                l++;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    /**
     * <a href="https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/">LeetCode - Longest Substring with At Most K Distinct Characters</a>
     */
    public static class LongestSubstringWithAtMostKDistinctCharacters {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/max-consecutive-ones-iii/">LeetCode - Max Consecutive Ones III</a>
     */
    public static class MaxConsecutiveOnesIII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/">LeetCode - Minimum Number of Operations to Make Array Continuous</a>
     */
    public static class MinimumNumberOfOperationsToMakeArrayContinuous {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximize-the-confusion-of-an-exam/">LeetCode - Maximize the Confusion of an Exam</a>
     */
    public static class MaximizeTheConfusionOfAnExam {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/longest-substring-of-one-repeating-character/">LeetCode - Longest Substring of One Repeating Character</a>
     */
    public static class LongestSubstringOfOneRepeatingCharacter {
        // placeholder
    }
}
