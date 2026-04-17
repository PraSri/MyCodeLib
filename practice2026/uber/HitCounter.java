package uber;

import java.util.ArrayList;
import java.util.List;

public class HitCounter {
    // List to store all hit timestamps in chronological order
    private List<Integer> timestamps = new ArrayList<>();

    /**
     * Initialize the hit counter system
     */
    public HitCounter() {
    }

    /**
     * Record a hit at the given timestamp
     *
     * @param timestamp - the current timestamp (in seconds granularity)
     */
    public void hit(int timestamp) {
        timestamps.add(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes (300 seconds)
     *
     * @param timestamp - the current timestamp (in seconds granularity)
     * @return number of hits in the past 300 seconds from the given timestamp
     */
    public int getHits(int timestamp) {
        // Find the index of the first timestamp that is within the 300-second window
        // We search for the leftmost position where timestamp >= (currentTime - 299)
        int leftBoundaryIndex = binarySearchLeftmost(timestamp - 300 + 1);

        // All elements from leftBoundaryIndex to the end are within the 300-second window
        return timestamps.size() - leftBoundaryIndex;
    }

    /**
     * Binary search to find the leftmost index where timestamp >= target
     *
     * @param target - the target timestamp to search for
     * @return the index of the first element >= target, or list size if all elements < target
     */
    private int binarySearchLeftmost(int target) {
        int left = 0;
        int right = timestamps.size();

        // Binary search for the leftmost position where timestamp >= target
        while (left < right) {
            int mid = (left + right) >> 1;  // Equivalent to (left + right) / 2

            if (timestamps.get(mid) >= target) {
                // Mid element is >= target, search in left half (including mid)
                right = mid;
            } else {
                // Mid element is < target, search in right half (excluding mid)
                left = mid + 1;
            }
        }

        return left;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
