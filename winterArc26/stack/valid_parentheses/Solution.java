package stack.valid_parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public boolean isValid(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }
        Deque<Character> expected = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(' -> expected.push(')');
                case '[' -> expected.push(']');
                case '{' -> expected.push('}');
                default -> {
                    if (expected.isEmpty() || expected.pop() != c) {
                        return false;
                    }
                }
            }
        }
        return expected.isEmpty();
    }
}
