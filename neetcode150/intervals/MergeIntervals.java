package intervals;

import java.util.*;

public class MergeIntervals {

    // Intervals ko pehle start ke basis par sort karo, phir ek current interval rakh ke overlap check karo.

    public int[][] merge(int[][] intervals) {
        // sort on basis of start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();

        // take first interval as current interval
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for(int[] interval: intervals) {
            // agar next interval ka start current ke end se phele hai
            // toh merge kro
            // current the end = max of both ends hoga
            if(interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                // overlap nhi, no merge, just put the interval in res.
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


}
