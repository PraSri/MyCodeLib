package stacks;

import java.util.Stack;

public class DecodeString {
    public static String decodeString(String s) {

        // Initialize two stacks: one for numbers (repeat counts) and one for strings
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        // Initialize an empty string to hold the current decoded segment
        StringBuilder current = new StringBuilder();

        // Initialize k to accumulate multi-digit numbers for repeat counts
        int k = 0;

        // Loop through each character in the input string s
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // If the character is a digit, update k to accumulate the multi-digit number
                k = 10 * k + (c - '0');
            } else if (c == '[') {
                // When encountering '[', push k onto the count stack and current onto the string stack
                countStack.push(k);
                stringStack.push(current.toString());

                // Reset k and current for the new segment inside the brackets
                k = 0;
                current.setLength(0);
            } else if (c == ']') {
                // When encountering ']', pop the last string and count from the stacks
                StringBuilder poppedString = new StringBuilder(stringStack.pop());
                int num = countStack.pop();

                // Repeat the current string num times and append it to the popped string
                for (int i = 0; i < num; i++) {
                    poppedString.append(current);
                }
                current = poppedString;
            } else {
                // For regular characters, append them to the current string
                current.append(c);
            }
        }

        // Return the fully decoded string after processing all characters
        return current.toString();
    }

    public static void main(String[] args) {
        String[] s = {"3[a]2[bc]", "3[a2[char]]", "2[abc]3[cd]ef", "10[a]", "2[ab3[cd]]4[e]"};
        for (int i = 0; i < s.length; i++) {
            System.out.println((i + 1) + ".\tString: " + s[i]);
            System.out.println("\tDecoded string: " + decodeString(s[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
