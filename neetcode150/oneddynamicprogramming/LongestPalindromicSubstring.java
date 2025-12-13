package oneddynamicprogramming;

public class LongestPalindromicSubstring {

    int start = 0, maxLen = 0;
    public String longestPalindrome(String s) {
        // "ababd"
        // "dbaba"
        int n = s.length();
        for(int i = 0; i<n; i++) {
            extend(s, i, i);
            extend(s, i, i+1);
        }
        return s.substring(start, start + maxLen);
    }

    private void extend(String s, int left, int right) {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if(maxLen < right-left-1) {
            maxLen = right - left - 1;
            start = left + 1;
        }
    }
}
