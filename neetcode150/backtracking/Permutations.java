package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Next Permutation (Medium)
 * ? https://leetcode.com/problems/next-permutation/
 * <p>
 * Permutations II (Medium)
 * ? https://leetcode.com/problems/permutations-ii/
 * <p>
 * Permutation Sequence (Hard)
 * ? https://leetcode.com/problems/permutation-sequence/
 * <p>
 * Combinations (Medium)
 * ? https://leetcode.com/problems/combinations/
 */

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] pick = new boolean[nums.length];
        backtrack(nums, res, pick, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res,
                           boolean[] pick, List<Integer> temp) {
        // base case
        if (temp.size() >= nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!pick[i]) {
                temp.add(nums[i]);
                pick[i] = true;
                backtrack(nums, res, pick, temp);
                temp.remove(temp.size() - 1);
                pick[i] = false;
            }
        }
    }

    /**
     * Next Permutation
     * https://leetcode.com/problems/next-permutation
     */
    public static class NextPermutation {
    }

    /**
     * Permutations II
     * https://leetcode.com/problems/permutations-ii
     */
    public static class PermutationsIi {
    }

    /**
     * Permutation Sequence
     * https://leetcode.com/problems/permutation-sequence
     */
    public static class PermutationSequence {
    }

    /**
     * Combinations
     * https://leetcode.com/problems/combinations
     */
    public static class Combinations {
    }
}
