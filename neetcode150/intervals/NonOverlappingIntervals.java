package intervals;

import java.util.*;

public class NonOverlappingIntervals {

    //Intervals diye gaye hain, hume minimum number of intervals
    // remove karne hain taaki baaki intervals overlap na karein.

    public int eraseOverlapIntervals(int[][] intervals) {
        //Intervals ko end time ke basis par sort kar do (jo pehle khatam ho, usko priority).
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        //Pehle interval ka end = prevEnd maan lo
        int prevEnd = intervals[0][1];
        for(int i = 1; i<intervals.length;i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            //Agar current start < prevEnd ? overlap ho raha hai
            // is interval ko remove karna padega (res++)
            if(start < prevEnd) {
                res++;
            } else {
                //Else ? overlap nahi hai
                // prevEnd = current end update kar do
                prevEnd = end;
            }
        }
        return res;
    }
}
