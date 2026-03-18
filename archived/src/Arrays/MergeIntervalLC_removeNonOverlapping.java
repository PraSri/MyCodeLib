package Arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervalLC_removeNonOverlapping {
  public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Interval[] arr = new Interval[intervals.length];
        int i = 0;
        for (int[] interval : intervals) {
            arr[i++] = new Interval(interval[0], interval[1]);
        }
        return solve(arr);
    }

    public int solve(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.end));
        int c = 1;
        int end = intervals[0].end;
        for(int i = 1; i<intervals.length; i++) {
            if(intervals[i].start>=end) {
                end = intervals[i].end;
                c++;
            }
        }
        return intervals.length - c;
    }
}
