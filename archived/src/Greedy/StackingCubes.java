package Greedy;

import java.util.ArrayList;

public class StackingCubes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Stacking Cubes
	 * 
	 * Problem Description
	 * 
	 * You are given A cubes that are of the same size. You are allowed to stack
	 * cubes on top of each other.
	 * 
	 * You should choose P cubes from the A cubes, such that you should stack all
	 * the remaining cubes on top of the P cubes, and each of the P cubes should
	 * have equal number of cubes on top of them.
	 * 
	 * Find and return the number of ways you can stack cubes on top of each other
	 * in the given manner.
	 * 
	 * NOTE: Every cube has to be stacked, you cannot have unstacked cubes. No stack
	 * can consist of a total of just one cube.
	 * 
	 * 
	 * 
	 * refer :
	 * https://github.com/rajnish952/InterviewBit/blob/master/StackingCubes.cpp
	 */
	public int solve(int A) {

		ArrayList<Integer> a = new ArrayList<Integer>();

		int n = (int) Math.sqrt(A);

		for (int i = 1; i <= n; i++) {
			if (A % i == 0) {
				if (A / i == i) {
					a.add(i);
				} else {
					a.add(i);
					a.add(A / i);
				}
			}
		}

		return a.size() - 1;

	}

}
