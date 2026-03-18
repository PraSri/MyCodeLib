import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IntersectionOfArray {

	public int[] intersect(int[] nums1, int[] nums2) {
		return intersect_v2(nums1, nums2);
	}

	public int[] intersect_v2(int[] a, int[] b) {

		/*
		 * freq map of array a
		 *
		 * 
		 **/

		List<Integer> res = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < a.length; i++) {
			if (map.containsKey(a[i])) {
				map.put(a[i], map.get(a[i]) + 1);
			} else {
				map.put(a[i], 1);
			}
		}

		for (int i = 0; i < b.length; i++) {
			if (map.containsKey(b[i]) && map.get(b[i]) > 0) {
				res.add(b[i]);
				map.put(b[i], map.get(b[i]) - 1);
			}
		}

		int[] ans = new int[res.size()];
		for (int i = 0; i < res.size(); i++) {
			ans[i] = res.get(i);
		}
		return ans;

	}

}
