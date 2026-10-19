import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Sliding Window Maximum / Monotonic Deque Template
 *
 * Invariant:
 * 1. Deque stores INDICES in strictly decreasing order of their corresponding values.
 * 2. Head of deque (peekFirst) always holds the maximum of the current valid window.
 * 3. Before inserting index i:
 *    - Evict expired indices from head: index <= i - k
 *    - Evict smaller/equal values from tail: nums[deque.peekLast()] <= nums[i]
 */
public class MonotonicDeque {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 1. Remove indices outside current sliding window [i - k + 1, i]
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2. Maintain monotonic decreasing order: pop smaller values from tail
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // 3. Push current index
            dq.offerLast(i);

            // 4. Record result once first full window is established
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}
