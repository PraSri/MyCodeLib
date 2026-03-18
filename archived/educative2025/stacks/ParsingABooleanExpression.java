package stacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParsingABooleanExpression {

    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == ')') {

                List<Character> values = new ArrayList<>();
                while (stack.peek() != '(') {
                    values.add(stack.pop());
                }
                stack.pop();
                char op = stack.pop();

                char result = evaluateSubExpr(op, values);
                stack.push(result);

            } else if (c != ',') {
                stack.push(c);
            }
        }
        return stack.peek() == 't';
    }

    private char evaluateSubExpr(char op, List<Character> values) {
        if (op == '!') {
            return values.get(0) == 't' ? 'f' : 't';
        }

        if (op == '&') {
            for (char value : values) {
                if (value == 'f')
                    return 'f';
            }
            return 't';
        }

        if (op == '|') {
            for (char value : values) {
                if (value == 't') {
                    return 't';
                }
            }
            return 'f';
        }
        return 'f';
    }

}
