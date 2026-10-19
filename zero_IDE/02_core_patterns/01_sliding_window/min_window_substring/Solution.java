import java.util.*;

public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        int[] targetCounts = new int[128];
        for (char c : t.toCharArray()) {
            targetCounts[c]++;
        }

        int required = 0;
        for (int count : targetCounts) {
            if (count > 0) required++;
        }

        int[] windowCounts = new int[128];
        int formed = 0;
        int l = 0, r = 0;
        int minLen = Integer.MAX_VALUE;
        int startIdx = 0;

        while (r < s.length()) {
            char c = s.charAt(r);
            windowCounts[c]++;

            if (targetCounts[c] > 0 && windowCounts[c] == targetCounts[c]) {
                formed++;
            }

            // Contract window from left while window remains valid
            while (l <= r && formed == required) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    startIdx = l;
                }

                char leftChar = s.charAt(l);
                windowCounts[leftChar]--;
                if (targetCounts[leftChar] > 0 && windowCounts[leftChar] < targetCounts[leftChar]) {
                    formed--;
                }
                l++;
            }
            r++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);
    }
}
