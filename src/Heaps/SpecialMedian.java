package Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SpecialMedian {
	/*
	 * Special Median => Similar to Running Median
	 * 
	 * Problem Description
	 * 
	 * You are given an array A containing N numbers. This array is called special
	 * if it satisfies one of the following properties:
	 * 
	 * There exists an element A[i] in the array such that A[i] is equal to the
	 * median of elements [A[0], A[1], ...., A[i-1]] There exists an element A[i] in
	 * the array such that A[i] is equal to the median of elements [A[i+1], A[i+2],
	 * ...., A[N-1]]
	 * 
	 * Median is the middle element in the sorted list of elements. If the number of
	 * elements are even then median will be (sum of both middle elements)/2.
	 * 
	 * Return 1 if the array is special else return 0.
	 * 
	 * NOTE:
	 * 
	 * For A[0] consider only the median of elements [A[1], A[2], ..., A[N-1]] (as
	 * there are no elements to the left of it) For A[N-1] consider only the median
	 * of elements [A[0], A[1], ...., A[N-2]]
	 * 
	 * 
	 * Problem Constraints
	 * 
	 * 1 <= N <= 1000000. A[i] is in the range of a signed 32-bit integer.
	 * 
	 * 
	 * Input Format
	 * 
	 * First and only argument is an integer array A.
	 * 
	 * 
	 * Output Format
	 * 
	 * Return 1 if the given array is special else return 0.
	 * 
	 * 
	 * Example Input
	 * 
	 * Input 1:
	 * 
	 * A = [4, 6, 8, 4]
	 * 
	 * Input 2:
	 * 
	 * A = [2, 7, 3, 1]
	 * 
	 * 
	 * 
	 * Example Output
	 * 
	 * Output 1:
	 * 
	 * 1
	 * 
	 * Output 2:
	 * 
	 * 0
	 * 
	 * 
	 * 
	 * Example Explanation
	 * 
	 * Explantion 1:
	 * 
	 * Here, 6 is equal to the median of [8, 4].
	 * 
	 * Explanation 2:
	 * 
	 * No element satisfies any condition.
	 */

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 2, 7, 3, 1 }));// 4, 6, 8, 4
		System.out.println(solve(new int[] { 4, 6, 8, 4 }));// 4, 6, 8, 4
	}

	public static int solve(int[] A) {

		int n = A.length;

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		};
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(comparator);
		if (find(A, n, minHeap, maxHeap)) {
			return 1;
		}

		minHeap.clear();
		maxHeap.clear();

		for (int i = 0; i < n / 2; i++) {
			int t = A[i];
			A[i] = A[n - i - 1];
			A[n - i - 1] = t;
		}

		if (find(A, n, minHeap, maxHeap)) {
			return 1;
		}

		return 0;
	}

	public static boolean find(int[] A, int n, PriorityQueue<Integer> min, PriorityQueue<Integer> max) {
		max.add(A[0]);
		double currMedian = A[0];
		for (int i = 1; i < n - 1; i++) {
			// insertion

			if (A[i] < currMedian)

			{

				max.add(A[i]);

				if (max.size() - min.size() > 1)

				{

					int temp = max.poll();

					min.add(temp);

				}

			}

			else

			{

				min.add(A[i]);

				if (min.size() - max.size() > 1)

				{

					int temp = min.poll();

					max.add(temp);

				}

			}

			int maxSize = max.size(), minSize = min.size();

			if (maxSize == minSize)

			{

				int mid1 = max.peek(), mid2 = min.peek();

				currMedian = ((double) mid1 + mid2) / 2;

			}

			else if (maxSize > minSize)

				currMedian = max.peek();

			else

				currMedian = min.peek();

			if (currMedian == A[i + 1])

				return true;

		}

		return false;
	}

}
