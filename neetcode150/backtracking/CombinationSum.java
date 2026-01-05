package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Letter Combinations of a Phone Number (Medium)
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * <p>
 * Combination Sum II (Medium)
 * https://leetcode.com/problems/combination-sum-ii/
 * <p>
 * Combinations (Medium)
 * https://leetcode.com/problems/combinations/
 * <p>
 * Combination Sum III (Medium)
 * https://leetcode.com/problems/combination-sum-iii/
 * <p>
 * Factor Combinations (Medium)
 * https://leetcode.com/problems/factor-combinations/
 * <p>
 * Combination Sum IV (Medium)
 * https://leetcode.com/problems/combination-sum-iv/
 * <p>
 * The Number of Ways to Make the Sum (Medium)
 * https://leetcode.com/problems/the-number-of-ways-to-make-the-sum/
 */

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(nums, 0, res, subset, target);
        return res;
    }

    private void dfs(int[] nums, int i,
                     List<List<Integer>> res, List<Integer> subset, int t) {
        // base case
        // found a valid combination
        if (t == 0) {
            res.add(new ArrayList<>(subset));
            return;
        }
        // invalid path: sum too big or out of numbers
        if (t < 0 || i >= nums.length) {
            return;
        }

        // choose curr
        subset.add(nums[i]);
        // we don't move to next number, allow re-use of same number
        dfs(nums, i, res, subset, t - nums[i]);

        // backtrack, don't use this curr
        subset.remove(subset.size() - 1);
        dfs(nums, i + 1, res, subset, t);
    }

    public static class CombinationSumII {
        // TC: O(n*2^n), SC: O(n)
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> subset = new ArrayList<>();
            Arrays.sort(candidates);
            dfs(candidates, 0, res, subset, target);
            return res;
        }

        private void dfs(int[] nums, int i,
                         List<List<Integer>> res, List<Integer> subset, int t) {
            // base case
            // found a valid combination
            if (t == 0) {
                res.add(new ArrayList<>(subset));
                return;
            }
            // invalid path: sum too big or out of numbers
            if (t < 0 || i >= nums.length) {
                return;
            }

            // choose curr
            subset.add(nums[i]);
            // we don't move to next number, allow re-use of same number
            dfs(nums, i + 1, res, subset, t - nums[i]);

            // backtrack, don't use this curr
            subset.remove(subset.size() - 1);
            // avoid dups to get picked again
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
            dfs(nums, i + 1, res, subset, t);
        }
    }

    /**
     * Letter Combinations of a Phone Number
     * https://leetcode.com/problems/letter-combinations-of-a-phone-number
     */
    public static class LetterCombinationsOfAPhoneNumber {
    }

    /**
     * Combination Sum II
     * https://leetcode.com/problems/combination-sum-ii
     */
    public static class CombinationSumIi {
    }

    /**
     * Combinations
     * https://leetcode.com/problems/combinations
     */
    public static class Combinations {
    }

    /**
     * Combination Sum III
     * https://leetcode.com/problems/combination-sum-iii
     */
    public static class CombinationSumIii {
    }

    /**
     * Factor Combinations
     * https://leetcode.com/problems/factor-combinations
     */
    public static class FactorCombinations {
    }

    /**
     * Combination Sum IV
     * https://leetcode.com/problems/combination-sum-iv
     */
    public static class CombinationSumIv {
    }

    /**
     * The Number of Ways to Make the Sum
     * https://leetcode.com/problems/the-number-of-ways-to-make-the-sum
     */
    public static class TheNumberOfWaysToMakeTheSum {
    }
}
