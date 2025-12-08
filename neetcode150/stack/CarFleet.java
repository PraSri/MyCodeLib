package stack;

import java.util.*;

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
            if(car[i][1] > curr) {
                curr = car[i][1];
                fleets++;
            }
        }

        return fleets;
    }

}
