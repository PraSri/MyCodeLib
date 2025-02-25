package p0s07cu;

import java.util.*;

public class IntervalListIntersection {

  public static int[][] intervalsIntersection(int[][] intervalLista, int[][] intervalListb) {

    List<int[]> intersections = new ArrayList<>(); // to store all intersecting intervals

    // index "i" to iterate over the length of list a and index "j"
    // to iterate over the length of list b
    int i = 0, j = 0;

    // while loop will break whenever either of the lists ends
    while (i < intervalLista.length && j < intervalListb.length) {

      // Let's check if intervalLista[i] intersects intervalListb[j]

      // 1. start - the potential start point of the intersection
      // 2. end - the potential endpoint of the intersection

      int start = Math.max(intervalLista[i][0], intervalListb[j][0]); // started late
      int end = Math.min(intervalLista[i][1], intervalListb[j][1]); // ended early

      // if this is an actual intersection
      if (start <= end) {
        intersections.add(new int[] {start, end}); // add it to the list
      }

      // Move forward in the list whose interval ends earlier
      if (intervalLista[i][1] < intervalListb[j][1]) {
        i += 1;
      } else {
        j += 1;
      }

    }

    return intersections.toArray(new int[0][]);
  }

  // Driver code
  public static void main(String[] args) {
    int[][][] inputIntervalLista = {
        {{1, 2}},
        {{1, 4}, {5, 6}, {9, 15}},
        {{3, 6}, {8, 16}, {17, 25}},
        {{4, 7}, {9, 16}, {17, 28}, {39, 50}, {55, 66}, {70, 89}},
        {{1, 3}, {5, 6}, {7, 8}, {12, 15}}
    };

    int[][][] inputIntervalListb = {
        {{1, 2}},
        {{2, 4}, {5, 7}, {9, 15}},
        {{2, 3}, {10, 15}, {18, 23}},
        {{3, 6}, {7, 8}, {9, 10}, {14, 19}, {23, 33}, {35, 40}, {45, 59}, {60, 64}, {68, 76}},
        {{2, 4}, {7, 10}}
    };

    for (int i = 0; i < inputIntervalLista.length; i++) {
      System.out.println(
          i + 1 + ".\t Interval List A: " + Arrays.deepToString(inputIntervalLista[i]));
      System.out.println("\t Interval List B: " + Arrays.deepToString(inputIntervalListb[i]));
      System.out.println("\t Intersecting intervals in 'A' and 'B' are: " +
          Arrays.deepToString(intervalsIntersection(inputIntervalLista[i], inputIntervalListb[i])));
      System.out.println(new String(new char[100]).replace('\0', '-'));
    }
  }
}
