package backtracking.combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] cands, int start, int remaining, List<Integer> path, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < cands.length && cands[i] <= remaining; i++) {
            path.add(cands[i]);
            backtrack(cands, i, remaining - cands[i], path, result);
            path.remove(path.size() - 1);
        }
    }
}
