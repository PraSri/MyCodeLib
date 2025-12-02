package slidingwindow;

import java.util.*;

public class LongestRepeatingCharacterReplacement {

    //Input: s = "XYYX", k = 2
    //
    //Output: 4

    //“Sabse zyada repeat hone wale character ko lock kar do,
    // aur baaki ko replace samjho.
    // Replace zyada ho jaye toh window shrink karo.”

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

}
