package Easy;

public class SearchInRotatedSortedArray_FOR_DISTINCT_ELEMENTS {

	public static boolean DUPLICATES_ALLOWED = false;

	public static void main(String[] args) {

		System.out.println(search_v2(new int[] {3,3,3,3,3,4,5, 6, 7, 8, 9, 10, 1, 2, 3,3,3,3}, 3));
	}

	public static int search_v2(int[] a, int x) {
		int n = a.length;
		return helper(a, 0, n - 1, x);
	}

	private static int helper(int[] a, int s, int e, int x) {
		if (s > e) {
			return -1;
		}

		int mid = (s + e) / 2;

		if (a[mid] == x) {
			return mid;
		}
		
		if(DUPLICATES_ALLOWED) {
			// tricky case : 3,1,2,3,3,3,3
			
			if(a[s]==a[mid] && a[mid]==a[e]) {
				s++;
				e--;
				return helper(a, s, e, x);
			}
		}

		// 1st sub array is sorted...a[s..mid]
		if (a[s] <= a[mid]) {

			// check x lies in range[s,mid]
			if (x >= a[s] && x <= a[mid]) {
				return helper(a, s, mid-1, x);
			}

			return helper(a, mid + 1, e, x);
		}

		// if 1st sub array is not sorted then 2nd sub array must be sorted

		// check x lies in range[mid+1,e]

		if (x >= a[mid] && x <= a[e]) {
			return helper(a, mid + 1, e, x);
		}

		return helper(a, s, mid-1, x);
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
