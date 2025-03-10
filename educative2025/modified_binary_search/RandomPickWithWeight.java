import java.util.*;

class RandomPickWithWeight {
    private List<Integer> runningSums;
    private int totalSum;

    public RandomPickWithWeight(int[] weights) {
        // List to store running sums of weights
        runningSums = new ArrayList<>();
        // Variable to calculate running sum
        int runningSum = 0;

        // Iterate through the given weights
        for (int w : weights) {
            // Add the current weight to the running sum
            runningSum += w;
            // Append the running sum to the running_sums list
            runningSums.add(runningSum);
        }

        // Store the total sum of weights
        totalSum = runningSum;
    }

    // Method to pick an index based on the weights
    public int pickIndex() {
        // Generate a random number between 1 and the total sum of the array
        Random random = new Random();
        int target = random.nextInt(totalSum) + 1;
        // Initialize low and high variables for binary search
        int low = 0;
        int high = runningSums.size();

        // Perform binary search to find the first value higher than the target
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target > runningSums.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        // Return the index (low) found
        return low;
    }

    // used in driver code
    private static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }


    // driver code
    public static void main(String[] args) {
        int counter = 900;

        int[][] weights = {
            {1, 2, 3, 4, 5},
            {1, 12, 23, 34, 45, 56, 67, 78, 89, 90},
            {10, 20, 30, 40, 50},
            {1, 10, 23, 32, 41, 56, 62, 75, 87, 90},
            {12, 20, 35, 42, 55},
            {10, 10, 10, 10, 10},
            {10, 10, 20, 20, 20, 30},
            {1, 2, 3},
            {10, 20, 30, 40},
            {5, 10, 15, 20, 25, 30}
        };

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < weights.length; i++) {
            System.out.println((i + 1) + ".\tList of weights: " + Arrays.toString(weights[i]) + ", pick_index() called " + counter + " times" + "\n");
            for (int l = 0; l < weights[i].length; l++) {
                map.put(l, 0);
            }
            RandomPickWithWeight sol = new RandomPickWithWeight(weights[i]);
            for (int j = 0; j < counter; j++) {
                int index = sol.pickIndex();
                map.put(index, map.get(index) + 1);
            }
            System.out.println(new String(new char[100]).replace('\0', '-'));
            System.out.println("\t" + String.format("%-10s%-5s%-10s%-5s%-15s%-5s%-20s%-5s%-15s",
                    "Indexes", "|", "Weights", "|", "Occurrences", "|", "Actual Frequency", "|", "Expected Frequency"));
            System.out.println(new String(new char[100]).replace('\0', '-'));
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                System.out.println("\t" + String.format("%-10s%-5s%-10s%-5s%-15s%-5s%-20s%-5s%-15s",
                        key, "|", weights[i][key], "|", value, "|",
                        String.format("%.2f", ((double) value / counter) * 100) + "%", "|",
                        String.format("%.2f", ((double) weights[i][key] / sum(weights[i])) * 100) + "%"));
            }
            map.clear();
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
