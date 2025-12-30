package stack;

import java.util.*;

public class ValidParentheses {


/**
 * Generate Parentheses (Medium)
https://leetcode.com/problems/generate-parentheses/

Longest Valid Parentheses (Hard)
https://leetcode.com/problems/longest-valid-parentheses/

Remove Invalid Parentheses (Hard)
https://leetcode.com/problems/remove-invalid-parentheses/

Check If Word Is Valid After Substitutions (Medium)
https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/

Check if a Parentheses String Can Be Valid (Medium)
https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/

Move Pieces to Obtain a String (Medium)
https://leetcode.com/problems/move-pieces-to-obtain-a-string/
*/

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for(char ch: s.toCharArray()) {

            if(ch == '(') {
                stack.push(')');
            } else if(ch == '[') {
                stack.push(']');
            } else if(ch == '{') {
                stack.push('}');
            } else {
                if(stack.isEmpty()) {
                    return false;
                } else {
                    char x = stack.pop();
                    if(x != ch) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * <a href="https://leetcode.com/problems/generate-parentheses/">LeetCode - Generate Parentheses</a>
     */
    public static class GenerateParentheses {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/longest-valid-parentheses/">LeetCode - Longest Valid Parentheses</a>
     */
    public static class LongestValidParentheses {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/remove-invalid-parentheses/">LeetCode - Remove Invalid Parentheses</a>
     */
    public static class RemoveInvalidParentheses {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/">LeetCode - Check If Word Is Valid After Substitutions</a>
     */
    public static class CheckIfWordIsValidAfterSubstitutions {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/">LeetCode - Check if a Parentheses String Can Be Valid</a>
     */
    public static class CheckIfAParenthesesStringCanBeValid {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/move-pieces-to-obtain-a-string/">LeetCode - Move Pieces to Obtain a String</a>
     */
    public static class MovePiecesToObtainAString {
        // placeholder
    }
}
