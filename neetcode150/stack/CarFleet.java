package stack;

import java.util.*;

/**Car Fleet II
https://leetcode.com/problems/car-fleet-ii/

Count Collisions on a Road
https://leetcode.com/problems/count-collisions-on-a-road/*/

public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {

        double[][] car = new double[position.length][2]; // keep pos & time

        for(int i = 0; i < position.length; i++) {
            car[i][0] = position[i];
            car[i][1] = (double)(target - position[i]) / speed[i];
        }

        Arrays.sort(car, (a, b) -> Double.compare(b[0], a[0]));

        double curr = 0;
        int fleets = 0;

        for(int i = 0; i < position.length; i++) {
            // agr curr time chota hai curr fleet se
            // toh naya fleet banega
            // kyuki tez cars mil ek fleet hi banegi
            // slowest se agey nhi nikal skti 
            if(car[i][1] > curr) {
                curr = car[i][1];
                fleets++;
            }
        }

        return fleets;
    }

    /**
     * <a href="https://leetcode.com/problems/car-fleet-ii/">LeetCode - Car Fleet II</a>
     */
    public static class CarFleetII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/count-collisions-on-a-road/">LeetCode - Count Collisions on a Road</a>
     */
    public static class CountCollisionsOnARoad {
        // placeholder
    }

}
