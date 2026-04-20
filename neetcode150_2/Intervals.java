import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Intervals {

    // Insert Intervals
    //Input: intervals = [[1,3],[4,6]], newInterval = [2,5]
    //
    //Output: [[1,6]]

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i = 0;

        List<int[]> res = new ArrayList<>();

        while (i < n && newInterval[0] > intervals[i][1]) {
            res.add(intervals[i]);
            i++;
        }

        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        res.add(newInterval);

        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }


    // Merge Intervals
    //Input: intervals = [[1,3],[1,5],[6,7]]
    //
    //Output: [[1,5],[6,7]]

    public int[][] merge(int[][] intervals) {

        // sort by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];

        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                res.add(newInterval);
            }
        }

        return res.toArray(new int[res.size()][]);

    }

    // Non-overlapping Intervals
    public int eraseOverlapIntervals(int[][] intervals) {

        // sort by end time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int res = 0;
        int prevEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (start < prevEnd) {
                res++;
            } else {
                prevEnd = end;
            }
        }
        return res;
    }

}
