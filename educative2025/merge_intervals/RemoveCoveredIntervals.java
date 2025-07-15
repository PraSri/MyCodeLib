package merge_intervals;

import java.util.Arrays;

public class RemoveCoveredIntervals {
    /**The first step is to simplify the process by sorting the intervals.
     * Sorting by the start point in ascending order is straightforward and
     * simplifies the iteration process. However, an important edge case
     * arises when two intervals share the same start point. In such scenarios,
     * sorting solely by the start point would fail to correctly identify covered intervals.
     * To handle this, we sort intervals with the same start point by their endpoint in descending order,
     * ensuring that longer intervals come first. This sorting strategy guarantees that if one interval covers another,
     * it will be positioned earlier in the sorted list. Once the intervals are sorted,
     * we iterate through them while keeping track of the maximum endpoint seen so far.
     * If the current interval’s end point exceeds this maximum, it is not covered,
     * so we increment the count and update the maximum end. The interval is covered and
     * skipped if the endpoint is less than or equal to the maximum. After completing the iteration,
     * the final count reflects the remaining non-covered intervals.*/

    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> {
            if(a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int count = 0;
        int prevEnd = 0;
        for(int[] interval: intervals) {
            int end = interval[1];
            if(end > prevEnd) {
                count++;
                prevEnd = end;
            }
        }

        return count;

    }
}
