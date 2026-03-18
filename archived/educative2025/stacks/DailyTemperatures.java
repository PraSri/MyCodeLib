package stacks;

import java.util.*;

public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;  // Get the number of days
        int[] output = new int[n];  // Initialize the output array with all elements set to 0
        Stack<Integer> stack = new Stack<>();  // Initialize an empty stack to hold indices of temperatures

        for (int i = 0; i < n; i++) {  // Loop through each day
            // While the stack is not empty and the current temperature is higher than the temperature
            // at the index stored at the top of the stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int j = stack.pop();  // Pop the top index from the stack
                output[j] = i - j;  // Calculate the number of days until a warmer temperature and store it in the output array
            }
            stack.push(i);  // Push the current index onto the stack
        }

        return output;  // Return the output array after processing all temperatures
    }

    public static void main(String[] args) {
        int[][] testCases = {{73, 74, 75, 71, 69, 72, 76, 73}, {30, 40, 50, 60}, {30, 60, 90}, {90, 60, 30}, {30, 30, 30, 30},};

        // Loop through the test cases and print the results
        for (int i = 0; i < testCases.length; i++) {
            int[] temperatures = testCases[i];
            System.out.println((i + 1) + "\ttemperature: " + Arrays.toString(temperatures));
            System.out.println("\n\toutput: " + Arrays.toString(dailyTemperatures(temperatures)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
