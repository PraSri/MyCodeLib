package binarysearch;

/**Search in a Sorted Array of Unknown Size (Medium)
https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/

Maximum Count of Positive Integer and Negative Integer (Easy)
https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/ */
class BinarySearch {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        int e = n-1;
        while(s<=e) {
            int mid = s + (e-s)/2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                e = mid-1;
            } else if(nums[mid] < target){
                s = mid + 1;
            }
        }
        return -1;
    }

    /**
     * <a href="https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/">LeetCode - Search in a Sorted Array of Unknown Size</a>
     */
    public static class SearchInASortedArrayOfUnknownSize {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/">LeetCode - Maximum Count of Positive Integer and Negative Integer</a>
     */
    public static class MaximumCountOfPositiveIntegerAndNegativeInteger {
        // placeholder
    }
}
