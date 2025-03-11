public class MaximumValueGivenIndexBoundedArray {
    // Method to calculate the sum
    public static int calculateSum(int index, int mid, int n) {
        int count = 0;

        // Calculate the sum on the left side of the index
        if (mid > index) {
            // If mid is greater than index, then the arithmetic sequence is
            // [mid - index, mid - index + 1, mid - 1, ..., mid]
            count += (mid + (mid - index)) * (index + 1) / 2;
        } else {
            // Otherwise, the arithmetic sequence is [1, 2, 3, ..., mid - 1, mid]
            // In addition, there will be (index - mid + 1) number of 1s
            count += (mid + 1) * mid / 2 + (index - mid + 1);
        }

        // Calculate the sum on the right side of the index
        if (mid >= n - index) {
            // If mid is greater than or equal to n - index, then the arithmetic series is
            // [mid, mid - 1, mid - 2, ... , mid - n + 1 + index]
            count += (mid + (mid - n + 1 + index)) * (n - index) / 2;
        } else {
            // Otherwise, the arithmetic sequence is [mid, mid - 1, ..., 2, 1]
            // In addition, there will be (n - index - mid) number of 1s
            count += (mid + 1) * mid / 2 + (n - index - mid);
        }

        // Subtract the mid at the index because it is counted twice
        return count - mid;
    }

    // Method to calculate the max mid
    public static int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;

        // Binary search for the maximum mid at the index
        while (left < right) {
            // Check if the current mid mid is a valid maximum mid
            int mid = (left + right + 1) / 2;

            // Move to the right half if valid
            if (calculateSum(index, mid, n) <= maxSum) {
                left = mid;
            } else {
                // Otherwise, move to the left half
                right = mid - 1;
            }
        }

        // The maximum valid mid at the index
        return left;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputList = {
            {6, 3, 18},
            {4, 2, 6},
            {3, 0, 3},
            {5, 3, 15},
            {7, 4, 20}
        };

        for (int i = 0; i < inputList.length; i++) {
            int n = inputList[i][0];
            int index = inputList[i][1];
            int maxSum = inputList[i][2];
            int result = maxValue(n, index, maxSum);
            System.out.println((i + 1) + ".\tInput: n = " + n + ", index = " + index + ", maxSum = " + maxSum);
            System.out.println("\tMaximum mid at index " + index + ": " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
