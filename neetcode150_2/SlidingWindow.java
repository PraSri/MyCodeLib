import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SlidingWindow {

    // Best time to buy and sell the stock // took 2 mins but solution was wrong
    public int maxProfit(int[] a) {
        int max = -1;
        int l = 0;
        int r = l + 1;
        while (r < a.length) {
            if (a[l] < a[r]) {
                int profit = a[r] - a[l];
                max = Math.max(max, profit);
            } else {
                l = r;
            }
            r++;
        }
        return max;
    }


    // Longest Substring without repeating character
    //Input: s = "zxyzxyz"
    //
    //Output: 3
    // took 8 mins and wrong answer. Practice more

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int n = s.length();
        int maxLen = 0;
        int l = 0;
        for (int r = 0; r < n; r++) {
            // repeated char
            if (lastSeen.containsKey(s.charAt(r))) {
                int seenAt = lastSeen.get(s.charAt(r));
                l = Math.max(seenAt + 1, l);
            }
            lastSeen.put(s.charAt(r), r);
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }

    // Longest Repeating Character Replacement
    // Input: s = "XYYX", k = 2
    //
    //Output: 4

    // not able to think in 4mins, practice more
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> count = new HashMap<>();
        int res = 0;
        int l = 0;
        int maxf = 0;
        for (int r = 0; r < s.length(); r++) {
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
            maxf = Math.max(maxf, count.getOrDefault(s.charAt(r), 0));
            while ((r - l + 1) - maxf > k) {
                count.put(s.charAt(l), count.getOrDefault(s.charAt(l), 0) - 1);
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    // Permutation in String
    // spent 10 mins without solution, practice more.
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (m < n) {
            return false;
        }

        int[] f1 = new int[26];
        int[] f2 = new int[26];

        for (int i = 0; i < n; i++) {
            f1[s1.charAt(i) - 'a']++;
            f2[s2.charAt(i) - 'a']++;
        }

        int matches = 0;

        for (int i = 0; i < 26; i++) {
            if (f1[i] == f2[i]) {
                matches++;
            }
        }

        int l = 0;
        for (int r = s1.length(); r < m; r++) {
            if (matches == 26) {
                return true;
            }

            int index = s2.charAt(r) - 'a';
            f2[index]++;
            if (f2[index] == f1[index]) {
                matches++;
            } else if (f1[index] + 1 == f2[index]) {
                matches--;
            }

            index = s2.charAt(l) - 'a';
            f2[index]--;
            if (f1[index] == f2[index]) {
                matches++;
            } else if (f1[index] - 1 == f2[index]) {
                matches--;
            }
            l++;
        }

        return matches == 26;
    }

    // Minimum window substring
    // not able to reach to solution, keep track of this solution
    //Input: s = "OUZODYXAZV", t = "XYZ"
    //
    //Output: "YXAZ"
    // OUZODYX.AZV
    //
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[] f = new int[128];
        int l = 0;
        int r = 0;
        int counter = m;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        for (char c : t.toCharArray()) {
            f[c - 'A']++;
        }

        while (r < n) {
            if (f[s.charAt(r) - 'A'] > 0) {
                counter--;
            }
            f[s.charAt(r) - 'A']--;
            r++;
            while (counter == 0) {
                if (minLen > (r - l)) {
                    minLen = (r - l);
                    minStart = l;
                }
                f[s.charAt(l) - 'A']++;
                if (f[s.charAt(l) - 'A'] > 0) {
                    counter++;
                }
                l++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);

    }

    // Sliding window maximum
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
        Deque<Integer> q = new LinkedList<>();
        int l = 0;
        int r = 0;
        while (r < n) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            q.addLast(r);
            if (l > q.getFirst()) {
                q.removeFirst();
            }
            if ((r + 1) >= k) {
                output[l] = nums[q.getFirst()];
                l++;
            }
            r++;
        }
        return output;
    }


}
