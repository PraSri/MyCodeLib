package QuickHelper;

import java.util.Arrays;

import helper.MyMath;
import helperimpl.MyMathImpl;

public class Sorting_CountingSort {

	public static void main(String[] args) {

		String s = "geeksforgeeks";
		System.out.println(String.valueOf(countSortCharArray(s.toCharArray())));

		Arrays.stream(countSortWithNegativeElements(new int[] { -5, -10, 0, -3, 8, 5, -1, 10 }))
				.forEach(i -> System.out.print(i + " , "));

	}

	// https://www.geeksforgeeks.org/counting-sort/

	public static char[] countSortCharArray(char[] c) {

		int[] count = new int[256];

		char[] output = new char[c.length];

		for (char ch : c) {
			count[ch]++;
		}

		for (int i = 1; i < 256; i++) {
			count[i] += count[i - 1];
		}

		for (int i = c.length - 1; i >= 0; i--) {
			output[count[c[i]] - 1] = c[i];
			count[c[i]]--;
		}

		for (int i = 0; i < c.length; i++)
			c[i] = output[i];

		return c;

	}

	public static int[] countSortWithNegativeElements(int[] a) {

		MyMath math = new MyMathImpl();
		int max = math.max(a);
		int min = math.min(a);

		int range = max - min + 1;

		int n = a.length;
		int[] count = new int[range];

		int[] output = new int[n];

		for (int i : a) {
			count[i - min]++;
		}

		for (int i = 1; i < range; i++) {
			count[i] += count[i - 1];
		}

		for (int i = n - 1; i >= 0; i--) {
			output[count[a[i] - min] - 1] = a[i];
			count[a[i] - min]--;
		}

		for (int i = 0; i < n; i++) {
			a[i] = output[i];
		}

		return a;

	}

}
