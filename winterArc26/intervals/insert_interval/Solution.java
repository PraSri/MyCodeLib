package intervals.insert_interval;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        result.add(new int[]{start, end});
        while (i < n) {
            result.add(intervals[i++]);
        }
        return result.toArray(new int[0][]);
    }
}
