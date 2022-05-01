package Arrays;


// https://leetcode.com/problems/merge-intervals/

public class MergeIntervalsLC {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        int m = 0;
        if(n != 0) {
           m = intervals[0].length; 
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        
        for(int[] interval : intervals) {
           if(interval[0]<= newInterval[1]){
               newInterval[1] = Math.max(newInterval[1], interval[1]);
           }else{
               newInterval = interval;
               result.add(newInterval);
           }
            
        }
        
        return result.toArray(new int[result.size()][]);
    }
}
