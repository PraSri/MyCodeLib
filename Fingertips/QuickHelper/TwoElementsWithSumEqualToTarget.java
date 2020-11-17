package QuickHelper;

import java.util.HashMap;
import java.util.Map;

public class TwoElementsWithSumEqualToTarget {

	public static void main(String[] args) {

	}

	/*******
	 * Given an array of integers nums and an integer target, return indices of the
	 * two numbers such that they add up to target.
	 * 
	 * You may assume that each input would have exactly one solution, and you may
	 * not use the same element twice.
	 * 
	 * You can return the answer in any order.
	 *********/
	
	public int[] twoSum(int[] nums, int target) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		int l = 0, m = 0;
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int x = target - nums[i];
			if (map.containsKey(x) && i != map.get(x)) {
				l = i;
				m = map.get(x);
				break;
			}
		}

		return new int[] { l, m };

	}

}
