package binarysearch;

/**
 * 
 * Search in Rotated Sorted Array II (Medium)
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

Find Minimum in Rotated Sorted Array (Medium)
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

Pour Water Between Buckets to Make Water Levels Equal (Medium)
https://leetcode.com/problems/pour-water-between-buckets-to-make-water-levels-equal/
 * 
*/
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {

        int n = nums.length;
        int s = 0;
        int e = n-1;

        while(s<=e){

            int mid = s + (e-s)/2;

            // mid hi answer hai
            if(nums[mid] == target) {
                return mid;
            }

            // left part sorted hai
            if(nums[s] <= nums[mid]) {
                // this part is sorted
                // agar target [s...mid] ki range me nhi hai
                // toh s ko shift krdo mid + 1
                // mid + 1 se end ke beech dhuno
                if(target > nums[mid] || target < nums[s]) {
                    s = mid + 1;
                } else {
                    // na toh, e = mid - 1 krdo
                    // matlab target s ..mid-1 ke beech hai
                    e = mid - 1;
                }
            } else {
                // search in this part
                if(target < nums[mid] || target > nums[e]) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            }
        }

        return -1;
    }

    /**
     * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array-ii/">LeetCode - Search in Rotated Sorted Array II</a>
     */
    public static class SearchInRotatedSortedArrayII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">LeetCode - Find Minimum in Rotated Sorted Array</a>
     */
    public static class FindMinimumInRotatedSortedArray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/pour-water-between-buckets-to-make-water-levels-equal/">LeetCode - Pour Water Between Buckets to Make Water Levels Equal</a>
     */
    public static class PourWaterBetweenBucketsToMakeWaterLevelsEqual {
        // placeholder
    }
}
