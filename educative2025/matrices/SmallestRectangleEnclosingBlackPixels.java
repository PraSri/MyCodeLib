package matrices;

import java.util.Arrays;
import java.util.List;

public class SmallestRectangleEnclosingBlackPixels {


    // Function to calculate the area of the smallest rectangle enclosing all '1' pixels
    public static int minArea(char[][] image, int x, int y) {
        // Find the left boundary of the rectangle using binary search on columns
        int left = binarySearch(0, y, mid -> containsBlackPixelInColumn(image, mid));
        // Find the right boundary using binary search on columns (invert the check condition)
        int right = binarySearch(y + 1, image[0].length, mid -> !containsBlackPixelInColumn(image, mid)) - 1;
        // Find the top boundary of the rectangle using binary search on rows
        int top = binarySearch(0, x, mid -> containsBlackPixelInRow(image, mid));
        // Find the bottom boundary using binary search on rows (invert the check condition)
        int bottom = binarySearch(x + 1, image.length, mid -> !containsBlackPixelInRow(image, mid)) - 1;

        // Calculate the area of the rectangle formed by the boundaries
        return (right - left + 1) * (bottom - top + 1);
    }

    // Check if a specific column contains a black pixel ('1')
    private static boolean containsBlackPixelInColumn(char[][] image, int mid) {
        for (char[] chars : image) {
            if (chars[mid] == '1') {
                return true;
            }
        }
        return false;
    }

    // Check if a specific row contains a black pixel ('1')
    private static boolean containsBlackPixelInRow(char[][] image, int mid) {
        for (char c : image[mid]) {
            if (c == '1') {
                return true;
            }
        }
        return false;
    }

    // A helper function to perform binary search with a custom check function
    private static int binarySearch(int low, int high, java.util.function.Predicate<Integer> checkFunc) {
        while (low < high) {  // Continue until the range is narrowed down to a single element
            int mid = (low + high) / 2;  // Calculate the mid-point of the current range
            if (checkFunc.test(mid)) {  // If the check function returns True for the mid-point
                high = mid;  // Narrow the search range to the left half
            } else {
                low = mid + 1;  // Otherwise, narrow the search range to the right half
            }
        }
        return low;  // Return the final position
    }

    // Helper function to print the image in the specified format
    private static void printImage(char[][] image) {
        System.out.print("Image: [");
        for (int i = 0; i < image.length; i++) {
            System.out.print("[");
            for (int j = 0; j < image[i].length; j++) {
                System.out.print("'" + image[i][j] + "'");
                if (j < image[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i < image.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Define a list of test cases with image matrices and coordinates (x, y)
        List<Object[]> testCases = Arrays.asList(new Object[]{new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '0', '1', '0'}}, 1, 2}, new Object[]{new char[][]{{'0', '0', '0', '0'}, {'0', '1', '1', '1'}, {'0', '1', '1', '1'}, {'0', '1', '1', '1'}, {'0', '0', '0', '0'}}, 2, 1}, new Object[]{new char[][]{{'0', '0', '0', '0'}, {'0', '1', '0', '0'}, {'1', '1', '1', '0'}, {'0', '0', '0', '0'}}, 2, 1}, new Object[]{new char[][]{{'1', '0', '0', '0'}, {'0', '1', '0', '0'}, {'0', '0', '1', '0'}, {'0', '0', '0', '1'}}, 1, 1}, new Object[]{new char[][]{{'0', '1', '0', '0', '0'}, {'1', '1', '1', '0', '0'}, {'0', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}, 1, 2});

        // Iterate through each test case and calculate the area of the smallest rectangle
        for (int i = 0; i < testCases.size(); i++) {
            Object[] testCase = testCases.get(i);
            char[][] image = (char[][]) testCase[0];
            int x = (int) testCase[1];
            int y = (int) testCase[2];

            System.out.println((i + 1) + ".\t");
            printImage(image);
            System.out.println("\tCoordinates: x = " + x + ", y = " + y);
            int area = minArea(image, x, y);  // Call the function to get the result
            System.out.println("\n\tThe area of the smallest enclosing rectangle is: " + area);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

