package graphs;

import java.util.*;

public class CourseSchedule {

    List<Integer> sortedOrder = new LinkedList<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // topological sort
        // indegree 0 wale queue me daalo
        // phir unke neighbours ki indegree reduce krke queue me daalo
        // jab sab visit ho jae to order print krdo

        int n = numCourses;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n + 1];

        for (int[] edge : prerequisites) {
            int parent = edge[0];
            int child = edge[1];
            indegree[child]++;
            graph.get(parent).add(child);
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int counter = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            counter++;
            sortedOrder.add(curr);
            for (Integer neigh : graph.get(curr)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.add(neigh);
                }
            }
        }
        return counter == n;
    }

    // CourseSchedule II -> return order of courses
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(canFinish(numCourses, prerequisites))
            return sortedOrder.stream().mapToInt(Integer::intValue).toArray();
        else
            return new int[]{};
    }


}
