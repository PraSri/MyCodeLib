package GoldmanSachs;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumNumberOfEventsThatCanBeAttended {

	public static int maxEvents(int[][] events) {

		Arrays.sort(events, (a, b) -> a[0] - b[0]); // sort events increasing by start time

		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		int ans = 0, i = 0, n = events.length;

		for (int d = 1; d <= 100000; d++) {

			while (i < n && events[i][0] == d) { // Add new events that can attend on day `d`
				minHeap.add(events[i++][1]);
			}
			while (!minHeap.isEmpty() && minHeap.peek() < d) { // Remove events that are already closed
				minHeap.poll();
			}
			if (!minHeap.isEmpty()) { // Use day `d` to attend to the event that closes earlier
				ans++;
				minHeap.poll();
			}
		}
		return ans;
	}

	public static int maxEvents_v2(int[][] A) {
		Queue<Integer> pq = new PriorityQueue<>();
		Arrays.sort(A, (a, b) -> a[0] - b[0]);
		int i = 0, d = 0, res = 0, n = A.length;
		
		while (!pq.isEmpty() || i < n) {
			if (pq.isEmpty())
				d = A[i][0];
			while (i < n && A[i][0] == d)
				pq.offer(A[i++][1]);
			pq.poll();
			++res;
			++d;
			while (!pq.isEmpty() && pq.peek() < d)
				pq.poll();
		}
		return res;
	}
}
