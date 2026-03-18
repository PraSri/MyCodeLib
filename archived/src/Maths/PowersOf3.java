package Maths;

import java.util.ArrayList;
import java.util.Collections;

public class PowersOf3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(solve_v2(586));
//		System.out.println(getPowerOf3Floor(842));
		System.out.println(getPowerOf3Floor_v2(243));
	}

	/*
	 * 
	 * Given a positive integer A. Return an array of minimum length whose elements
	 * represent the powers of 3 and the sum of all the elements is equal to A.
	 * 
	 * NOTE: If A cannot be represented as the sum of powers of 3, then return an
	 * array containing only -1.
	 * 
	 * ex. A = 13
	 * 
	 * no <= 13%3
	 * 
	 * 12
	 * 
	 * no<=12 which is the power of 3
	 * 
	 * 9
	 * 
	 * 
	 * no near to 13 n power of 3
	 * 
	 * 9
	 * 
	 * 13 - 9 = 4
	 * 
	 * 4 - 3 = 1
	 * 
	 * 1 - 1 = 0
	 * 
	 * 
	 * 13/3 = 4.3.
	 * 
	 * 3^x <= 13 xln3 <= ln 13 x <= ln 13/ln 3 x = 2 .
	 * 
	 * 
	 * 
	 */

	public ArrayList<Integer> solve(int A) {
		ArrayList<Integer> a = new ArrayList<Integer>();

		int n = A;
// 	if(n%3==0) {
// 		a.add(n);
// 		return a;
// 	}
		int i = 0;
		int sum = n;
		while (sum > 0) {
			int x = power(3, i);
			sum = sum - x;
//		 System.out.print("sum = " + sum + " x = " + x);
			a.add(x);
			i++;
			if (sum < 0) {
				a.clear();
				a.add(-1);
				return a;
			}
		}
		return a;
	}

	private static int power(int a, int b) {
		if (b == 0)
			return 1;
		int p = a;
		for (int i = 1; i < b; i++) {
			p = p * a;
		}

		return p;

	}

	public static int getPowerOf3Floor(int n) {
		int res = 1;
		double x = Math.log(n);
		System.out.println(" x = " + x);
		double y = Math.log(3);
		System.out.println(" y = " + y);
		double z = x / y;
		System.out.println(" z  =" + z + " Math.round(z); " + Math.round(z));
//		res = (int) z;
		res = (int) Math.round(z);
		return res;
	}

	public static int getPowerOf3Floor_v2(int n) {
		long a = 3;
		int i = 0;
		while (a <= n) {
			i++;
			a = a * 3;
		}
		return i;
	}

	public static ArrayList<Integer> solve_v2(int A) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		int sum = 0;
		int n = A;
		while (n > 0) {
			double xd = Math.pow(3, getPowerOf3Floor(n));
			int x = (int) Math.pow(3, getPowerOf3Floor(n));
			System.out.println("X = " + x + " xd = " + xd);
			sum += x;
			a.add(x);
			n = n - x;
			System.out.println(" n = " + n);
		}
		System.out.println(sum);
		if (sum != A) {
			a.clear();
			a.add(-1);
			return a;
		}

		Collections.reverse(a);

		return a;
	}

}
