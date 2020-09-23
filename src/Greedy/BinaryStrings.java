package Greedy;

import java.util.Arrays;

public class BinaryStrings {

	public static void main(String[] args) {

		BinaryStrings b = new BinaryStrings();

		int x = b.solve("00010110", 3);
		int y = b.solve("011", 3);
		System.out.println(x);
		System.out.println(y);

	}

	/*
	 * You are given a string A consisting of 1's and 0's. Now the task is to make
	 * the string consisting of only 1's. But you are allowed to perform only the
	 * following operation: Take exactly B consecutive elements of string and change
	 * 1 to 0 and 0 to 1.
	 * 
	 * Each operation takes 1 unit time so you have to determine the minimum time
	 * required to make the string of 1's only. If not possible return -1.
	 */

	public int solve(String A, int B) {

		return find(A.toCharArray(), B);

	}

	private int find(char[] c, int b) {

		int n = c.length;

		int i = 0;
		int ans = 0;
		while (i < n) {
			if (c[i] == '0') {
				int j = i;
				while (b + i <= n && j < b + i) {
					if (c[j] == '0') {
						c[j] = '1';
					} else {
						c[j] = '0';
					}
					j++;
				}
				ans++;
			}
			i++;
		}
//		System.out.println(String.valueOf(c));
//		Arrays.sort(c);
//		int x = Arrays.binarySearch(c, '0');
////		System.out.println("x [= " + x);
//		if (x >= 0) {
//			return -1;
//		}

		for (int k = 0; k < n; k++) {
			if (c[k] == '0') {
				return -1;
			}
		}

		return ans;
	}
}
