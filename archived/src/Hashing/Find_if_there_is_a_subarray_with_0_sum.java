package Hashing;

import java.util.HashMap;
import java.util.Map;

public class Find_if_there_is_a_subarray_with_0_sum {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 5, 17, -22, 11 }));
	}

	public static int solve(int[] A) {
		Map<Long, Integer> m = new HashMap<>();
		int n = A.length;
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += A[i];
			if (A[i] == 0 || sum == 0 || m.get(sum) != null) {
				return 1;
			}
			m.put(sum, i);
		}
		return 0;

	}

	/*
	 * 4, 2, -3, 1, 6
	 * 
	 * 4 6 3 4 10 = sum till ith index
	 * 
	 * check if sum[j]-sum[i]=0 then
	 * 
	 * A[i+1] to A[j] is the sub array with sum = 0
	 * 
	 * 
	 */

}
