package GoldmanSachs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDifferenceOfArrays {

	public static void main(String[] args) {
		System.out.println(findDiff(new int[] { 1, 2, 3, 4, 5, 5 }, new int[] { 1, 2, 3, 5, 3 }));
	}

	public static List<Integer> findDiff(int[] first, int[] second) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int value : first) {
			int current = count.getOrDefault(value, 0);
			count.put(value, current + 1);
		}
		for (int value : second) {
			int current = count.getOrDefault(value, 0);
			count.put(value, current - 1);
		}
		List<Integer> result = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			int diff = entry.getValue();
			int times = Math.abs(diff);
			for (int i = 0; i < times; i++) {
				result.add(entry.getKey());
			}
		}
		return result;
	}
}
