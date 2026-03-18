package top_K_elements;

import java.util.*;

public class MaximalScoreAfterApplyingKOperations {

    public static int maxScore(int[] nums, int k) {
        // Create a max-heap by negating the elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Add all elements to the heap
        for (int num : nums) {
            maxHeap.add(num);
        }

        int score = 0;

        for (int i = 0; i < k; i++) {
            // Extract the largest element
            int largest = maxHeap.poll();
            score += largest;  // Add the largest element to the score

            // Update the element with (largest + 2) // 3 and push it back
            int reduced = (largest + 2) / 3;
            maxHeap.add(reduced);  // Add the reduced value back into the heap
        }

        return score;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {12, 18, 24, 6},
            {7, 14, 3},
            {50, 20, 15, 10, 5},
            {8, 16, 5, 12, 3, 7},
            {50, 50, 50, 50, 50, 50, 50, 50}
        };
        int[] ks = {3, 2, 4, 3, 5};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println((i + 1) + ".\tnums: " + Arrays.toString(testCases[i]));
            System.out.println("\tk: " + ks[i]);
            int result = maxScore(testCases[i], ks[i]);
            System.out.println("\n\tMaximum Score: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
