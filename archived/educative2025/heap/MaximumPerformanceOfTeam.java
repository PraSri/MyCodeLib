package heap;

import java.util.*;

public class MaximumPerformanceOfTeam {
    // sort the engineers based on efficiency in descending order
    // use min heap for team speed
    // cal speedSum & current performance
    // update max so far
    // when min heap across k
    // pop and deduct speed sum
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {

        int mod = (int) 1e9 + 7;
        List<int[]> engineers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            engineers.add(new int[]{efficiency[i], speed[i]});
        }
        engineers.sort((a, b) -> b[0] - a[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        long max = 0, speedSum = 0;

        for (int[] engineer : engineers) {
            int eff = engineer[0];
            int spd = engineer[1];
            if (minHeap.size() == k) {
                speedSum -= minHeap.poll();
            }
            minHeap.add(spd);
            speedSum += spd;
            max = Math.max(max, speedSum * eff);
        }
        return (int) (max % mod);
    }
}
