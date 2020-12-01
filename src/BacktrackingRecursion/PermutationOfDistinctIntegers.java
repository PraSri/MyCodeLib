package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PermutationOfDistinctIntegers {

//	https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)

	public static void main(String[] args) {

		for (List<Integer> a : permute(new int[] { 1, 2, 3 })) {
			for (int x : a) {
				System.out.print(x);
			}
			System.out.println();
		}

	}

	public static List<List<Integer>> permute(int[] nums) {

		List<Integer> sl = new ArrayList<Integer>();
		List<List<Integer>> l = new ArrayList<>();
		boolean usingSet = true;
		if (usingSet) {
			Set<Integer> set = new LinkedHashSet<Integer>();
			permuteUtilSet(nums, set, l);

		} else
			permuteUtil(nums, sl, l);

		return l;
	}

	private static void permuteUtilSet(int[] nums, Set<Integer> set, List<List<Integer>> l) {

		if (nums.length == set.size())
			l.add(new ArrayList<Integer>(set));

		for (int i = 0; i < nums.length; i++) {

			if (set.contains(nums[i]))
				continue;

			set.add(nums[i]);

			permuteUtilSet(nums, set, l);

			set.remove(getLast(set));

		}

	}

	private static Integer getLast(Set<Integer> set) {
		return set.stream().skip(set.size() - 1).findFirst().get();
	}

	private static void permuteUtil(int[] nums, List<Integer> sl, List<List<Integer>> l) {

		if (nums.length == sl.size()) {
			l.add(new ArrayList<Integer>(sl));
		}

		for (int i = 0; i < nums.length; i++) {

			if (sl.contains(nums[i]))
				continue;

			sl.add(nums[i]);

			permuteUtil(nums, sl, l);

			sl.remove(sl.size() - 1);

		}

	}

}
