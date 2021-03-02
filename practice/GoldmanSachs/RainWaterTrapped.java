package GoldmanSachs;

import java.util.Stack;

public class RainWaterTrapped {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * This is O(1) space complexity solution
	 * 
	 * My understanding of this solution is : I am moving from left to right keeping
	 * track of left max height and right max height initially i assigned 1st bar as
	 * left max height and last bar as right max height after that see which max
	 * height is smaller go to the smaller bar side and update its max height if
	 * upcoming bar is bigger than max then make it as max and add in diff with curr
	 * bar in result.........
	 * 
	 */

	public static int maxWater_2nestedLoop_solution(int[] arr, int n) {

		// To store the maximum water
		// that can be stored
		int res = 0;

		// For every element of the array
		// except first and last element
		for (int i = 1; i < n - 1; i++) {

			// Find maximum element on its left
			int left = arr[i];
			for (int j = 0; j < i; j++) {
				left = Math.max(left, arr[j]);
			}

			// Find maximum element on its right
			int right = arr[i];
			for (int j = i + 1; j < n; j++) {
				right = Math.max(right, arr[j]);
			}

			// Update maximum water value
			res += Math.min(left, right) - arr[i];
		}
		return res;
	}

	public int trap(final int[] heights) {
		if (heights.length <= 2) {
			return 0;
		}

		int left = 0, right = heights.length - 1, totalArea = 0;
		int leftMaxHeight = heights[left], rightMaxHeight = heights[right];

		while (left < right) {
			if (heights[left] < heights[right]) {
				leftMaxHeight = Math.max(leftMaxHeight, heights[++left]);
				totalArea += leftMaxHeight - heights[left];
			} else {
				rightMaxHeight = Math.max(rightMaxHeight, heights[--right]);
				totalArea += rightMaxHeight - heights[right];
			}
		}
		return totalArea;
	}

	public int trap_stack(int[] height) {
		if (height == null || height.length < 2)
			return 0;

		Stack<Integer> stack = new Stack<>();

		int water = 0, i = 0;

		while (i < height.length) {

			if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
				stack.push(i++);
			} else {

				int pre = stack.pop();

				if (!stack.isEmpty()) {
					// find the smaller height between the two sides
					int minHeight = Math.min(height[stack.peek()], height[i]);
					// calculate the area
					water += (minHeight - height[pre]) * (i - stack.peek() - 1);
				}
			}

		}
		return water;
	}

	// Java program to find maximum amount of water that can
	// be trapped within given set of bars.

	// Method for maximum amount of water
	static int findWater(int[] arr, int n) {
		// left[i] contains height of tallest bar to the
		// left of i'th bar including itself
		int left[] = new int[n];

		// Right [i] contains height of tallest bar to
		// the right of ith bar including itself
		int right[] = new int[n];

		// Initialize result
		int water = 0;

		// Fill left array
		left[0] = arr[0];
		for (int i = 1; i < n; i++)
			left[i] = Math.max(left[i - 1], arr[i]);

		// Fill right array
		right[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--)
			right[i] = Math.max(right[i + 1], arr[i]);

		// Calculate the accumulated water element by element
		// consider the amount of water on i'th bar, the
		// amount of water accumulated on this particular
		// bar will be equal to min(left[i], right[i]) - arr[i] .
		for (int i = 0; i < n; i++)
			water += Math.min(left[i], right[i]) - arr[i];

		return water;
	}

}
