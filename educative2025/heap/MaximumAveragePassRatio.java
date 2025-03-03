import java.util.PriorityQueue;
import java.util.Arrays;

public class MaximumAveragePassRatio {
    
    // Helper function to calculate the pass ratio gain when adding a student
    public static double gain(int passes, int total) {
        return ((double) (passes + 1) / (total + 1)) - ((double) passes / total);
    }
    
    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        // Create a max-heap (priority queue) based on the gain value
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        // Populate the heap with initial values
        for (int[] cls : classes) {
            maxHeap.offer(new double[]{gain(cls[0], cls[1]), cls[0], cls[1]});
        }

        // Distribute the extra students
        for (int i = 0; i < extraStudents; i++) {
            double[] top = maxHeap.poll(); // Extract the class with max gain
            int passes = (int) top[1] + 1;
            int total = (int) top[2] + 1;
            maxHeap.offer(new double[]{gain(passes, total), passes, total});
        }

        // Calculate the final average pass ratio
        double totalRatio = 0.0;
        for (double[] cls : maxHeap) {
            totalRatio += cls[1] / cls[2];
        }
        return totalRatio / classes.length;
    }

    public static void main(String[] args) {
        int[][][] classes = {
            {{1, 2}, {3, 5}, {2, 2}},  
            {{2, 4}, {3, 9}, {4, 5}, {2, 10}},  
            {{1, 3}, {2, 4}, {3, 6}},  
            {{5, 10}, {2, 3}, {3, 7}, {4, 8}},  
            {{10, 20}, {5, 5}, {8, 12}, {6, 15}}  
        };

        int[] extra_students = {2, 4, 3, 5, 3};

        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + ".\tClasses: " + Arrays.deepToString(classes[i]));
            System.out.println("\textraStudents: " + extra_students[i]);
            double result = maxAverageRatio(classes[i], extra_students[i]);
            System.out.println("\n\tFinal Average Pass Ratio: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
