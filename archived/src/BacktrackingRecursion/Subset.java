package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Subset {

	public static void main(String[] args) {
		for (ArrayList<Integer> a : subsets(new ArrayList<>(Arrays.asList(1, 2, 3)))) {
			System.out.println(a);
		}

		for (List<Integer> a : subsetsWithDup(new int[] { 1, 2, 3 })) {
//			System.out.println(a);
		}
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1])
				continue; // skip duplicates
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> op = new ArrayList<>();
		Collections.sort(A);
		subsetsUtil(A, op, ans, 0);
		return ans;
	}

	private static void subsetsUtil(ArrayList<Integer> in, ArrayList<Integer> op, ArrayList<ArrayList<Integer>> ans,
			int i) {
		ans.add(new ArrayList<>(op));
		for (int j = i; j < in.size(); j++) {
			if (j > i && in.get(j) == in.get(j - 1))
				continue;
			op.add(in.get(j));
			subsetsUtil(in, op, ans, j + 1);
			op.remove(op.size() - 1);
		}
	}

}
