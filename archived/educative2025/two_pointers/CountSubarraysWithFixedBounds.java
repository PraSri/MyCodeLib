package two_pointers;

public class CountSubarraysWithFixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;

        // Initialize pointers:
        // minPos  – last index where minK was seen
        // maxPos  – last index where maxK was seen
        // leftBound – last index where an out-of-range element (not in [minK, maxK]) was seen
        int minPos = -1;
        int maxPos = -1;
        int leftBound = -1;

        // Initialize total count of valid subarrays
        long count = 0;

        // Iterate through the array
        for (int i = 0; i < n; i++) {

            // If current number is out of range, reset the window
            if (nums[i] < minK || nums[i] > maxK) {
                leftBound = i;        // This becomes the new invalid boundary
                minPos = -1;          // Reset positions of minK and maxK
                maxPos = -1;
            }

            // Update minPos if current number equals minK
            if (nums[i] == minK) {
                minPos = i;
            }

            // Update maxPos if current number equals maxK
            if (nums[i] == maxK) {
                maxPos = i;
            }

            // If both minK and maxK have been seen since the last invalid element
            if (minPos != -1 && maxPos != -1) {
                // Calculate number of valid subarrays ending at index i
                // The valid start indices range from (leftBound + 1) to min(minPos, maxPos)
                // Each start index in this range gives a valid subarray ending at i
                count += Math.max(0, Math.min(minPos, maxPos) - leftBound);
            }
        }

        // Return the total number of valid subarrays
        return count;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] testCases = {
                {1, 3, 5, 2, 7, 5},
                {1, 5},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4},
                {1, 5, 1, 5, 1, 5}
        };
        int[] minKs = {1, 1, 1, 2, 1};
        int[] maxKs = {5, 5, 1, 5, 5};

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int minK = minKs[i];
            int maxK = maxKs[i];
            System.out.println((i + 1) + ".\tnums = " + java.util.Arrays.toString(nums));
            System.out.println("\tminK = " + minK);
            System.out.println("\tmaxK = " + maxK);

            CountSubarraysWithFixedBounds sol = new CountSubarraysWithFixedBounds();
            long result = sol.countSubarrays(nums, minK, maxK);
            System.out.println("\n\tOutput: " + result);
            System.out.println("-".repeat(100));
        }
    }
}
