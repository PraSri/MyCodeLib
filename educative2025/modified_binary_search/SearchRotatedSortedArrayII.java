class SearchRotatedSortedArrayII{
    public static boolean DUPLICATES_ALLOWED = true;
    public boolean search(int[] nums, int target) {
        return search_v2(nums, target)==-1 ? false : true ;
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
}
