package merge_intervals;

import java.util.Arrays;
import java.util.Comparator;

public class CountDaysWithoutMeeting {
    public int countDays(int days, int[][] meetings) {

        Arrays.sort(meetings, Comparator.comparing(a -> a[0]));

        int occupied = 0;
        int start = meetings[0][0];
        int end = meetings[0][1];

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] <= end) {
                end = Math.max(meetings[i][1], end);
            } else {
                occupied += (end - start + 1);
                start = meetings[i][0];
                end = meetings[i][1];
            }
        }

        occupied += (end - start + 1);

        return days - occupied;

    }
}
