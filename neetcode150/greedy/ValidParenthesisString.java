package greedy;

/**
 * Special Binary String (Hard)
 * https://leetcode.com/problems/special-binary-string/
 * <p>
 * Check if a Parentheses String Can Be Valid (Medium)
 * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/
 **/

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        int n = s.length() - 1;
        int open = 0, close = 0;
        for (int i = 0; i <= n; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                open++;
            } else {
                open--;
            }

            if (s.charAt(n - i) == ')' || s.charAt(n - i) == '*') {
                close++;
            } else {
                close--;
            }

            if (open < 0 || close < 0) {
                return false;
            }

        }

        return true;
    }

    /**
     * Special Binary String
     * https://leetcode.com/problems/special-binary-string
     */
    public static class SpecialBinaryString {
    }

    /**
     * Check if a Parentheses String Can Be Valid
     * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid
     */
    public static class CheckIfAParenthesesStringCanBeValid {
    }
}
