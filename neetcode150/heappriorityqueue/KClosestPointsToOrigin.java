package heappriorityqueue;

import java.util.*;

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

}
