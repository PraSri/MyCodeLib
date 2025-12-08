package stack;

import java.util.*;

public class ValidParentheses {
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
}
