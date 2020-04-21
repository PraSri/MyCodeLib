package Hashing;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		System.out.println(longestConsecutive(new int[] { 100, 4, 200, 1, 3, 2 }));
	}

//	public static int longestConsecutive(final int[] A) {
//		int n = A.length;
//		
//		for (int i = 0; i < n; i++) {
//			f[]
//		}
//
//	}

	public static int longestConsecutive(int[] nums) {
		Map<Integer, Integer> ranges = new HashMap<>();
		int max = 0;
		for (int num : nums) {
			if (ranges.containsKey(num))
				continue;

			// 1.Find left and right num
			int left = ranges.getOrDefault(num - 1, 0);
			int right = ranges.getOrDefault(num + 1, 0);
			int sum = left + right + 1;
//			System.out.println(num + " " + " left=  " + left + " right = " + right + " sum= " + sum);
			max = Math.max(max, sum);

			// 2.Union by only updating boundary
			// Leave middle k-v dirty to avoid cascading update
			if (left > 0)
				ranges.put(num - left, sum);
			if (right > 0)
				ranges.put(num + right, sum);
			ranges.put(num, sum); // Keep each number in Map to de-duplicate
		}
		return max;
	}

}
