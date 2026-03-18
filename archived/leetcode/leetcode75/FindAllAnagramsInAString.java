package leetcode75;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
    }

    /**
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/
     */

    public static List<Integer> findAnagrams(String s, String p) {

        int n = s.length();
        int m = p.length();

        List<Integer> res = new ArrayList<>();

        if (m > n)
            return res;

        int[] fs = freq(s.substring(0, m));
        int[] fp = new int[26];
        for (char c : p.toCharArray()) {
            fp[c - 'a']++;
        }
        if (areSame(fp, fs)) {
            res.add(0);
        }

        for (int i = m; i < n; i++) {
            fs[s.charAt(i - m) - 'a']--;
            fs[s.charAt(i) - 'a']++;
            if (areSame(fp, fs)) {
                res.add(i - m + 1);
            }
        }

        return res;
    }

    private static boolean areSame(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
    }

    private static int[] freq(String s) {
        int[] fs = new int[26]; // size is 26 since a-z are allowed in given string
        for (char c : s.toCharArray()) {
            fs[c - 'a']++;
        }
        return fs;
    }

}
