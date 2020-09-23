package Greedy;

import java.util.Arrays;

public class AnotherCoinProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solve(47));
	}

	/*
	 * Another Coin Problem
	 * 
	 * Problem Description
	 * 
	 * The monetary system in DarkLand is really simple and systematic. The locals
	 * only use coins. The coins come in different values. The values used are:
	 * 
	 * 1, 5, 25, 125, 625, 3125, 15625, ...
	 * 
	 * Formally, for each K >= 0 there are coins worth 5K.
	 * 
	 * Given an integer A denoting the cost of an item, find and return the smallest
	 * number of coins necessary to pay exactly the cost of the item (assuming you
	 * have a sufficient supply of coins of each of the types you will need).
	 * 
	 */
	public static int solve(int A) {

		double[] coins = new double[17];

		for (int i = 16; i >= 0; i--) {
			coins[16 - i] = Math.pow(5, i);
		}

		double a = A;
		int ans = 0;
		for (int i = 0; i < 17; i++) {
			if (coins[i] <= a) {
				int x = (int) (a / coins[i]);
				a = a % coins[i];
				ans += x;
				if (a == 0)
					break;
			}
		}

		return (int) ans;

	}

}
