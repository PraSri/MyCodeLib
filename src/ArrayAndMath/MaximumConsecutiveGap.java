package ArrayAndMath;

public class MaximumConsecutiveGap {

	public static void main(String[] args) {
	}

	/*
	 * Given an unsorted integer array A of size N. Find the maximum difference
	 * between the successive elements in its sorted form.
	 * 
	 * Try to solve it in linear time/space.
	 * 
	 * You may assume that all the elements in the array are non-negative integers
	 * and fit in the 32-bit signed integer range. You may also assume that the
	 * difference will not overflow.
	 * 
	 * Return 0 if the array contains less than 2 elements.
	 * 
	 */

	public int maximumGap(final int[] A) {
		int n = A.length;
		if (n < 2)
			return 0;
		int minV = Integer.MAX_VALUE;
		int maxV = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (A[i] > maxV) {
				maxV = A[i];
			}
			if (A[i] < minV) {
				minV = A[i];
			}
		}
		if (maxV - minV <= 1) {
			return maxV - minV;
		}
		int minPossibleGap = (int) Math.floor((maxV - minV) / (n - 1));
		// System.out.println("minPossibleGap:" + minPossibleGap);
		int numberOfBuckets = (int) Math.floor((maxV - minV) / minPossibleGap);
		// System.out.println("numberOfBuckets:" + numberOfBuckets);
		int[] buckets_min = new int[numberOfBuckets + 1];
		int[] buckets_max = new int[numberOfBuckets + 1];
		for (int i = 0; i <= numberOfBuckets; i++) {
			buckets_min[i] = Integer.MAX_VALUE;
			buckets_max[i] = Integer.MIN_VALUE;
		}
		int bucketNumber = 0;
		for (int i = 0; i < n; i++) {
			bucketNumber = (int) Math.floor((A[i] - minV) / minPossibleGap);
			// System.out.println("bucketNumber:" + bucketNumber);
			buckets_min[bucketNumber] = Math.min(A[i], buckets_min[bucketNumber]);
			buckets_max[bucketNumber] = Math.max(A[i], buckets_max[bucketNumber]);
		}
		// for (int i = 0; i <=bucketNumber; i++) {
		// System.out.println("buckets_max:"+buckets_max[i]);
		// System.out.println("buckets_min:"+buckets_min[i]);
		// }
		int prev_bucket = 0;
		int max_gap = Integer.MIN_VALUE;
		for (int curr = 1; curr <= numberOfBuckets; curr++) {
			if (buckets_min[curr] == Integer.MAX_VALUE || buckets_max[prev_bucket] == Integer.MIN_VALUE) {
				continue;
			}
			int gap = buckets_min[curr] - buckets_max[prev_bucket];
			max_gap = Math.max(max_gap, gap);
			prev_bucket = curr;
		}
		return max_gap;
	}

}
