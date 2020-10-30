package BitManipulation;

public class InterestingArray_MakeArray0BySumXor {

	public static void main(String[] args) {

	}

	/*******
	 * You have an array A with N elements. We have 2 types of operation available
	 * on this array :
	 * 
	 * We can split a element B into 2 elements C and D such that B = C + D. We can
	 * merge 2 elements P and Q as one element R such that R = P^Q i.e XOR of P and
	 * Q.
	 * 
	 * You have to determine whether it is possible to make array A containing only
	 * 1 element i.e. 0 after several splits and/or merge?
	 * 
	 * 
	 ****/

	/********************* APPROACH ****************/

	/*****
	 * If any element in array is even then it can be made 0. Split that element in
	 * two equal parts of A[i]/2 and A[i]/2. XOR of two equal numbers is zero.
	 * Therefore this strategy makes an element 0.
	 * 
	 * If any element is odd. Split it in two part: 1, A[i]-1. Since A[i]-1 is even,
	 * it can be made 0 by above strategy. Therefore an odd element can reduce its
	 * size to 1.
	 * 
	 * Two odd elements can therefore be made 0 by following above strategy and
	 * finally XOR them (i.e. 1) as 1 XOR 1 = 0.
	 * 
	 * Therefore if number of odd elements in array is even, then answer is
	 * possible. Otherwise an element of value 1 will be left and it is not possible
	 * to satisfy the condition.
	 * 
	 ****/

	public String solve(int[] A) {
		int n = A.length;
		int xor = 0;
		for (int i = 0; i < n; i++) {
			xor ^= A[i];
		}
		if (xor % 2 == 0) {
			return "Yes";
		}
		return "No";
	}

}
