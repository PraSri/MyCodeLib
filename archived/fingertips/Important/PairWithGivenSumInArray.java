package Important;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PairWithGivenSumInArray {

	public static void main(String[] args) {
//		getPair_v1(new int[] { 0, -1, 2, -3, 1 }, -2);
		getPair_v2(new int[] { 1, 1, 2, 45, 46, 46 }, 47);
//		getPair_v3(new int[] { 0, -1, 2, -3, 1 }, -2);
	}

	// Two pointer approach ..need to sort the array ..O(nlogn)
	public static void getPair_v1(int[] a, int x) {
		Arrays.sort(a);
		int n = a.length;
		int s = 0, e = n - 1;
		while (s < e) {
			if (a[s] + a[e] == x) {
//				System.out.println("index : " + s + " and " + e);
				System.out.println("values : " + a[s] + " and " + a[e]);
				s++;
				e--;
			} else if (a[s] + a[e] > x) {
				e--;
			} else {
				s++;
			}
		}
	}

	// hashing ..O(n)
	public static void getPair_v2(int[] a, int x) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (map.containsKey(x - a[i])) {
				System.out.println("index : " + i + " and " + map.get(x - a[i]));
				System.out.println("values : " + a[i] + " and " + a[map.get(x - a[i])]);
				set.add(Arrays.asList(a[i], a[map.get(x - a[i])]));
			}
			map.put(a[i], i);
		}
		System.out.println("count : " + set.size());
	}

	// hashing using set......O(n)
	public static void getPair_v3(int[] a, int x) {
		Set<Integer> set = new HashSet<Integer>();
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (set.contains(x - a[i])) {
				System.out.println("values : " + a[i] + " and " + (x - a[i]));
			}
			set.add(a[i]);
		}
	}

}
