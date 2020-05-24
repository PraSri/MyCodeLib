package Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MergeIntervals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to their
	 * start times.
	 * 
	 */
	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
		intervals.add(newInterval);
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		ArrayList<Interval> a = new ArrayList<>();
		a.add(intervals.get(0));
		for (int i = 1; i < intervals.size(); i++) {
			if (intervals.get(i).start <= a.get(a.size() - 1).end) {
				a.get(a.size() - 1).end = Math.max(a.get(a.size() - 1).end, intervals.get(i).end);
			} else {
				a.add(intervals.get(i));
			}
		}
		return a;
	}

//	Definition for an interval.
	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
}
