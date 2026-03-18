package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetWithDuplicatesIntegers {

	public static void main(String[] args) {
		
		for (List<Integer> a : subsetsWithDup(new int[] { 1, 2, 2 })) {
			System.out.println(a);
		}

	}

	/****
	 * 
	 * 
	 * Given a collection of integers that might contain duplicates, nums, return
	 * all possible subsets (the power set).
	 * 
	 * 
	 * Input: [1,2,2] Output: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
	 * 
	 * 
	 ***************/
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<Integer> sl = new ArrayList<Integer>();
		List<List<Integer>> l = new ArrayList<>();

		Arrays.sort(nums);

		subsetsWithDupUtil(nums, sl, l, 0);

		return l;

	}

	private static void subsetsWithDupUtil(int[] nums, List<Integer> sl, List<List<Integer>> l, int i) {
		l.add(new ArrayList<Integer>(sl));

		for (int index = i; index < nums.length; index++) {

			// skip duplicates

			if (index > i && nums[index] == nums[index - 1])
				continue;

			sl.add(nums[index]);

			subsetsWithDupUtil(nums, sl, l, index + 1);

			sl.remove(sl.size() - 1);

		}
	}
}
