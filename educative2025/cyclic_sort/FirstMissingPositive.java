package cyclic_sort;

public class FirstMissingPositive {

    public static int firstMissingPositiveInteger(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctSpot = nums[i] - 1;  // Determining the correct position of the current element
            // Check if the current element is in the range [1, n] and is not already at its correct position
            if (correctSpot >= 0 && correctSpot < nums.length && nums[i] != nums[correctSpot]) {
                // Swap the current element with the element at its correct position
                int temp = nums[i];
                nums[i] = nums[correctSpot];
                nums[correctSpot] = temp;
            } else {
                i++;  // Move on to the next element if the current element is already at its correct position or out of range
            }
        }

        // Iterate through the array again and check if each element is equal to its index plus 1
        for (int j = 0; j < nums.length; j++) {
            if (j + 1 != nums[j]) {
                return j + 1;  // Return the smallest missing positive integer
            }
        }

        return nums.length + 1;  // If all the elements are in order, return the next positive integer
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositiveInteger(new int[]{7,8,9,10,11}));
        System.out.println(firstMissingPositiveInteger(new int[]{2,2,3}));
    }


}
