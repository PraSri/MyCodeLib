import java.util.PriorityQueue;

public class MinimumCostConnectSticks {
    public static int connectSticks(int[] sticks) {
        int totalCost = 0;
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int stick : sticks) {
            minHeap.add(stick);
        }
        
        while (minHeap.size() > 1) {
            int first = minHeap.remove();
            int second = minHeap.remove();
            
            int cost = first + second;
            totalCost += cost;
            
            minHeap.add(cost);
        }
        
        return totalCost;
    }
    
    public static void main(String[] args) {
        int[][] testCases = {
            {2, 4, 3},
            {1, 8, 3, 5},
            {5},
            {1, 2, 3, 4, 5},
            {7, 6, 8, 10}
        };
        
        for (int i = 0; i < testCases.length; i++) {
            int[] sticks = testCases[i];
            System.out.println((i + 1) + ".\tsticks: " + java.util.Arrays.toString(sticks));
            System.out.println("\tMinimum cost: " + connectSticks(sticks));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
