package stacks;

import java.util.Stack;

public class MinimumStringLengthAfterRemovingSubstrings {

    public static int minLength(String s) {
        Stack<Character> stack = new Stack<>();

        // Iterate over each character in the input string
        for (char currentChar : s.toCharArray()) {
            // If the stack is empty, simply push the current character
            if (stack.isEmpty()) {
                stack.push(currentChar);
                continue;
            }

            // Check for "AB" pattern, remove the pair by popping from the stack
            if (currentChar == 'B' && stack.peek() == 'A') {
                stack.pop();
            }
            // Check for "CD" pattern, remove the pair by popping from the stack
            else if (currentChar == 'D' && stack.peek() == 'C') {
                stack.pop();
            }
            // Otherwise, push the current character onto the stack
            else {
                stack.push(currentChar);
            }
        }

        return stack.size();
    }
}
