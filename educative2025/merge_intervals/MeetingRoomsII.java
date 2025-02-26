package p0s07cu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoomsII {

  public static int findSets(int[][] intervals) {
    return minMeetingRooms(convertToList(intervals));
  }

  public static List<Interval> convertToList(int[][] intervals) {
    List<Interval> list = new ArrayList<>();
    for (int[] interval : intervals) {
      list.add(new Interval(interval[0], interval[1]));
    }
    return list;
  }

  /**
   * We are given an input array of meeting time intervals, intervals,
   * where each interval has a start time and an end time.
   * Your task is to find the minimum number of meeting rooms required to hold these meetings.
   * Example:
   * Meeting time intervals = [ [1, 3], [2, 6], [8, 10], [9, 15], [12, 14] ]
   * Meeting room 1 = [ [1, 3], [8, 10], [12, 14] ]
   * Meeting room 2 = [ [2, 6], [9, 15] ]
   */

  public static int minMeetingRooms(List<Interval> intervals) {
    intervals.sort(Comparator.comparingInt(a -> a.start));
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (Interval interval : intervals) {
      if (!minHeap.isEmpty() && interval.start >= minHeap.peek()) {
        minHeap.poll();
      }
      minHeap.offer(interval.end);
    }
    return minHeap.size();

  }

  /**
   * SOLUTION :
   * <p>Sort the given input intervals with respect to their start times.
   * <p>
   * Initialize a min-heap and push the end time of the first interval onto it.
   * <p>
   * Loop over the remaining intervals.
   * <p>
   * In each iteration, compare the start time of the current interval with all the end times present in the heap.
   * <p>
   * If the earliest end time of all intervals seen so far (the root of the heap)
   * occurs before the start time of the current interval,
   * remove the earliest interval from the heap and push the current interval onto the heap.
   * <p>
   * Otherwise, allot a new meeting room, that is, add the current interval in the heap
   * without removing any existing interval.
   * <p>
   * After processing all the intervals, the size of the heap is the count of meeting rooms needed to hold the meetings.
   * </p>
   */

  public static class Interval {
    public int start, end;

    public Interval(int s, int e) {
      this.start = s;
      this.end = e;
    }
  }


}
