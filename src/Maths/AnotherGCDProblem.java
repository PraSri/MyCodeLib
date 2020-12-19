package Maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import QuickHelper.SmallestPrimeFactorOfInteger;

public class AnotherGCDProblem {

	/** WARNING this solution is failing for large input getting OOM error *****/
	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 8, 5, 6, 8, 5, 12));// //3, 8, 5, 6, 8, 5, 12

		int ans = getMaxSubarrayLengthWithGCDGreaterThanOne(list);

		System.out.println(ans);

	}

	/**
	 *
	 *
	 * Given an integer array A of size N. Find the maximum length of a subarray Al,
	 * Al+1 ... Ar such that gcd(A[l], A[l+1], ... A[r]) > 1.
	 *
	 ** NOTE: If no such subarray exists, return -1.
	 **
	 **
	 **/
	/****** Solution Approach **********/

	/*****
	 *
	 *
	 * First think about how many distinct prime factors can a number < 106 has.
	 * 
	 * There can be atmost 10 distinct prime factors of a number. So, if we reduce
	 * every element into it’s distinct prime factors. There will be atmost 10*N
	 * elements.
	 * 
	 * Consider a 2-D list V where V[i] contain the indexes of elements having i as
	 * one of the prime factor. These indexes are in sorted order.
	 * 
	 * So for every list V[i], gcd of elements at consecutive indexes in the list
	 * must be atleast i as all these elements have factor i in common.
	 * 
	 * So, our problem reduces to find the maximum length of consecutive elements in
	 * the all the lists. This can be easily be done.
	 *
	 *
	 ********/
	public static int maxn = 1000001;
	public static int[] smallestPrimeFactor = SmallestPrimeFactorOfInteger.smallestPrimeFactor;
	public static Map<Integer, Set<Integer>> primeIndexMapList;

	public static int getMaxSubarrayLengthWithGCDGreaterThanOne(ArrayList<Integer> list) {
		primeIndexMapList = new HashMap<Integer, Set<Integer>>();
		int n = list.size();
		SmallestPrimeFactorOfInteger.sieveSmallestPrimeFactor();
		int maxv = list.get(0);
		for (int i = 0; i < n; i++) {
			int element = list.get(i);
			maxv = Math.max(maxv, element);
			while (element > 1) {
				int p = smallestPrimeFactor[element];
				if (!primeIndexMapList.containsKey(p)) {
					primeIndexMapList.put(p, new LinkedHashSet<Integer>(maxn));
				}
				primeIndexMapList.get(p).add(i);
				while (element % p == 0) {
					element = element / p;
				}
			}
		}
		int ans = -1;
//		System.out.println(primeIndexMapList);
		for (Map.Entry<Integer, Set<Integer>> me : primeIndexMapList.entrySet()) {
			int p = me.getKey();
			if (p < 2 || p > maxv)
				continue;
			List<Integer> indices = new ArrayList<Integer>(me.getValue());

			int curr = 1;
			for (int i = 0; i < indices.size(); i++) {
				if (i != 0 && indices.get(i) - indices.get(i - 1) == 1) {
					curr++;
				} else {
					curr = 1;
				}
				ans = Math.max(ans, curr);
			}

		}
		return ans;
	}

}
