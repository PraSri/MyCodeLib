import java.util.function.Predicate;

/**
 * Standalone Binary Search Templates
 * Memorize these 5 essential patterns:
 * 1. Exact Match Integer Search
 * 2. Lower Bound (First True / Leftmost)
 * 3. Upper Bound (Last True / Rightmost)
 * 4. Search on Answer (Monotonic Predicate)
 * 5. Floating Point Binary Search
 */
public class BinarySearchTemplates {

    // 1. Exact Match: [lo, hi] inclusive
    public static int binarySearchExact(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    // 2. Lower Bound: First index where nums[index] >= target (or predicate is true)
    // Invariant: Search space [lo, hi]. When predicate(mid) is true, answer <= mid, so hi = mid.
    public static int lowerBound(int[] nums, int target) {
        int lo = 0, hi = nums.length; // hi can be n if target > all elements
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // 3. Upper Bound: First index where nums[index] > target
    public static int upperBound(int[] nums, int target) {
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // 4. Binary Search on Answer: Find minimum integer x satisfying predicate
    public static long binarySearchOnAnswerMin(long minVal, long maxVal, Predicate<Long> isValid) {
        long lo = minVal, hi = maxVal;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (isValid.test(mid)) {
                hi = mid; // Try smaller
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // 5. Floating Point Binary Search: Fixed iterations (e.g. 100) avoids precision loops
    public static double binarySearchFloat(double lo, double hi, Predicate<Double> isValid) {
        for (int iter = 0; iter < 100; iter++) {
            double mid = lo + (hi - lo) / 2.0;
            if (isValid.test(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return lo;
    }
}
