package two_pointers.top_K_elements;

import java.util.*;

public class KClosestPoints {
   static class ClosestPoints {

        public static class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int distFromOrigin() {
                // ignoring sqrt
                return (x * x) + (y * y);
            }
        }

        public static List<Point> kClosest(Point[] points, int k) {
            PriorityQueue<Point> maxHeap = new PriorityQueue<>((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());
            // put first 'k' points in the max heap
            for (int i = 0; i < k; i++)
                maxHeap.add(points[i]);

            // go through the remaining points of the input array, if a Location is closer to the origin than the top Location
            // of the max-heap, remove the top Location from heap and add the Location from the input array
            for (int i = k; i < points.length; i++) {
                if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
                    maxHeap.poll();
                    maxHeap.add(points[i]);
                }
            }

            // the heap has 'k' points closest to the origin, return them in a list
            return new ArrayList<>(maxHeap);
        }
    }

    // Driver code
}
