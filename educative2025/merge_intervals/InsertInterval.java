package p0s07cu;

import java.util.*;

public class InsertInterval {

  public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {

    int newStart = newInterval[0];
    int newEnd = newInterval[1];

    int i = 0;

    int n = existingIntervals.length;

    List<int[]> outputList = new ArrayList<>();

    // append all the intervals which are smaller than new interval
    while (i < n && existingIntervals[i][0] < newStart) {
      outputList.add(existingIntervals[i]);
      i += 1;
    }

    // append new interval or merge it with the last entry
    if (outputList.isEmpty() || outputList.get(outputList.size() - 1)[1] < newStart) {
      outputList.add(newInterval);
    } else {
      outputList.get(outputList.size() - 1)[1] =
          Math.max(outputList.get(outputList.size() - 1)[1], newEnd);
    }

    // append left over intervals in output list, also keep merging with last entry
    while (i < n) {
      int[] ei = existingIntervals[i];
      int start = ei[0];
      int end = ei[1];
      if (outputList.get(outputList.size() - 1)[1] < start) {
        outputList.add(ei);
      } else {
        outputList.get(outputList.size() - 1)[1] =
            Math.max(outputList.get(outputList.size() - 1)[1], end);
      }
      i += 1;
    }

    // transform the arrayList to array using toArray() function
    return outputList.toArray(new int[0][0]);
  }

  // Driver code
  public static void main(String[] args) {
    int[][] newIntervals = {
        {5, 7},
        {8, 9},
        {10, 12},
        {1, 3},
        {1, 10}
    };

    int[][][] existingIntervals = {
        {{1, 2}, {3, 5}, {6, 8}},
        {{1, 3}, {5, 7}, {10, 12}},
        {{8, 10}, {12, 15}},
        {{5, 7}, {8, 9}},
        {{3, 5}}
    };

    for (int i = 0; i < newIntervals.length; i++) {
      System.out.print((i + 1) + ".\tExisting intervals: ");
      System.out.println(Arrays.deepToString(existingIntervals[i]));
      System.out.println(
          "\tNew interval: [" + newIntervals[i][0] + ", " + newIntervals[i][1] + "]");
      int[][] output = insertInterval(existingIntervals[i], newIntervals[i]);
      System.out.println("\tUpdated intervals: " + Arrays.deepToString(output));
      System.out.println(new String(new char[100]).replace('\0', '-'));
    }
  }
}
