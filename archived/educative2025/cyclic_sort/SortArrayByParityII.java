package cyclic_sort;

public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] nums) {
        // Initialize two pointers:
        // i -> Moves across even indexes [0, 2, 4, ...]
        // j -> Moves across odd indexes [1, 3, 5, ...]
        int i = 0, j = 1;

        // Traverse the array while both pointers are within bounds
        while (i < nums.length && j < nums.length) {
            if (nums[i] % 2 == 0) {  // If number at even index 'i' is even, it's correctly placed
                i += 2;  // Move to the next even index
            } else if (nums[j] % 2 == 1) {  // If number at odd index 'j' is odd, it's correctly placed
                j += 2;  // Move to the next odd index
            } else {
                // If misplaced (odd at even index or even at odd index), swap them
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                // Move both pointers forward after swapping
                i += 2;
                j += 2;
            }
        }

        return nums;
    }


}
