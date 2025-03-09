class ClosestPoints {

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
        PriorityQueue<Point> maxHeap = new PriorityQueue< >((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());
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
        return new ArrayList< >(maxHeap);
    }

    // Driver code
    public static void main(String[] args) {
        Point[] pointsOne = new Point[] {
            new Point(1, 3), new Point(3, 4), new Point(2, -1)
        };
        Point[] pointsTwo = new Point[] {
            new Point(1, 3), new Point(2, 4), new Point(2, -1), new Point(-2, 2), new Point(5, 3), new Point(3, -2)
        };
        Point[] pointsThree = new Point[] {
            new Point(1, 3), new Point(5, 3), new Point(3, -2), new Point(-2, 2)
        };
        Point[] pointsFour = new Point[] {
            new Point(2, -1), new Point(-2, 2), new Point(1, 3), new Point(2, 4)
        };
        Point[] pointsFive = new Point[] {
            new Point(1, 3), new Point(2, 4), new Point(2, -1), new Point(-2, 2), new Point(5, 3), new Point(3, -2), new Point(5, 3), new Point(3, -2)
        };
        Point[][] points = {pointsOne, pointsTwo, pointsThree, pointsFour, pointsFive};
        int[] kList = {2, 3, 1, 4, 5};
        for (int i = 0; i < points.length; i++) {

            System.out.print((i + 1) + ".\tSet of points: ");
            for (Point p: points[i])
                System.out.print("[" + p.x + " , " + p.y + "] ");
            System.out.println("\n\tK: " + kList[i]);
            List<Point> result = ClosestPoints.kClosest(points[i], kList[i]);
            System.out.print("\tHere are the k = " + kList[i] + " points closest to the origin(0, 0): ");
            for (Point p: result)
                System.out.print("[" + p.x + " , " + p.y + "] ");
            System.out.println("\n");
            System.out.println(PrintHyphens.repeat("-", 100));
        }
    }
}
