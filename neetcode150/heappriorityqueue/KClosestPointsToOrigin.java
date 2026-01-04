package heappriorityqueue;

import java.util.*;

/**Kth Largest Element in an Array (Medium)
👉 https://leetcode.com/problems/kth-largest-element-in-an-array/

Top K Frequent Elements (Medium)
👉 https://leetcode.com/problems/top-k-frequent-elements/

Top K Frequent Words (Medium)
👉 https://leetcode.com/problems/top-k-frequent-words/

Find Nearest Point That Has the Same X or Y Coordinate (Easy)
👉 https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/

Minimum Rectangles to Cover Points (Medium)
👉 https://leetcode.com/problems/minimum-rectangles-to-cover-points/

K-th Nearest Obstacle Queries (Medium)
👉 https://leetcode.com/problems/k-th-nearest-obstacle-queries/*/

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        int[][] res = new int[k][2];

        Comparator<Pair> comparator = new Comparator<Pair>(){

            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.d.compareTo(o2.d);
            }

        };

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(comparator);

        for(int i = 0;i<points.length;i++) {
            int x = points[i][0];
            int y = points[i][1];
            long d = (long) (x*x) + (long) (y*y);
            minHeap.add(new Pair(x, y, d));
        }

        int i = 0;

        while(k>0) {
            Pair p = minHeap.poll();
            res[i][0] = p.x;
            res[i][1] = p.y;
            i++;
            k--;
        }

        return res;
    }

    public static class Pair {
        int x, y;
        Long d;
        public Pair(int x, int y, long d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }


    /**
     * Kth Largest Element in an Array
     * https://leetcode.com/problems/kth-largest-element-in-an-array
     */
    public static class KthLargestElementInAnArray {
    }

    /**
     * Top K Frequent Elements
     * https://leetcode.com/problems/top-k-frequent-elements
     */
    public static class TopKFrequentElements {
    }

    /**
     * Top K Frequent Words
     * https://leetcode.com/problems/top-k-frequent-words
     */
    public static class TopKFrequentWords {
    }

    /**
     * Find Nearest Point That Has the Same X or Y Coordinate
     * https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate
     */
    public static class FindNearestPointThatHasTheSameXOrYCoordinate {
    }

    /**
     * Minimum Rectangles to Cover Points
     * https://leetcode.com/problems/minimum-rectangles-to-cover-points
     */
    public static class MinimumRectanglesToCoverPoints {
    }

    /**
     * K-th Nearest Obstacle Queries
     * https://leetcode.com/problems/k-th-nearest-obstacle-queries
     */
    public static class KThNearestObstacleQueries {
    }
}
