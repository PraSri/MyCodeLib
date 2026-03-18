package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Anagrams {

	public static void main(String[] args) {
		String[] A = new String[] { "cat", "dog", "god", "tca" };
		List<String> Ai = new ArrayList<>(Arrays.asList(A));
//		for (int i = 0; i < anagrams(A).length; i++) {
//			for (int j = 0; j < anagrams(A)[0].length; j++)
//				System.out.print(anagrams(A)[i][j] + " ");
//			System.out.println();
//		}
		anangramV2(Ai);
	}

	public static int[][] anagrams(final String[] A) {
		int[][] a;
		Set<Integer> s = new HashSet<>();
		Set<Integer> check = new HashSet<>();
		ArrayList<ArrayList<Integer>> l = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < A.length; i++) {
			s.clear();
			if (check.add(i + 1)) {
				for (int j = i; j < A.length; j++) {
					if (isAnagram(A[i], A[j])) {
						s.add(i + 1);
						s.add(j + 1);
						check.add(j + 1);
					}
				}
			}
			ArrayList<Integer> t = new ArrayList<>(s);
//			System.out.println(t);
			if (t.size() != 0)
				l.add(t);
		}
//		System.out.println("l.size() = " + l.size());
//		System.out.println("l.get(0).size() = " + l.get(0).size());
		a = new int[l.size()][10004];
		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < l.get(i).size(); j++) {
//				System.out.println(i + " " + j);
				a[i][j] = l.get(i).get(j);
			}
		}
		return a;
	}

	public static boolean isAnagram(String a, String b) {
		int la = a.length();
		int lb = b.length();
		if (la != lb)
			return false;
		int[] f = new int[30];
		for (int i = 0; i < la; i++) {
			f[a.charAt(i) - 'a'] += 1;
		}
		for (int i = 0; i < lb; i++) {
			f[b.charAt(i) - 'a']--;
		}
		for (int i = 0; i < f.length; i++) {
			if (f[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public static void anangramV2(List<String> A) {
		Map<String, List<Integer>> m = new LinkedHashMap<>();
		int i = 1;
		for (String x : A) {
			char[] f = new char[26];
			for (char c : x.toCharArray()) {
				f[c - 'a']++;
			}
			String keyString = String.valueOf(f);
//			System.out.println(keyString);
			if (!m.containsKey(keyString)) {
				m.put(keyString, new ArrayList<>());
			}
			m.get(keyString).add(i);
			i++;
		}
		for (Entry<String, List<Integer>> e : m.entrySet()) {
			System.out.println(e.getValue());
		}
		System.out.println(m.values());
	}

	public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
		Map<String, ArrayList<Integer>> m = new LinkedHashMap<>();
		int i = 1;
		for (String x : A) {
			char[] f = new char[26];
			for (char c : x.toCharArray()) {
				f[c - 'a']++;
			}
			String keyString = String.valueOf(f);
//			System.out.println(keyString);
			if (!m.containsKey(keyString)) {
				m.put(keyString, new ArrayList<>());
			}
			m.get(keyString).add(i);
			i++;
		}
		ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		for (Entry<String, ArrayList<Integer>> e : m.entrySet()) {
			a.add(new ArrayList<>(e.getValue()));
		}
// 		System.out.println(m.values());

		return a;
	}

}
