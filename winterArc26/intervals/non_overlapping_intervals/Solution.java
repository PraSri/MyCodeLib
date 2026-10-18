package intervals.non_overlapping_intervals;

import java.util.Arrays;

public class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int removed = 0;
        int lastEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] >= lastEnd) {
                lastEnd = interval[1];
            } else {
                removed++;
            }
        }
        return removed;
    }
}
