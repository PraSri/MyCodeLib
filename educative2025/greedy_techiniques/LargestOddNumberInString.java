import java.util.ArrayList;
import java.util.List;

public class LargestOddNumberInString {

    // Function to find the largest odd-numbered substring
    public static String largestOddNumber(String num) {
        // Traverse the string from the end to the beginning
        for (int i = num.length() - 1; i >= 0; i--) {
            // Check if the current digit is odd
            if (Character.getNumericValue(num.charAt(i)) % 2 == 1) {
                // Return the substring from the start to the last odd digit
                return num.substring(0, i + 1);
            }
        }
        // If no odd digit is found, return an empty string
        return "";
    }

    // Driver code
    public static void main(String[] args) {
        // List of test cases
        List<String> testCases = new ArrayList<>();
        testCases.add("345679");  // Example with multiple odd digits
        testCases.add("357");     // Example with all odd digits
        testCases.add("2468");    // Example with no odd digit
        testCases.add("5");       // Example with a single odd digit
        testCases.add("74");      // Example with one odd and one even digit
        testCases.add("4597680"); // Example with even digits at the end

        // Execute test cases and print results
        for (int i = 0; i < testCases.size(); i++) {
            String testCase = testCases.get(i);
            String result = largestOddNumber(testCase);

            // Print the results
            System.out.println((i + 1) + ".\tnum: " + testCase);
            System.out.println("\tResult: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
