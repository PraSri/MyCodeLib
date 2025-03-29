package modified_binary_search;

import java.util.*;

public class KClosest {

  // The overall time complexity becomes O(logn+k)
  
  public static int binarySearch(int[] array, int target) {
        // initialize the left and the right pointer
        int left = 0;
        int right = array.length - 1;

        // find the first closest element to target while left is less than or equal to right
        while (left <= right) {
            // compute the middle index
            int mid = (left + right) / 2;

            // if the value at mid is equal to target, return mid
            if (array[mid] == target) {
                return mid;
            }

            // if the value at mid is less than target, move left forward
            if (array[mid] < target) {
                left = mid + 1;
            }

            // if the value at mid is greater than target, move right backward
            else {
                right = mid - 1;
            }
        }

        // if the target is not found, return the index where it should be inserted
        return left;
    }

  // time complexity = O(log(n-k) + k)
  public List<Integer> findClosestElementsOptimized(int[] arr, int k, int x) {
        int n = arr.length;
        int s = 0, e = n-k;
        List<Integer> res = new ArrayList<>();
        
        while(s<e) {
            int mid = s + (e-s)/2;
            if(x-arr[mid] > arr[mid+k]-x) {
                s = mid + 1;
            } else {
              e = mid;  
            }
        }
        
        for(int i = s; i<s+k; i++) {
            res.add(arr[i]);
        }
        
        return res;
    }
  
    public static List<Integer> findClosestElements(int[] nums, int k, int target) {
        
        List<Integer> closestElements = new ArrayList<>();

        // If the length of 'nums' is the same as k, return 'nums'
        if (nums.length == k) {
            for (int num : nums) {
                closestElements.add(num);
            }
            return closestElements;
        }

        // if target is less than or equal to first element in 'nums',
        // return the first k elements from 'nums'
        if (target <= nums[0]) {
            for (int i = 0; i < k; i++) {
                closestElements.add(nums[i]);
            }
            return closestElements;
        }

        // if target is greater than or equal to last element in 'nums',
        // return the last k elements from 'nums'
        if (target >= nums[nums.length - 1]) {
            for (int i = nums.length - k; i < nums.length; i++) {
                closestElements.add(nums[i]);
            }
            return closestElements;
        }

        // find the first closest element to target using binary search
        int firstClosest = binarySearch(nums, target);

        // initialize the sliding window pointers
        int windowLeft = firstClosest - 1;
        int windowRight = windowLeft + 1;

        // expand the sliding window until its size becomes equal to k
        while ((windowRight - windowLeft - 1) < k) {

            // if window's left pointer is going out of bounds, move the window rightward
            if (windowLeft == -1) {
                windowRight++;
                continue;
            }

            // if window's right pointer is going out of bounds
            // OR if the element pointed to by window's left pointer is closer to target than
            // the element pointed to by window's right pointer, move the window leftward
            if (windowRight == nums.length || Math.abs(nums[windowLeft] - target) <= Math.abs(nums[windowRight] - target)) {
                windowLeft--;
            }

            // if the element pointed to by window's right pointer is closer to target than
            // the element pointed to by window's left pointer, move the window rightward
            else {
                windowRight++;
            }
        }

        // return k closest elements present inside the window
        for (int i = windowLeft + 1; i < windowRight; i++) {
            closestElements.add(nums[i]);
        }
        return closestElements;
    }

    // Driver code
}
