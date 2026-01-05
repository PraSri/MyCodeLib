package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 * <p>
 * Combination Sum
 * https://leetcode.com/problems/combination-sum/
 * <p>
 * Binary Watch
 * https://leetcode.com/problems/binary-watch/
 * <p>
 * Count Number of Texts
 * https://leetcode.com/problems/count-number-of-texts/
 * <p>
 * Minimum Number of Pushes to Type Word I
 * https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/
 * <p>
 * Minimum Number of Pushes to Type Word II
 * https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/
 */

public class LetterCombinationsOfAPhoneNumber {

    Map<Character, String> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty())
            return res;
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        helper(res, digits, 0, "");
        return res;
    }

    private void helper(List<String> res, String digits, int i, String temp) {
        if (i >= digits.length()) {
            res.add(temp);
            return;
        }

        String curr = map.get(digits.charAt(i));

        for (int j = 0; j < curr.length(); j++) {
            helper(res, digits, i + 1, temp + curr.charAt(j));
        }

    }

    /**
     * Generate Parentheses
     * https://leetcode.com/problems/generate-parentheses
     */
    public static class GenerateParentheses {
    }

    /**
     * Combination Sum
     * https://leetcode.com/problems/combination-sum
     */
    public static class CombinationSum {
    }

    /**
     * Binary Watch
     * https://leetcode.com/problems/binary-watch
     */
    public static class BinaryWatch {
    }

    /**
     * Count Number of Texts
     * https://leetcode.com/problems/count-number-of-texts
     */
    public static class CountNumberOfTexts {
    }

    /**
     * Minimum Number of Pushes to Type Word I
     * https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i
     */
    public static class MinimumNumberOfPushesToTypeWordI {
    }

    /**
     * Minimum Number of Pushes to Type Word II
     * https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii
     */
    public static class MinimumNumberOfPushesToTypeWordIi {
    }
}
