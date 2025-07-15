package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsIII {

  public int mostBooked(int n, int[][] meetings) {

    int[] count = new int[n];
    PriorityQueue<Integer> available = new PriorityQueue<>();
    PriorityQueue<long[]> usedRoom = new PriorityQueue<>(
        (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));

    for (int i = 0; i < n; i++) {
      available.offer(i);
    }

    Arrays.sort(meetings, (a, b) -> (a[0] - b[0]));

    for (int i = 0; i < meetings.length; i++) {
      long startTime = meetings[i][0];
      long endTime = meetings[i][1];
      while (!usedRoom.isEmpty() && usedRoom.peek()[0] <= startTime) {
        int room = (int) usedRoom.poll()[1];
        available.offer(room);
      }
      if(available.isEmpty()) {
        long end = usedRoom.peek()[0];
        int room = (int) usedRoom.poll()[1];
        endTime = end + (endTime-startTime);
        available.offer(room);
      }
      int room = available.poll();
      usedRoom.offer(new long[]{endTime, room});
      count[room]++;
    }

    int max = 0;
    for(int i=0;i<n;i++) {
      if(count[i] > count[max]) {
        max = i;
      }
    }

    return max;

  }

}
