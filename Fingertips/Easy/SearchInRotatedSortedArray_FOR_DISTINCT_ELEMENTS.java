package Easy;

public class SearchInRotatedSortedArray_FOR_DISTINCT_ELEMENTS {

	public static void main(String[] args) {

	}

	public int search(int[] nums, int target) {

		int pivot = getPivot(nums, 0, nums.length - 1);

		// if pivot is not found means no rotation

		if (pivot == -1)
			return binarySearch(nums, 0, nums.length - 1, target);

		// if pivot is target index

		if (nums[pivot] == target)
			return pivot;

		// if target is less than 0th element then target lies on left side of pivot

		if (nums[0] <= target)
			return binarySearch(nums, 0, pivot - 1, target);

		// else right side of pivot

		return binarySearch(nums, pivot + 1, nums.length - 1, target);

	}

	private int binarySearch(int[] a, int s, int e, int target) {

		if (s > e)
			return -1;

		int mid = s + (e - s) / 2;

		if (target == a[mid])
			return mid;

		if (target > a[mid])
			return binarySearch(a, mid + 1, e, target);

		return binarySearch(a, s, mid - 1, target);
	}

	private int getPivot(int[] a, int s, int e) {

		if (s > e)
			return -1;

		if (s == e)
			return s;

		int mid = s + (e - s) / 2;

		if (mid < e && a[mid] > a[mid + 1])
			return mid;

		if (mid > s && a[mid] < a[mid - 1])
			return mid - 1;

		if (a[s] >= a[mid])
			return getPivot(a, s, mid - 1);

		return getPivot(a, mid + 1, e);

	}

}
