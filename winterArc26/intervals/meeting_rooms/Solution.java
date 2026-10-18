package intervals.meeting_rooms;

import common.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public boolean canAttendMeetings(List<Interval> intervals) {
        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt(i -> i.start));
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i).start < sorted.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }
}
