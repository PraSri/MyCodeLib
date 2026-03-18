package backtracking;

import java.util.*;

public class RemoveInvalidParentheses {
    // tc: O(2^n)
    public List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();
        // count parenthesis needed to remove from left and right
        int leftToRemove = 0;
        int rightToRemove = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                leftToRemove++;
            else if (c == ')')
                if (leftToRemove > 0)
                    leftToRemove--;
                else rightToRemove++;
        }

        //backtracking
        backtrack(s, 0, 0, 0, new StringBuilder(), leftToRemove, rightToRemove, result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int openCount, int closedCount,
                           StringBuilder path, int leftRemain, int rightRemain, Set<String> result) {
        // base case when you reach the end of string
        if (index == s.length()) {
            if (leftRemain == 0 && rightRemain == 0 && openCount == closedCount) {
                result.add(path.toString());
            }
            return;
        }
        char ch = s.charAt(index);
        int len = path.length();
        if (ch == '(') {
            // 1. remove it
            if (leftRemain > 0) {
                backtrack(s, index + 1, openCount, closedCount, path, leftRemain - 1, rightRemain, result);
            }
            // 2. keep it
            path.append(ch);
            backtrack(s, index + 1, openCount + 1, closedCount, path, leftRemain, rightRemain, result);
            path.setLength(len); // backtrack
        } else if (ch == ')') {
            if (rightRemain > 0) {
                backtrack(s, index + 1, openCount, closedCount, path, leftRemain, rightRemain - 1, result);
            }
            if (closedCount < openCount) {
                path.append(ch);
                backtrack(s, index + 1, openCount, closedCount + 1, path, leftRemain, rightRemain, result);
                path.setLength(len);
            }

        } else {
            path.append(ch);
            backtrack(s, index + 1, openCount, closedCount, path, leftRemain, rightRemain, result);
            path.setLength(len);
        }
    }
}
