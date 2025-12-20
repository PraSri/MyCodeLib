package intervals;

import java.util.*;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length , i = 0;

        List<int[]> res = new ArrayList<>();

        // jo intervals completely newInterval se pehle end ho chuke hain
        // unko seedha res mein daal do aur i increment kro
        while(i<n && newInterval[0] > intervals[i][1]) {
            res.add(intervals[i]);
            i++;
        }

        // jo intervals overlap karte hain, unke sath new interval ko merge kro
        // start humesa min hoga aur end max
        // i increment krte rho
        while(i<n && newInterval[1]>=intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        // baaki bache hue intervals ko simply res mein daal do
        while(i<n) {
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }

}
