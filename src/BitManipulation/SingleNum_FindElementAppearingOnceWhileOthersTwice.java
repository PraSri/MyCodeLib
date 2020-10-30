package BitManipulation;

public class SingleNum_FindElementAppearingOnceWhileOthersTwice {

	public static void main(String[] args) {

	}

	/**
	 * Given an array of integers A, every element appears twice except for one.
	 * Find that single one.
	 * 
	 */

	/***
	 * A ^ A = 0 and A ^ B ^ A = B.
	 * 
	 * So, all even occurences will cancel out using XOR.
	 * 
	 * 
	 **/

	public int singleNumber(final int[] A) {
		int ans = 0;
		for (int i = 0; i < A.length; i++) {
			ans ^= A[i];
		}
		return ans;
	}
}
