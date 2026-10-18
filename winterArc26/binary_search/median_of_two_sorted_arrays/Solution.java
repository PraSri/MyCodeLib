package binary_search.median_of_two_sorted_arrays;

public class Solution {

    public double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }
        int m = a.length;
        int n = b.length;
        int half = (m + n + 1) / 2;
        int lo = 0;
        int hi = m;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int j = half - i;
            int aLeft = i == 0 ? Integer.MIN_VALUE : a[i - 1];
            int aRight = i == m ? Integer.MAX_VALUE : a[i];
            int bLeft = j == 0 ? Integer.MIN_VALUE : b[j - 1];
            int bRight = j == n ? Integer.MAX_VALUE : b[j];
            if (aLeft <= bRight && bLeft <= aRight) {
                int leftMax = Math.max(aLeft, bLeft);
                if (((m + n) & 1) == 1) {
                    return leftMax;
                }
                return (leftMax + (double) Math.min(aRight, bRight)) / 2.0;
            }
            if (aLeft > bRight) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays must be sorted");
    }
}
