package graphs;

import java.util.*;

/***Course Schedule II (Medium)
 https://leetcode.com/problems/course-schedule-ii/

 Graph Valid Tree (Medium)
 https://leetcode.com/problems/graph-valid-tree/

 Minimum Height Trees (Medium)
 https://leetcode.com/problems/minimum-height-trees/

 Course Schedule III (Hard)
 https://leetcode.com/problems/course-schedule-iii/

 Build a Matrix With Conditions (Hard)
 https://leetcode.com/problems/build-a-matrix-with-conditions/

 Course Schedule (Medium)
 https://leetcode.com/problems/course-schedule/

 Alien Dictionary (Hard)
 https://leetcode.com/problems/alien-dictionary/

 Minimum Height Trees (Medium)
 https://leetcode.com/problems/minimum-height-trees/

 Sequence Reconstruction (Medium)
 https://leetcode.com/problems/sequence-reconstruction/

 Course Schedule III (Hard)
 https://leetcode.com/problems/course-schedule-iii/

 Parallel Courses (Medium)
 https://leetcode.com/problems/parallel-courses/

 Find All Possible Recipes from Given Supplies (Medium)
 https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/

 Build a Matrix With Conditions (Hard)
 https://leetcode.com/problems/build-a-matrix-with-conditions/

 Sort Array by Moving Items to Empty Space (Hard)
 https://leetcode.com/problems/sort-array-by-moving-items-to-empty-space/
 ***/

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
        if (canFinish(numCourses, prerequisites))
            return sortedOrder.stream().mapToInt(Integer::intValue).toArray();
        else
            return new int[]{};
    }


    /**
     * Course Schedule II (Medium)
     * https://leetcode.com/problems/course-schedule-ii
     */
    public static class CourseScheduleIi {
    }

    /**
     * Graph Valid Tree (Medium)
     * https://leetcode.com/problems/graph-valid-tree
     */
    public static class GraphValidTree {
    }

    /**
     * Minimum Height Trees (Medium)
     * https://leetcode.com/problems/minimum-height-trees
     */
    public static class MinimumHeightTrees {
    }

    /**
     * Course Schedule III (Hard)
     * https://leetcode.com/problems/course-schedule-iii
     */
    public static class CourseScheduleIii {
    }

    /**
     * Build a Matrix With Conditions (Hard)
     * https://leetcode.com/problems/build-a-matrix-with-conditions
     */
    public static class BuildAMatrixWithConditions {
    }

    /**
     * Alien Dictionary (Hard)
     * https://leetcode.com/problems/alien-dictionary
     */
    public static class AlienDictionary {
    }

    /**
     * Sequence Reconstruction (Medium)
     * https://leetcode.com/problems/sequence-reconstruction
     */
    public static class SequenceReconstruction {
    }

    /**
     * Parallel Courses (Medium)
     * https://leetcode.com/problems/parallel-courses
     */
    public static class ParallelCourses {
    }

    /**
     * Find All Possible Recipes from Given Supplies (Medium)
     * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies
     */
    public static class FindAllPossibleRecipesFromGivenSupplies {
    }

    /**
     * Sort Array by Moving Items to Empty Space (Hard)
     * https://leetcode.com/problems/sort-array-by-moving-items-to-empty-space
     */
    public static class SortArrayByMovingItemsToEmptySpace {
    }
}
