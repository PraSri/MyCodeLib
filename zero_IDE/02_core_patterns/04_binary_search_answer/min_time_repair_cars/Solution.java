public class Solution {
    public long repairCars(int[] ranks, int cars) {
        long lo = 1;
        // Upper bound: fastest mechanic repairs all cars alone
        int minRank = Integer.MAX_VALUE;
        for (int r : ranks) minRank = Math.min(minRank, r);
        long hi = (long) minRank * (long) cars * (long) cars;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (canRepair(ranks, cars, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean canRepair(int[] ranks, int cars, long time) {
        long totalCars = 0;
        for (int r : ranks) {
            totalCars += (long) Math.sqrt(time / (double) r);
            if (totalCars >= cars) return true;
        }
        return totalCars >= cars;
    }
}
