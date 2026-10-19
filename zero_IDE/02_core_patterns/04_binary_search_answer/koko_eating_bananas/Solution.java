public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = 0;
        for (int pile : piles) {
            hi = Math.max(hi, pile);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFinish(piles, mid, h)) {
                hi = mid; // Try smaller speed
            } else {
                lo = mid + 1; // Speed too slow
            }
        }

        return lo;
    }

    private boolean canFinish(int[] piles, int speed, int h) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + (long) speed - 1) / speed;
        }
        return totalHours <= h;
    }
}
