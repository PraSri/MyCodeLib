package oneddynamicprogramming;

public class PalindromicSubstrings {
    int count = 0;
    public int countSubstrings(String s) {
        int n = s.length();
        for(int i = 0; i<n; i++) {
            extend(s, i, i);
            extend(s, i, i+1);
        }
        return count;
    }

    private void extend(String s, int left, int right) {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
    }
}
