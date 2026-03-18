package modified_binary_search;

class SingleOccurenceElementSortedArray {
    public static int singleNonDuplicate(int[] nums) {

        // initilaize the left and right pointer
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {

            // if mid is odd, decrement it to make it even
            int mid = l + (r - l) / 2;
            if (mid % 2 == 1) mid--;

            // if the elements at mid and mid + 1 are the same, then the single element must appear after the midpoint
            if (nums[mid] == nums[mid + 1]) {
                l = mid + 2;
            }
            // otherwise, we must search for the single element before the midpoint
            else {
                r = mid;
            }
        }
        return nums[l];
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 2, 2, 3, 3, 4, 4},
                {1, 1, 2, 2, 3, 4, 4, 5, 5},
                {1, 1, 2, 3, 3},
                {1, 1, 2},
                {0, 2, 2, 3, 3, 4, 4, 5, 5}
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.print(i + 1);
        }
    }
}
