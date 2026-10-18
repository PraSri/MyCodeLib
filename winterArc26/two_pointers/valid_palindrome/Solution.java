package two_pointers.valid_palindrome;

public class Solution {

    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            char a = s.charAt(l);
            char b = s.charAt(r);
            if (!Character.isLetterOrDigit(a)) {
                l++;
            } else if (!Character.isLetterOrDigit(b)) {
                r--;
            } else {
                if (Character.toLowerCase(a) != Character.toLowerCase(b)) {
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }
}
