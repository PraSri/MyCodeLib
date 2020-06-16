package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {

	public static void main(String[] args) {

		List<String> A = Arrays.asList("caat", "taac", "dog", "god", "acta", "dog");
		for (ArrayList<Integer> a : BestSolution.anagrams(A)) {
			for (int i : a) {
				System.out.print(i + " ");
			}
			System.out.println();
		}

	}

	/* THIS SOLUTION FAILS WHEN INPUT AS DUPLICATE STRINGS **/

	public static ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		anagrams(res, A);

		return res;
	}

	private static void anagrams(ArrayList<ArrayList<Integer>> res, List<String> a) {
		Map<String, Map<Character, Integer>> m = new LinkedHashMap();
		Map<String, Integer> lookup = new HashMap<>();
		for (int i = 0; i < a.size(); i++) {
			lookup.put(a.get(i), i);
		}
		for (String s : lookup.keySet()) {
			System.out.println(s + "--" + lookup.get(s));
		}
		buildFrequencyMap(m, a);
		for (int i = 0; i < a.size(); i++) {
			String s = a.get(i);
			if (lookup.get(s) != -1) {
				ArrayList<Integer> subres = new ArrayList<>();
				subres.add(i + 1);
				for (Map.Entry<String, Map<Character, Integer>> me : m.entrySet()) {
					if (!me.getKey().equals(s) && me.getKey().length() == s.length()) {
						if (me.getValue().equals(m.get(s))) {
							subres.add(lookup.get(me.getKey()) + 1);
							lookup.put(me.getKey(), -1);
						}
					}
				}
				res.add(subres);
			}
		}
	}

	private static void buildFrequencyMap(Map<String, Map<Character, Integer>> m, List<String> a) {

		for (String s : a) {
			Map<Character, Integer> f = new HashMap<>();
			for (int i = 0; i < s.length(); i++) {
				if (!f.containsKey(s.charAt(i))) {
					f.put(s.charAt(i), 1);
				} else {
					f.put(s.charAt(i), f.get(s.charAt(i)) + 1);
				}
			}
			m.put(s, f);
		}

	}

	/*
	 * THIS SOLUTION WORKS FOR DUPLICATE STRINGS AND FULLY CORRECT SOLUTION
	 * 
	 * BUT COMPLEXITY IS O(N*NLOGN) SINCE IT IS SORTING THE STRING EVERYTIME
	 */

	public static class BetterSolution {

		public static ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {

			Map<String, List<Integer>> map = new HashMap<>();
			int i = 0;
			for (String s : A) {
				char[] c = s.toCharArray();
				Arrays.sort(c);
				String key = String.valueOf(c);
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<>());
				}
				map.get(key).add(i + 1);
				i++;
			}

			return new ArrayList(map.values());
		}
	}

	/*
	 * BUILT THE KEY USING CHAR[26] ARRAY WHICH WILL IMPROVE COMPLEXITY FROM
	 * O(NLOGN) OF SORT TO O(N)
	 */

	public static class BestSolution {
		public static ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {

			Map<String, List<Integer>> map = new HashMap<>();
			int i = 0;
			for (String s : A) {
				char[] c = new char[26];
				for (char ch : s.toCharArray()) {
					c[ch - 'a']++;
				}
				String key = String.valueOf(c);
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<>());
				}
				map.get(key).add(i + 1);
				i++;
			}

			return new ArrayList(map.values());
		}
	}

}
