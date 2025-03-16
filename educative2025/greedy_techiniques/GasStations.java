import java.util.*;

class GasStations {
    public static int gasStationJourney(int[] gas, int[] cost) {
        // Check if it is possible to complete the journey based on total gas and cost.
        if (Arrays.stream(cost).sum() > Arrays.stream(gas).sum()) {
            return -1;
        }
        // Initialize variables for tracking total gas and starting index.
        int currentGas = 0;
        int startingIndex = 0;
        // Iterate over all gas stations in the list.
        for (int i = 0; i < gas.length; i++) {
            // Update current gas level by adding gas and subtracting cost at current station.
            currentGas += gas[i] - cost[i];
            // If the current gas level is negative, reset it to zero and update the starting index.
            if (currentGas < 0) {
                currentGas = 0;
                startingIndex = i + 1;
            }
        }
        // Return starting index of gas station that allows journey to be completed.
        return startingIndex;
    }
    // Driver code
    public static void main(String[] args) {

        int[][] gas = {
            {1, 2, 3, 4, 5},
            {2, 3, 4},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 10},
            {1, 1, 1, 1, 1},
            {1, 2, 3, 4, 5}
        };
        int[][] cost = {
            {3, 4, 5, 1, 2},
            {3, 4, 5},
            {1, 2, 3, 4, 5},
            {2, 2, 1, 3, 1},
            {1, 0, 1, 2, 3},
            {1, 2, 3, 4, 5}
        };
        for (int i = 0; i < cost.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tGas: " + Arrays.toString(gas[i]));
            System.out.println("\tCost: " + Arrays.toString(cost[i]));
            System.out.println("\tThe index of the gas station we can start our journey from is "+ gasStationJourney(gas[i], cost[i])+ " (If it's -1, then that means no solution exists)");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

}
