package microsoft;

import java.util.*;

public class MappingService {

    // Represents a 2D Point/Node in the graph
    static class Point {
        String id;
        double x, y;

        public Point(String id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    // Represents a Landmark at a specific point
    static class Landmark {
        String name;
        String type; // School, Hospital, etc.
        Point location;

        public Landmark(String name, String type, Point location) {
            this.name = name;
            this.type = type;
            this.location = location;
        }
    }

    static class Graph2D {
        private final Map<String, Point> points = new HashMap<>();
        private final Map<String, List<Edge>> adjacencyList = new HashMap<>();
        private final List<Landmark> landmarks = new ArrayList<>();

        public void addPoint(String id, double x, double y) {
            points.put(id, new Point(id, x, y));
            adjacencyList.putIfAbsent(id, new ArrayList<>());
        }

        public void addEdge(String u, String v, double distance) {
            adjacencyList.get(u).add(new Edge(v, distance));
            adjacencyList.get(v).add(new Edge(u, distance)); // Undirected
        }

        public void addLandmark(String name, String type, String pointId) {
            landmarks.add(new Landmark(name, type, points.get(pointId)));
        }

        /**
         * Requirement 1: Shortest Distance using Dijkstra
         */
        public double getShortestDistance(String startId, String endId) {
            PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.dist));
            Map<String, double[]> distances = new HashMap<>();

            for (String id : points.keySet()) distances.put(id, new double[]{Double.MAX_VALUE});
            distances.get(startId)[0] = 0;
            pq.add(new NodeDistance(startId, 0));

            while (!pq.isEmpty()) {
                NodeDistance current = pq.poll();
                if (current.id.equals(endId)) return current.dist;

                for (Edge edge : adjacencyList.get(current.id)) {
                    double newDist = distances.get(current.id)[0] + edge.distance;
                    if (newDist < distances.get(edge.to)[0]) {
                        distances.get(edge.to)[0] = newDist;
                        pq.add(new NodeDistance(edge.to, newDist));
                    }
                }
            }
            return -1; // Unreachable
        }

        /**
         * Requirement 2: Find Nearest Landmarks
         */
        public List<Landmark> getNearestLandmarks(String pointId, double radius) {
            Point center = points.get(pointId);
            List<Landmark> result = new ArrayList<>();

            for (Landmark lm : landmarks) {
                double dist = Math.sqrt(Math.pow(lm.location.x - center.x, 2) +
                        Math.pow(lm.location.y - center.y, 2));
                if (dist <= radius) {
                    result.add(lm);
                }
            }
            return result;
        }

        private static class Edge {
            String to;
            double distance;

            Edge(String to, double distance) {
                this.to = to;
                this.distance = distance;
            }
        }

        private static class NodeDistance {
            String id;
            double dist;

            NodeDistance(String id, double dist) {
                this.id = id;
                this.dist = dist;
            }
        }
    }
}
