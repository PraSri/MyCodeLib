package stack.evaluate_reverse_polish_notation;

public class Solution {

    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = -1;
        for (String token : tokens) {
            if (token.length() == 1 && "+-*/".indexOf(token.charAt(0)) >= 0) {
                int b = stack[top--];
                int a = stack[top--];
                stack[++top] = switch (token.charAt(0)) {
                    case '+' -> a + b;
                    case '-' -> a - b;
                    case '*' -> a * b;
                    default -> a / b;
                };
            } else {
                stack[++top] = Integer.parseInt(token);
            }
        }
        return stack[top];
    }
}
