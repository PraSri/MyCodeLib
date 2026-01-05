package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subsets II (Medium)
 * https://leetcode.com/problems/subsets-ii/
 * <p>
 * Generalized Abbreviation (Medium)
 * https://leetcode.com/problems/generalized-abbreviation/
 * <p>
 * Letter Case Permutation (Medium)
 * https://leetcode.com/problems/letter-case-permutation/
 * <p>
 * Find Array Given Subset Sums (Hard)
 * https://leetcode.com/problems/find-array-given-subset-sums/
 * <p>
 * Count Number of Maximum Bitwise-OR Subsets (Medium)
 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
 */

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(nums, 0, res, subset);
        return res;
    }

    private void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> subset) {
        // base case
        // we have processed all the elements
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        // choose curr element
        subset.add(nums[i]);
        dfs(nums, i + 1, res, subset);
        // backtrack
        // skip curr element
        subset.remove(subset.size() - 1);
        dfs(nums, i + 1, res, subset);
    }

    public static class SubsetsII {
        
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> subset = new ArrayList<>();
            Arrays.sort(nums);
            dfs(nums, 0, res, subset);
            return res;
        }

        private void dfs(int[] nums, int i,
                         List<List<Integer>> res, List<Integer> subset) {
            // base case
            // we have processed all the elements
            if (i >= nums.length) {
                res.add(new ArrayList<>(subset));
                return;
            }

            // choose curr element
            subset.add(nums[i]);
            dfs(nums, i + 1, res, subset);
            // backtrack
            // skip curr element
            subset.remove(subset.size() - 1);
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
            dfs(nums, i + 1, res, subset);
        }
    }

    /**
     * Subsets II
     * https://leetcode.com/problems/subsets-ii
     */
    public static class SubsetsIi {
    }

    /**
     * Generalized Abbreviation
     * https://leetcode.com/problems/generalized-abbreviation
     */
    public static class GeneralizedAbbreviation {
    }

    /**
     * Letter Case Permutation
     * https://leetcode.com/problems/letter-case-permutation
     */
    public static class LetterCasePermutation {
    }

    /**
     * Find Array Given Subset Sums
     * https://leetcode.com/problems/find-array-given-subset-sums
     */
    public static class FindArrayGivenSubsetSums {
    }

    /**
     * Count Number of Maximum Bitwise-OR Subsets
     * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets
     */
    public static class CountNumberOfMaximumBitwiseOrSubsets {
    }
}
