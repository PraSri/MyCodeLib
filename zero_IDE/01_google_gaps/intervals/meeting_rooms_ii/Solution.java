import java.util.*;

public class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0;
        int endPtr = 0;

        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[endPtr]) {
                // A meeting starts before the earliest ending meeting finishes -> allocate room
                rooms++;
            } else {
                // Room vacated; reuse room by advancing end pointer
                endPtr++;
            }
        }

        return rooms;
    }
}
