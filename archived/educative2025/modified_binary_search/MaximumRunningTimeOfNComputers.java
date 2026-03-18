package modified_binary_search;

import java.util.Arrays;

public class MaximumRunningTimeOfNComputers {
    public static long maxRunTime(int n, int[] batteries) {

        long totalPower = Arrays.stream(batteries).sum();
        long s = 0;
        long e = totalPower / n;

        while (s < e) {

            long mid = e - (e - s) / 2;
            long usable = 0;
            for (int b : batteries) {
                usable += Math.min(b, mid);
            }
            if (usable >= n * mid) {
                s = mid;
            } else {
                e = mid - 1;
            }
        }

        return s;

    }

    public static void main(String[] args) {
        long ans = maxRunTime(2, new int[]{3,3,3});
        System.out.println(ans);
    }
}
