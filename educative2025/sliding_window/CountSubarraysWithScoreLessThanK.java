package sliding_window;

public class CountSubarraysWithScoreLessThanK {

    public long countSubarrays(int[] nums, long k) {
        long result = 0;         // Store the runningSum count of valid subarrays
        long runningSum = 0;          // Keeps the running sum of the current window
        int left = 0;            // Left boundary of the sliding window

        // Expand the window by moving the right boundary one step at a time
        for (int right = 0; right < nums.length; right++) {
            runningSum += nums[right];  // Add the current element to the runningSum

            // Calculate score for the current window
            long score = runningSum * (right - left + 1);

            // Shrink the window from the left until the score is less than k
            // Score = runningSum sum * number of elements in the current window
            while (left <= right && score >= k) {
                runningSum -= nums[left];  // Subtract the leftmost element
                left++;               // Move the left boundary to the right

                score = runningSum * (right - left + 1);
            }

            // All subarrays ending at 'right' and starting from 'left' to 'right' are valid
            // Their count is (right - left + 1)
            result += right - left + 1;
        }

        return result;  // Return the runningSum count of valid subarrays
    }

    // Driver code
    public static void main(String[] args) {
        CountSubarraysWithScoreLessThanK sol = new CountSubarraysWithScoreLessThanK();

        int[][] testArrays = {
                {2, 1, 4, 3, 5},
                {10, 1, 2},
                {12, 2, 2, 3},
                {5, 4, 2, 10},
                {7, 2, 9, 4, 6},
                {20, 30, 40},
                {11, 1, 3},
                {15, 22, 18, 30, 14, 28, 33, 19, 26, 12},
                {45, 31, 27, 38, 40, 29, 22, 47, 36, 25, 44, 33, 21, 30, 26},
                {100, 200, 300, 400, 500, 99, 98, 97}
        };

        long[] ks = {
                10, 15, 18, 25, 40, 10, 10, 600, 1000, 50
        };

        for (int i = 0; i < testArrays.length; i++) {
            int[] nums = testArrays[i];
            long k = ks[i];

            System.out.println((i + 1) + ".\tnums: " + java.util.Arrays.toString(nums));
            System.out.println("\tk: " + k);
            System.out.println("\n\tCount of subarrays = " + sol.countSubarrays(nums, k));
            System.out.println("-".repeat(100));
        }
    }
}
