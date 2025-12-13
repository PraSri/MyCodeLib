package advancedgraphs;

import java.util.*;

public class MinCostToConnectPoints {


// create a class edge with x, y co-ordinates and cost

// initialize visited array, priority queue for edge objects sorted on cost

//take point 1 and put all the points from point1 to queue with cost as manhanttan distance

//mark first 0th node as visited

//initialize minCost = 0 which will be the answer

// loop till queue is empty or all nodes are accessed

// inside loop, pop one node, if its not visited add its cost in mincost

// mark it visited

// taking this node as reference push all the edges with manhanttan dist as cost in queue

// decrement the counter keeping track of total nodes accessed

// return minCost

    public static class Edge {
        int x, y, cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        int[] p1 = points[0];
        for (int i = 0; i < n; i++) {
            int[] p2 = points[i];
            int cost = getCost(p1[0], p1[1], p2[0], p2[1]);
            q.add(new Edge(0, i, cost));
        }
        visited[0] = true;
        int minCost = 0;
        int c = n - 1;
        while (!q.isEmpty() && c > 0) {
            Edge curr = q.poll();
            int y = curr.y;
            int cost = curr.cost;
            if (!visited[y]) {
                minCost += cost;
                visited[y] = true;
                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        int d = getCost(points[y][0], points[y][1], points[i][0], points[i][1]);
                        q.add(new Edge(y, i, d));
                    }
                }
                c--;
            }
        }
        return minCost;
    }

    public int getCost(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
