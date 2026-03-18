package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDistinctIntegers {
	
	public static void main(String[] args) {
		for (List<Integer> a : subsets(new int[] { 1, 2, 3 })) {
			System.out.println(a);
		}
	}

	/*****
	 * 
	 * Given an integer array nums, return all possible subsets (the power set).
	 * 
	 * The solution set must not contain duplicate subsets.
	 * 
	 *********/

	/***
	 * Input: nums = [1,2,3] Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
	 ****/

	public static List<List<Integer>> subsets(int[] nums) {

		List<Integer> sl = new ArrayList<Integer>();
		List<List<Integer>> l = new ArrayList<>();

		Arrays.sort(nums);
		
		subsetsUtil(nums, sl, l, 0);

		return l;

	}

	private static void subsetsUtil(int[] nums, List<Integer> sl, List<List<Integer>> l, int i) {

		l.add(new ArrayList<Integer>(sl));

		for (int index = i; index < nums.length; index++) {

			sl.add(nums[index]);

			subsetsUtil(nums, sl, l, index + 1);

			sl.remove(sl.size() - 1);

		}

	}

}
