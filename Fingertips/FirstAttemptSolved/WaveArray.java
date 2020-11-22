package FirstAttemptSolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WaveArray {

	public static void main(String[] args) {

	}

	// https://www.interviewbit.com/problems/wave-array/

	public int[] wave(int[] A) {
		int n = A.length;
		Arrays.sort(A);
		for (int i = 0; i < n; i++) {
			if (i > 0 && i % 2 != 0 && A[i] > A[i - 1]) {
				swap(A, i, i - 1);
			}
			if (i < n - 1 && i % 2 == 0 && A[i] > A[i + 1]) {
				swap(A, i + 1, i);
			}
		}
		return A;
	}

	private void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	public ArrayList<Integer> wave(ArrayList<Integer> a) {
		Collections.sort(a);
		for (int i = 0; i < a.size() - 1; i = i + 2) {
			int temp = a.get(i);
			a.set(i, a.get(i + 1));
			a.set(i + 1, temp);
		}
		return a;
	}

}
