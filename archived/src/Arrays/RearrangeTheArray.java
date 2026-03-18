package Arrays;

public class RearrangeTheArray {

	/*
	 * Given an array of integers A of size N that is a permutation of [0, 1, 2,
	 * ..., (N-1)].
	 * 
	 * Rearrange the array such that A[i] = j is changed to A[j] = i for all i and j
	 * form 0 to N-1.
	 * 
	 * Note: Try solving this with O(1) extra space.
	 * 
	 * 
	 */

	public int[] solve(int[] A) {

		int n = A.length;
		long j = 0;
		int[] a = new int[n];
		// for(int i =0;i<n;i++){
		// j = i%100000;
		// A[A[i]%n] = A[A[i]%n] + (int)j*n;
		// }
		// for(int i=0;i<n;i++)
		// A[i]=A[i]/n;
		for (int i = 0; i < n; i++) {
			a[A[i]] = i;
		}
		return a;
	}

}
