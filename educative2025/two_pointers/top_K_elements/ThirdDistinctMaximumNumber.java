package two_pointers.top_K_elements;

import java.util.*;

public class ThirdDistinctMaximumNumber {
    
    public static int thirdMax(int[] nums) {
        // Min-heap to store the top 3 distinct numbers
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        // Set to keep track of distinct elements already added to the heap
        Set<Integer> taken = new HashSet<>();
        
        // Iterate through the nums array
        for (int num : nums) {
            // Skip the number if it's already in the set
            if (taken.contains(num)) {
                continue;
            }
            
            // If the heap already contains 3 numbers
            if (heap.size() == 3) {
                // Check if current number is larger than the smallest in the heap
                if (heap.peek() < num) {
                    // Remove the smallest number from both heap and set
                    taken.remove(heap.poll());
                    
                    // Add the current larger number to the heap and the set
                    heap.offer(num);
                    taken.add(num);
                }
            } else {
                // If less than 3 numbers, add the current number to the heap and set
                heap.offer(num);
                taken.add(num);
            }
        }
        
        // Handle cases with fewer than 3 distinct numbers
        if (heap.size() == 1) {
            // Only one distinct number, return it
            return heap.peek();
        } else if (heap.size() == 2) {
            // Two distinct numbers, return the larger of the two
            int firstNum = heap.poll();
            return Math.max(firstNum, heap.peek());
        }

        // If 3 distinct numbers, return the third maximum (smallest in the heap)
        return heap.peek();
    }

    // Driver code
    public static void main(String[] args) {
        int[][] testCases = {
            {3, 2, 1},
            {1, 2},
            {2, 2, 3, 1},
            {5, 5, 4, 3, 2},
            {1, 1, 1, 1}
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.print((i + 1) + ".\tnums: " + Arrays.toString(testCases[i]));
            System.out.println("\n\tThe third maximum is: " + thirdMax(testCases[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
