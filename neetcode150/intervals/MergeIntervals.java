package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/***### Similar Questions  Merge Intervals (LeetCode 56)

 1. **Insert Interval**
 [<a href="https://leetcode.com/problems/insert-interval/">...</a>](https://leetcode.com/problems/insert-interval/)

 2. **Meeting Rooms**
 [https://leetcode.com/problems/meeting-rooms/](https://leetcode.com/problems/meeting-rooms/)

 3. **Meeting Rooms II**
 [https://leetcode.com/problems/meeting-rooms-ii/](https://leetcode.com/problems/meeting-rooms-ii/)

 4. **Teemo Attacking**
 [https://leetcode.com/problems/teemo-attacking/](https://leetcode.com/problems/teemo-attacking/)

 5. **Add Bold Tag in String**
 [https://leetcode.com/problems/add-bold-tag-in-string/](https://leetcode.com/problems/add-bold-tag-in-string/)

 6. **Range Module**
 [https://leetcode.com/problems/range-module/](https://leetcode.com/problems/range-module/)

 7. **Employee Free Time**
 [https://leetcode.com/problems/employee-free-time/](https://leetcode.com/problems/employee-free-time/)

 8. **Partition Labels**
 [https://leetcode.com/problems/partition-labels/](https://leetcode.com/problems/partition-labels/)

 9. **Interval List Intersections**
 [https://leetcode.com/problems/interval-list-intersections/](https://leetcode.com/problems/interval-list-intersections/)

 10. **Amount of New Area Painted Each Day**
 [https://leetcode.com/problems/amount-of-new-area-painted-each-day/](https://leetcode.com/problems/amount-of-new-area-painted-each-day/)

 11. **Longest Substring of One Repeating Character**
 [https://leetcode.com/problems/longest-substring-of-one-repeating-character/](https://leetcode.com/problems/longest-substring-of-one-repeating-character/)

 12. **Count Integers in Intervals**
 [https://leetcode.com/problems/count-integers-in-intervals/](https://leetcode.com/problems/count-integers-in-intervals/)

 13. **Divide Intervals Into Minimum Number of Groups**
 [https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/)

 14. **Determine if Two Events Have Conflict**
 [https://leetcode.com/problems/determine-if-two-events-have-conflict/](https://leetcode.com/problems/determine-if-two-events-have-conflict/)

 15. **Count Ways to Group Overlapping Ranges**
 [https://leetcode.com/problems/count-ways-to-group-overlapping-ranges/](https://leetcode.com/problems/count-ways-to-group-overlapping-ranges/)

 16. **Points That Intersect With Cars**
 [https://leetcode.com/problems/points-that-intersect-with-cars/](https://leetcode.com/problems/points-that-intersect-with-cars/)

 17. **Count Days Without Meetings**
 [https://leetcode.com/problems/count-days-without-meetings/](https://leetcode.com/problems/count-days-without-meetings/)

 18. **Minimize Connected Groups by Inserting Interval**
 [https://leetcode.com/problems/minimize-connected-groups-by-inserting-interval/](https://leetcode.com/problems/minimize-connected-groups-by-inserting-interval/)
 **/

public class MergeIntervals {

    // Intervals ko pehle start ke basis par sort karo, phir ek current interval rakh ke overlap check karo.

    public int[][] merge(int[][] intervals) {
        // sort on basis of start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();

        // take first interval as current interval
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval : intervals) {
            // agar next interval ka start current ke end se phele hai
            // toh merge kro
            // current the end = max of both ends hoga
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                // overlap nhi, no merge, just put the interval in res.
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    /**
     * 1. **Insert Interval**
     * https://leetcode.com/problems/insert-interval/">...</a>](https://leetcode.com/problems/insert-interval/)
     */
    public static class InsertInterval {
    }

    /**
     * 2. **Meeting Rooms**
     * https://leetcode.com/problems/meeting-rooms/](https://leetcode.com/problems/meeting-rooms/)
     */
    public static class MeetingRooms {
    }

    /**
     * 3. **Meeting Rooms II**
     * https://leetcode.com/problems/meeting-rooms-ii/](https://leetcode.com/problems/meeting-rooms-ii/)
     */
    public static class MeetingRoomsIi {
    }

    /**
     * 4. **Teemo Attacking**
     * https://leetcode.com/problems/teemo-attacking/](https://leetcode.com/problems/teemo-attacking/)
     */
    public static class TeemoAttacking {
    }

    /**
     * 5. **Add Bold Tag in String**
     * https://leetcode.com/problems/add-bold-tag-in-string/](https://leetcode.com/problems/add-bold-tag-in-string/)
     */
    public static class AddBoldTagInString {
    }

    /**
     * 6. **Range Module**
     * https://leetcode.com/problems/range-module/](https://leetcode.com/problems/range-module/)
     */
    public static class RangeModule {
    }

    /**
     * 7. **Employee Free Time**
     * https://leetcode.com/problems/employee-free-time/](https://leetcode.com/problems/employee-free-time/)
     */
    public static class EmployeeFreeTime {
    }

    /**
     * 8. **Partition Labels**
     * https://leetcode.com/problems/partition-labels/](https://leetcode.com/problems/partition-labels/)
     */
    public static class PartitionLabels {
    }

    /**
     * 9. **Interval List Intersections**
     * https://leetcode.com/problems/interval-list-intersections/](https://leetcode.com/problems/interval-list-intersections/)
     */
    public static class IntervalListIntersections {
    }

    /**
     * 10. **Amount of New Area Painted Each Day**
     * https://leetcode.com/problems/amount-of-new-area-painted-each-day/](https://leetcode.com/problems/amount-of-new-area-painted-each-day/)
     */
    public static class AmountOfNewAreaPaintedEachDay {
    }

    /**
     * 11. **Longest Substring of One Repeating Character**
     * https://leetcode.com/problems/longest-substring-of-one-repeating-character/](https://leetcode.com/problems/longest-substring-of-one-repeating-character/)
     */
    public static class LongestSubstringOfOneRepeatingCharacter {
    }

    /**
     * 12. **Count Integers in Intervals**
     * https://leetcode.com/problems/count-integers-in-intervals/](https://leetcode.com/problems/count-integers-in-intervals/)
     */
    public static class CountIntegersInIntervals {
    }

    /**
     * 13. **Divide Intervals Into Minimum Number of Groups**
     * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/)
     */
    public static class DivideIntervalsIntoMinimumNumberOfGroups {
    }

    /**
     * 14. **Determine if Two Events Have Conflict**
     * https://leetcode.com/problems/determine-if-two-events-have-conflict/](https://leetcode.com/problems/determine-if-two-events-have-conflict/)
     */
    public static class DetermineIfTwoEventsHaveConflict {
    }

    /**
     * 15. **Count Ways to Group Overlapping Ranges**
     * https://leetcode.com/problems/count-ways-to-group-overlapping-ranges/](https://leetcode.com/problems/count-ways-to-group-overlapping-ranges/)
     */
    public static class CountWaysToGroupOverlappingRanges {
    }

    /**
     * 16. **Points That Intersect With Cars**
     * https://leetcode.com/problems/points-that-intersect-with-cars/](https://leetcode.com/problems/points-that-intersect-with-cars/)
     */
    public static class PointsThatIntersectWithCars {
    }

    /**
     * 17. **Count Days Without Meetings**
     * https://leetcode.com/problems/count-days-without-meetings/](https://leetcode.com/problems/count-days-without-meetings/)
     */
    public static class CountDaysWithoutMeetings {
    }

    /**
     * 18. **Minimize Connected Groups by Inserting Interval**
     * https://leetcode.com/problems/minimize-connected-groups-by-inserting-interval/](https://leetcode.com/problems/minimize-connected-groups-by-inserting-interval/)
     */
    public static class MinimizeConnectedGroupsByInsertingInterval {
    }

    /**
     * leetcode.com/problems/insert-interval/">...</a>](
     * https://leetcode.com/problems/insert-interval/)
     */
    public static class LeetcodeComProblemsInsertIntervalA {
    }

    /**
     * leetcode.com/problems/meeting-rooms/](
     * https://leetcode.com/problems/meeting-rooms/)
     */
    public static class LeetcodeComProblemsMeetingRooms {
    }

    /**
     * leetcode.com/problems/meeting-rooms-ii/](
     * https://leetcode.com/problems/meeting-rooms-ii/)
     */
    public static class LeetcodeComProblemsMeetingRoomsIi {
    }

    /**
     * leetcode.com/problems/teemo-attacking/](
     * https://leetcode.com/problems/teemo-attacking/)
     */
    public static class LeetcodeComProblemsTeemoAttacking {
    }

    /**
     * leetcode.com/problems/add-bold-tag-in-string/](
     * https://leetcode.com/problems/add-bold-tag-in-string/)
     */
    public static class LeetcodeComProblemsAddBoldTagInString {
    }

    /**
     * leetcode.com/problems/range-module/](
     * https://leetcode.com/problems/range-module/)
     */
    public static class LeetcodeComProblemsRangeModule {
    }

    /**
     * leetcode.com/problems/employee-free-time/](
     * https://leetcode.com/problems/employee-free-time/)
     */
    public static class LeetcodeComProblemsEmployeeFreeTime {
    }

    /**
     * leetcode.com/problems/partition-labels/](
     * https://leetcode.com/problems/partition-labels/)
     */
    public static class LeetcodeComProblemsPartitionLabels {
    }

    /**
     * leetcode.com/problems/interval-list-intersections/](
     * https://leetcode.com/problems/interval-list-intersections/)
     */
    public static class LeetcodeComProblemsIntervalListIntersections {
    }

    /**
     * leetcode.com/problems/amount-of-new-area-painted-each-day/](
     * https://leetcode.com/problems/amount-of-new-area-painted-each-day/)
     */
    public static class LeetcodeComProblemsAmountOfNewAreaPaintedEachDay {
    }

    /**
     * leetcode.com/problems/longest-substring-of-one-repeating-character/](
     * https://leetcode.com/problems/longest-substring-of-one-repeating-character/)
     */
    public static class LeetcodeComProblemsLongestSubstringOfOneRepeatingCharacter {
    }

    /**
     * leetcode.com/problems/count-integers-in-intervals/](
     * https://leetcode.com/problems/count-integers-in-intervals/)
     */
    public static class LeetcodeComProblemsCountIntegersInIntervals {
    }

    /**
     * leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/](
     * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/)
     */
    public static class LeetcodeComProblemsDivideIntervalsIntoMinimumNumberOfGroups {
    }

    /**
     * leetcode.com/problems/determine-if-two-events-have-conflict/](
     * https://leetcode.com/problems/determine-if-two-events-have-conflict/)
     */
    public static class LeetcodeComProblemsDetermineIfTwoEventsHaveConflict {
    }

    /**
     * leetcode.com/problems/count-ways-to-group-overlapping-ranges/](
     * https://leetcode.com/problems/count-ways-to-group-overlapping-ranges/)
     */
    public static class LeetcodeComProblemsCountWaysToGroupOverlappingRanges {
    }

    /**
     * leetcode.com/problems/points-that-intersect-with-cars/](
     * https://leetcode.com/problems/points-that-intersect-with-cars/)
     */
    public static class LeetcodeComProblemsPointsThatIntersectWithCars {
    }

    /**
     * leetcode.com/problems/count-days-without-meetings/](
     * https://leetcode.com/problems/count-days-without-meetings/)
     */
    public static class LeetcodeComProblemsCountDaysWithoutMeetings {
    }

    /**
     * leetcode.com/problems/minimize-connected-groups-by-inserting-interval/](
     * https://leetcode.com/problems/minimize-connected-groups-by-inserting-interval/)
     */
    public static class LeetcodeComProblemsMinimizeConnectedGroupsByInsertingInterval {
    }
}
