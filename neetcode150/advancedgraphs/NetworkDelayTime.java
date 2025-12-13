package advancedgraphs;

import java.util.*;

public class NetworkDelayTime {

    //Because all weights are positive ? Dijkstra is perfect.
    //
    //You use a priority queue (min-heap) that always gives you the node that can be reached in the least time so far.

    //Each element in the heap is {timeSoFar, node}.

    public int networkDelayTime(int[][] times, int n, int k) {

        // single-source shortest path ? Dijkstra’s Algorithm with a min-heap.

        Map<Integer, List<int[]>> adjList = new HashMap<>();

        for(int[] time: times) {
            int source = time[0];
            int dest = time[1];
            int w = time[2];
            adjList.computeIfAbsent(source, x -> new ArrayList<>()).add(new int[]{dest, w});
        }

        // Each element in the heap is {timeSoFar, node}.
        // sort heap on time
        // min time will be root
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        Set<Integer> visited = new HashSet<>();
        // put source in queue
        pq.add(new int[]{0, k});
        int delay = 0;

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int t = curr[0];
            int node = curr[1];
            if(visited.contains(node)) {
                continue;
            }
            visited.add(node);
            delay = Math.max(delay, t);
            List<int[]> neighbours = adjList.getOrDefault(node, new ArrayList<>());
            for(int[] neighbour: neighbours) {
                int neighbourNode = neighbour[0];
                int neighbourTime = neighbour[1];
                if(!visited.contains(neighbourNode)) {
                    int newTime = t + neighbourTime;
                    pq.offer(new int[]{newTime, neighbourNode});
                }
            }
        }

        if(visited.size() == n) {
            return delay;
        }
        return -1;
    }
}
