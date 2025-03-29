package dynamic_programming;

import java.util.* ;

public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        // Initialize dp
        List<List<List<Integer>>> dp = new ArrayList<>(target + 1);
        dp.add(new ArrayList<>());
        dp.get(0).add(new ArrayList<>());

        // For each value from 1 to target
        for (int i = 1; i <= target; i++) {
            dp.add(new ArrayList<>());
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {

                    // Checking previous results from dp
                    for (List<Integer> prev : dp.get(i - nums[j])) {
                        List<Integer> temp = new ArrayList<>(prev);
                        temp.add(nums[j]);
                        temp.sort(null);

                        // If the new combination is not already in dp
                        if (!dp.get(i).contains(temp)) {
                            dp.get(i).add(temp);
                        }
                    }
                }
            }
        }

        // Return the combinations
        return dp.get(target);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        back(0, target, candidates, res, new ArrayList<>(), 0);
        return res;
    }

    public void back(int sum, int target, int[] candidates, List<List<Integer>> res, List<Integer> temp, int start) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            sum = sum + candidates[i];
            back(sum, target, candidates, res, temp, i);
            temp.remove(temp.size() - 1);
            sum = sum - candidates[i];
        }
    }

}
