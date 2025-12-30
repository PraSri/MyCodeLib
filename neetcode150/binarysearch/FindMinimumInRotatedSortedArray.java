package binarysearch;


/***Search in Rotated Sorted Array (Medium)
https://leetcode.com/problems/search-in-rotated-sorted-array/

Find Minimum in Rotated Sorted Array II (Hard)
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/ */

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int n = nums.length;
        int s = 0;
        int e = n-1;

        while(s<e) {

            // agr kabhi bhi rotated sorted array me left side pe chota
            // number hai matlab array ya uska koi part pure sorted hai in ascending order
            // toh minimum bhi left side wala number hai
            if(nums[s]<nums[e]) {

                return nums[s];

            }

            int mid = s + (e-s)/2;

            // ye normal binary search toh hai nhi
            // toh mid kuch bhi ho skta hai
            // agr mid bada ya barabar hai left most number ke
            // toh left shift kro do mid+1
            if(nums[mid] >= nums[s]) {

                //Agar mid ki value start se badi ya barabar hai:
                //
                //iska matlab left part sorted hai
                //
                //pivot (minimum) left mein nahi ho sakta
                //
                //minimum right side mein hoga
                s = mid+1;

            } else {

                // iska matlab mid chota hai
                // koi left ke chota aaya hai
                // matlab s..mid sorted nhi hai
                // beech me kahi toh rotated hai
                // end shift krdo mid pe

                //Matlab mid kahi pivot ke aas-paas hai
                //
                //Minimum left side including mid mein hoga
                //Isliye end ko mid par le jaate hain.
                e = mid;

            }
            // s ... mid mid+1... e
            // ab mere pass 2 range possibilities hai
            // 1. [mid+1, e]
            // 2. [s, mid]

        }
        //s == e ho jaata hai ? wahi minimum element ka index hai.
        return nums[s];
    }

    /**
     * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">LeetCode - Search in Rotated Sorted Array</a>
     */
    public static class SearchInRotatedSortedArray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/">LeetCode - Find Minimum in Rotated Sorted Array II</a>
     */
    public static class FindMinimumInRotatedSortedArrayII {
        // placeholder
    }

}
