package arraysandhashing;

import java.util.*;

class ValidAnagram {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int x : count) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }

    static class GroupAnagrams {

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String s : strs) {
                int[] f = new int[26];
                for (char ch : s.toCharArray()) {
                    f[ch - 'a']++;
                }
                String key = Arrays.toString(f);
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(s);
            }
            return new ArrayList<>(map.values());
        }
    }

    //**Palindrome Permutation**
    //
    //Given a string `s`, return `true` if a permutation of the string could form a palindrome, and `false` otherwise.
    //
    //A palindrome is a string that reads the same forward and backward.
    //A permutation is a rearrangement of the characters in the string.
    //
    //---
    //
    //**Example 1:**
    //
    //```
    //Input: s = "code"
    //Output: false
    //```
    //
    //**Example 2:**
    //
    //```
    //Input: s = "aab"
    //Output: true
    //```
    //
    //**Example 3:**
    //
    //```
    //Input: s = "carerac"
    //Output: true
    //```
    //
    //---
    //
    //**Constraints:**
    //
    //* `1 <= s.length <= 5000`
    //* `s` consists of lowercase English letters.

    static class PalindromePermutation {

        //Palindrome me max ek character ki frequency odd ho sakti hai — baaki sab even honi chahiye.

        public boolean canPermutePalindrome(String s) {
            int[] freq = new int[26];

            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            int oddCount = 0;
            for (int count : freq) {
                if ((count & 1) == 1) {   // odd frequency
                    oddCount++;
                    if (oddCount > 1) return false;
                }
            }

            return true;
        }

    }

    static class FindAllAnagramsInAString {
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

    static class FindResultantArrayAfterRemovingAnagrams {

        public List<String> removeAnagrams(String[] words) {
            Stack<String> stack = new Stack<>();

            for (int i = words.length - 1; i >= 0; i--) {

                String s = words[i];

                while (!stack.isEmpty() && anagram(stack.peek(), s)) stack.pop();

                stack.push(s);
            }
            List<String> res = new ArrayList<>();
            while (!stack.isEmpty()) res.add(stack.pop());
            return res;
        }

        public boolean anagram(String p, String q) {
            int[] arr = new int[26];
            for (char i : p.toCharArray()) arr[i - 'a']++;
            for (char i : q.toCharArray()) arr[i - 'a']--;
            for (int i : arr) if (i != 0) return false;
            return true;
        }

    }
}
