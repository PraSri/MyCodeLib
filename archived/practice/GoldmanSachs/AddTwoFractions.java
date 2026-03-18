package GoldmanSachs;

import java.util.Arrays;

import QuickHelper.GCD_one_liner;

/*******
 * 
 * Given two fractions which are represented as an array of two elements
 * (numerator and denominator), the task to find the reduced fraction which is
 * the sum of two fractions.
 * 
 ********/
public class AddTwoFractions {

	public static void main(String[] args) {
		System.out.println(findSumFraction(new int[] { 1, 2, 3, 2 }));
		String s = "s".toString();
		Arrays.sort(s.toCharArray());
	}

	public static String findSumFraction(int[] a) {

		int x = a[0], y = a[1], z = a[2], w = a[3];

		int num = x * w + y * z;
		int deno = y * w;

		int gcd = GCD_one_liner.gcd(num, deno);

		num = num / gcd;
		deno = deno / gcd;

		return num + "" + "/" + deno;

	}

}
