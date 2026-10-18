package monotonic_stack.car_fleet;

import java.util.Arrays;

public class Solution {

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][];
        for (int i = 0; i < n; i++) {
            cars[i] = new int[]{position[i], speed[i]};
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));
        int fleets = 0;
        double slowestAhead = 0;
        for (int[] car : cars) {
            double arrival = (double) (target - car[0]) / car[1];
            if (arrival > slowestAhead) {
                fleets++;
                slowestAhead = arrival;
            }
        }
        return fleets;
    }
}
