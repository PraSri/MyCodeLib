package backtracking.letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int i, StringBuilder path, List<String> result) {
        if (i == digits.length()) {
            result.add(path.toString());
            return;
        }
        for (char c : KEYS[digits.charAt(i) - '0'].toCharArray()) {
            path.append(c);
            backtrack(digits, i + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
