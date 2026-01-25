package intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * *Minimum Number of Arrows to Burst Balloons
 * <a href="https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/">...</a>
 * <p>
 * Determine if Two Events Have Conflict
 * <a href="https://leetcode.com/problems/determine-if-two-events-have-conflict/">...</a>
 * <p>
 *
 */

public class NonOverlappingIntervals {

    //Intervals diye gaye hain, hume minimum number of intervals
    // remove karne hain taaki baaki intervals overlap na karein.

    public int eraseOverlapIntervals(int[][] intervals) {
        //Intervals ko end time ke basis par sort kar do (jo pehle khatam ho, usko priority).
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        //Pehle interval ka end = prevEnd maan lo
        int prevEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            //Agar current start < prevEnd ? overlap ho raha hai
            // is interval ko remove karna padega (res++)
            if (start < prevEnd) {
                res++;
            } else {
                //Else ? overlap nahi hai
                // prevEnd = current end update kar do
                prevEnd = end;
            }
        }
        return res;
    }

    /**
     * Minimum Number of Arrows to Burst Balloons
     * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/">...</a>
     */
    public static class MinimumNumberOfArrowsToBurstBalloons {
    }

    /**
     * Determine if Two Events Have Conflict
     * https://leetcode.com/problems/determine-if-two-events-have-conflict/">...</a>
     */
    public static class DetermineIfTwoEventsHaveConflict {
    }
}
