package String;

import java.util.ArrayList;

public class CompareVersionNumbers {

	public static void main(String[] args) {
		System.out.println(compareVersion_v3("1.0", "1"));
//		System.out.println("01".compareTo("1"));
//		System.out.println(removeLeadingZeros("01"));
	}

	public static int compareVersion(String A, String B) {
		String[] a = A.split("\\.");
		String[] b = B.split("\\.");
		int la = a.length;
		int lb = b.length;
//		System.out.println(la + lb);
		int i = 0, j = 0;
		int n = Math.max(la, lb);
		while (i < n) {
			if (Integer.parseInt(a[i]) < Integer.parseInt(b[j])) {
				return -1;
			} else if (Integer.parseInt(a[i]) > Integer.parseInt(b[j])) {
				return 1;
			} else {
				i++;
				j++;
			}
		}
		if (i > j) {
			return 1;
		} else if (j > i) {
			return -1;
		}
		System.out.println("i=" + i + " j= " + j);
		return 0;
	}

	public static int compareVersion_v2(String A, String B) {

		int v1 = 0, v2 = 0;
		int la = A.length();
		int lb = B.length();
		for (int i = 0, j = 0; (i < la || j < lb);) {
			while (i < la && A.charAt(i) != '.') {
				v1 = v1 * 10 + (A.charAt(i) - '0');
				i++;
			}
			while (j < lb && B.charAt(j) != '.') {
				v2 = v2 * 10 + (B.charAt(j) - '0');
				j++;
			}
			if (v1 > v2) {
				return 1;
			}
			if (v1 < v2) {
				return -1;
			}
			v1 = 0;
			v2 = 0;
			i++;
			j++;
		}

		return 0;
	}

	public static int compareVersion_v3(String A, String B) {
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();
		int i = 0, j = 0;
		while (i < a.length || j < b.length) {

			// skip the leading zeroes
			while (i < a.length && a[i] == '0') {
				i++;
			}
			while (j < b.length && b[j] == '0') {
				j++;
			}

			// extract the sub-string
			ArrayList<Character> c1 = new ArrayList<>();
			while (i < a.length && a[i] != '.') {
				c1.add(a[i]);
				i++;
			}

			ArrayList<Character> c2 = new ArrayList<>();
			while (j < b.length && b[j] != '.') {
				c2.add(b[j]);
				j++;
			}

			// compare the sub-strings

			int res = compareSubStrings(c1, c2);
			if (res != 0) {
				return res;
			}
			i++;
			j++;

		}

		return 0;

	}

	private static int compareSubStrings(ArrayList<Character> c1, ArrayList<Character> c2) {

		int l1 = c1.size();
		int l2 = c2.size();

		if (l1 > l2)
			return 1;
		if (l1 < l2)
			return -1;

		int i = 0;
		int j = 0;

		while (i < l1) {
			if (c1.get(i) > c2.get(j)) {
				return 1;
			}
			if (c1.get(i) < c2.get(j)) {
				return -1;
			}
			i++;
			j++;
		}

		return 0;
	}

}
