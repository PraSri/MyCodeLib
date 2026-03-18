package stacks;

import java.util.*;

public class RemoveAdjacent {

    public static String removeDuplicates(String s) {
        // Create an empty stack.
        Stack<Character> stack = new Stack<>();

        // Iterare over the string
        for (char c : s.toCharArray()) {
            // If stack has at least one character and
            // stack's top character is same as the string's character.
            if (!stack.isEmpty() && stack.peek() == c) {
                // Pop a character from the stack.
                stack.pop();
            } else {
                // Otherwise, push that character onto the stack.
                stack.push(c);
            }
        }

        // Initialize a StringBuilder to construct the result string
        StringBuilder result = new StringBuilder();
        // Pop all characters from the stack and append them to the StringBuilder
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        // Since the characters were added in reverse order,
        // reverse the StringBuilder to get the final result
        return result.reverse().toString();
    }

    // Driver code
    public static void main(String[] args) {
        String[] inputs = {"g", "ggaabcdeb", "abbddaccaaabcd", "aannkwwwkkkwna", "abbabccblkklu"};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println((i + 1) + ".\tRemove duplicates from string: '" + inputs[i] + "'");
            String resultingString = removeDuplicates(inputs[i]);
            System.out.println("\tString after removing duplicates: " + resultingString);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

}
