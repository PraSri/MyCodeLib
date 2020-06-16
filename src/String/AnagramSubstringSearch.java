package String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnagramSubstringSearch {

	public static void main(String[] args) {
		String A = "AAABABAA";
		String B = "AABA";
		for (int i : solve(A, B)) {
			System.out.print(i + " ");
		}
	}

	public static ArrayList<Integer> solve(String A, String B) {
		int n = A.length();
		int m = B.length();
		Map<Character, Integer> hm = new HashMap<>();
		Map<Character, Integer> a = new HashMap<>();
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			if (!hm.containsKey(B.charAt(i))) {
				hm.put(B.charAt(i), 1);
			} else {
				hm.put(B.charAt(i), hm.get(B.charAt(i)) + 1);
			}
		}
//		for (Map.Entry<Character, Integer> mp : hm.entrySet()) {
//			System.out.println(mp.getKey() + "->" + mp.getValue());
//		}
		int i = 0;
		int j;
		while (i <= n - m) {
			j = i;
			a.clear();
			int x = m;
			while (x > 0) {
				if (!hm.containsKey(A.charAt(j))) {
					break;
				} else {
					if (!a.containsKey(A.charAt(j))) {
						a.put(A.charAt(j), 1);
					} else {
						a.put(A.charAt(j), a.get(A.charAt(j)) + 1);
					}
//					System.out.println("*****************" + i + "*************" + j);
//					for (Map.Entry<Character, Integer> mp : a.entrySet()) {
//						System.out.println(mp.getKey() + "->" + mp.getValue());
//					}
				}
				j++;
				x--;
			}
			if (isFrequencySame(hm, a)) {
				res.add(i);
			}
//			j--;
			i++;;
		}
		return res;
	}

	private static boolean isFrequencySame(Map<Character, Integer> hm, Map<Character, Integer> a) {
		for (Map.Entry<Character, Integer> m : hm.entrySet()) {
			if (m.getValue() != a.get(m.getKey())) {
				return false;
			}
		}
		return true;
	}

}
