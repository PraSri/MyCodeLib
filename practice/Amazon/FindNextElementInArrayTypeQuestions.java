package Amazon;

import java.util.List;

public interface FindNextElementInArrayTypeQuestions {

//	https://www.geeksforgeeks.org/find-the-nearest-smaller-numbers-on-left-side-in-an-array/ 
//	https://www.geeksforgeeks.org/next-greater-element/ => https://www.geeksforgeeks.org/next-greater-element-in-same-order-as-input/
//	https://www.geeksforgeeks.org/next-smaller-element/
//	https://www.geeksforgeeks.org/previous-greater-element/

	// previous smallest element
	// Find the nearest smaller numbers on left side in an array
	public List<Integer> PSE(int[] a);

	// Previous greater element
	public List<Integer> PGE(int[] a);

//	Next Smaller Element
	public List<Integer> NSE(int[] a);

//	Next Greater Element
	public List<Integer> NGE(int[] a);

	/*******
	 * I believe all should be studied at once, as it provides perspective: NGE -
	 * Stack ordered at decreasing order + digest at the end (elements for which no
	 * NGE was found). NSE - Stack ordered at increasing order + digest at the end
	 * (elements for which no NSE was found). PGE - Stack ordered at decreasing
	 * order. Here the "digest" is performed when we pop irrelevant elements during
	 * the main loop, not in retrospect. PSE - Stack ordered at increasing order.
	 * Same "digest" remark as in PGE.
	 * 
	 * NGE can also be calculated via the PGE algorithm scheme, by traversing the
	 * array in reverse order. Also correct towards calculating NSE via the scheme
	 * of PSE.
	 **********/

}
