package QuickHelper;

public class LastOccurrenceInSortedArray {
	
	public static int getLastOccurrenceInSortedArray(int[] a, int x) {
		return binarySearchLast(a, 0, a.length - 1, x);
	}

	private static int binarySearchLast(int[] a, int s, int e, int x) {

		int mid;
		int ans = -1;
		while (s <= e) {

			mid = s + (e - s) / 2;
			if (a[mid] == x) {
				ans = mid;
				s = mid + 1;
			} else if (a[mid] > x) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}

		return ans;
	}

}
