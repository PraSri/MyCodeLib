package TwoPointers;

public class Array3Pointers {

	public static void main(String[] args) {

	}

//	 A = [1, 4, 10]
//	 B = [2, 15, 20]
//	 C = [10, 12]
//	minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).

	public int minimize(final int[] A, final int[] B, final int[] C) {

		int a = A.length;
		int b = B.length;
		int c = C.length;
		int i = 0, j = 0, k = 0;
		int d = Integer.MAX_VALUE;
		int ir = 0, jr = 0, kr = 0;
		int sol = Integer.MAX_VALUE;
		while (i < a && j < b && k < c) {
			int min = Math.min(A[i], Math.min(B[j], C[k]));
			int max = Math.max(A[i], Math.max(B[j], C[k]));
			if (max - min < d) {
				d = max - min;
				ir = i;
				jr = j;
				kr = k;
//				sol = Math.min(sol,
//						Math.max(Math.abs(A[ir] - B[jr]), Math.max(Math.abs(B[jr] - C[kr]), Math.abs(C[kr] - A[ir]))));
			}
			if (d == 0)
				break;
			if (A[i] == min)
				i++;
			else if (B[j] == min)
				j++;
			else
				k++;
		}
		return Math.max(Math.abs(A[ir] - B[jr]), Math.max(Math.abs(B[jr] - C[kr]), Math.abs(C[kr] - A[ir])));
	}

}
