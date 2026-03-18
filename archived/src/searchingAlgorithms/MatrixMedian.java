package searchingAlgorithms;

import java.util.Arrays;

import javax.management.relation.Role;

public class MatrixMedian {

	public static void main(String[] args) {
//		int[][] a = new int[][] { { 1, 3, 5 }, { 2, 6, 9 }, { 3, 6, 9 } };
		int[][] a = new int[][] {{5, 17, 100}};
//		System.out.println(getMax(a));
//		System.out.println(getMin(a));
		System.out.println(findMedian(a));
	}

	public static int getMax(int[][] a) {
		int maxValue = 0;
		int rowLen = a.length;
		int colLen = a[0].length;
		for (int i = 0; i < rowLen; i++) {
			if (a[i][colLen - 1] > maxValue) {
				maxValue = a[i][colLen - 1];
			}
		}
		return maxValue;
	}

	public static int getMin(int[][] a) {
		int minValue = Integer.MAX_VALUE;
		int rowLen = a.length;
		int colLen = a[0].length;
		for (int i = 0; i < rowLen; i++) {
			if (a[i][0] < minValue) {
				minValue = a[i][0];
			}
		}
		return minValue;
	}

	public static int findMedian(int[][] A) {
		int rowLen = A.length;
		int colLen = A[0].length;
		int s = getMin(A);
		int e = getMax(A);
		int mid;
		int reqc = (rowLen * colLen + 1) / 2;
		int c = 0;
		while (s < e) {
			mid = (s + e) / 2;
			c = 0;
			// find no. of elements less than mid
			for (int i = 0; i < rowLen; i++) {
				int ub = Arrays.binarySearch(A[i], mid);
//				System.out.println("ub="+ub);
				// if element not found in array binary searh returns -ip-1.....x = -ip-1....x+1
				// = -ip
				if (ub < 0) {
					ub = Math.abs(ub) - 1;
				} else {
					// if element is found then the binary search returns the any index if duplicate
					// of searched term
					while (ub < A[i].length && A[i][ub] == mid) {
						ub++;
					}
				}
				c += ub;
			}
			if (c <reqc) {
				s = mid+1;
			} else {
				e = mid ;
			}
		}

		return s;
	}

}
