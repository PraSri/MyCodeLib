import java.util.*;

public class LargestNumberAfterDigitSwapsParity {

    public static int largestInteger(int num) {
        // Convert the number into a list of digits
        String numStr = Integer.toString(num);
        int[] digits = new int[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            digits[i] = numStr.charAt(i) - '0';
        }

        // Create two heaps for odd and even digits
        PriorityQueue<Integer> oddHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> evenHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Fill the heaps
        for (int d : digits) {
            if (d % 2 == 0) {
                evenHeap.add(d);  // Use max-heap for even digits
            } else {
                oddHeap.add(d);   // Use max-heap for odd digits
            }
        }

        // Construct the result
        StringBuilder result = new StringBuilder();
        for (int d : digits) {
            if (d % 2 == 0) {
                // Get the largest even digit
                result.append(evenHeap.poll());
            } else {
                // Get the largest odd digit
                result.append(oddHeap.poll());
            }
        }

        // Convert the result string back to an integer
        return Integer.parseInt(result.toString());
    }

    public static void main(String[] args) {
        int[] testCases = {1234, 65875, 4321, 2468, 98123};
        for (int num : testCases) {
            System.out.println("\tInput number: " + num);
            System.out.println("\tOutput number: " + largestInteger(num));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
