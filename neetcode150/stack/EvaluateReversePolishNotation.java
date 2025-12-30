package stack;

import java.util.*;

/**Basic Calculator
https://leetcode.com/problems/basic-calculator/

Expression Add Operators
https://leetcode.com/problems/expression-add-operators/*/

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for(String s: tokens) {
            if(s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if(s.equals("-")) {
                Integer x = stack.pop();
                Integer y = stack.pop();
                stack.push(y - x);
            } else if(s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if(s.equals("/")) {
                Integer x = stack.pop();
                Integer y = stack.pop();
                stack.push(y/x);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }

        return stack.pop();
    }

    /**
     * <a href="https://leetcode.com/problems/basic-calculator/">LeetCode - Basic Calculator</a>
     */
    public static class BasicCalculator {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/expression-add-operators/">LeetCode - Expression Add Operators</a>
     */
    public static class ExpressionAddOperators {
        // placeholder
    }
}
