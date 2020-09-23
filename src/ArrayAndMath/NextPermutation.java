package ArrayAndMath;

public class NextPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Implement the next permutation, which rearranges numbers into the numerically
	 * next greater permutation of numbers for a given array A of size N.
	 * 
	 * If such arrangement is not possible, it must be rearranged as the lowest
	 * possible order i.e., sorted in an ascending order.
	 * 
	 * NOTE:
	 * 
	 * The replacement must be in-place, do not allocate extra memory. DO NOT USE
	 * LIBRARY FUNCTION FOR NEXT PERMUTATION. Use of Library functions will
	 * disqualify your submission retroactively and will give you penalty points.
	 */

	public int[] nextPermutation(int[] A) {
		int i = A.length - 1;

		while (i > 0 && A[i - 1] >= A[i]) {
			i--;
		}
		i--;

		if (i >= 0) {
			int j = A.length - 1;
			while (j >= 0 && A[i] >= A[j]) {
				j--;
			}
			if (j >= 0) {
				swap(A, i, j);
			}
		}
		reverse(A, i);
		return A;
	}

	private static void swap(int[] nums, int i, int j) {

		int t;

		t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;

	}

	private static void reverse(int[] nums, int i) {

		int right = nums.length - 1;
		int left = i + 1;
		while (left < right) {
			if (nums[left] > nums[right]) {
				swap(nums, right, left);
			}
			left++;
			right--;
		}

	}

}
