// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=list&envId=9zkoubot


// First apporach to think of is 2 binary search, first to find first occurence & second to find last occurrence

// Optimized approach is below with 2 pointers first & last

class FindFirstLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {

        return bs(nums, 0, nums.length - 1, target);
        
    }

    public static int[] bs(int[] a, int s, int e, int x) {
        int mid = 0;
        int ans[] = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        int f = -1;
        int l = -1;
        while(s<=e) {
            mid = s + (e-s)/2;
            if(a[mid] == x) {
                f = mid;
                l = mid;
                while(f > 0 && a[f-1] == x) {
                    f --;
                }
                while(l < a.length - 1 && a[l+1] == x) {
                    l ++;
                }
                break;
            } else if (a[mid] > x) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        ans[0] = f;
        ans[1] = l;
        return ans;
    }
}
