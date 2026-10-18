package topological_sort.course_schedule;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }
        int[] queue = new int[numCourses];
        int head = 0;
        int tail = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue[tail++] = i;
            }
        }
        while (head < tail) {
            int course = queue[head++];
            for (int next : graph.get(course)) {
                if (--indegree[next] == 0) {
                    queue[tail++] = next;
                }
            }
        }
        return tail == numCourses;
    }
}
