package stack;

import java.util.*;

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
}
