package binary_search_on_answer.koko_eating_bananas;

public class Solution {

    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = 0;
        for (int pile : piles) {
            hi = Math.max(hi, pile);
        }
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (hoursNeeded(piles, mid) <= h) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private long hoursNeeded(int[] piles, int speed) {
        long hours = 0;
        for (int pile : piles) {
            hours += (pile + (long) speed - 1) / speed;
        }
        return hours;
    }
}
