package Easy;

public class SearchInRotatedSortedArray_FOR_DUPLICATE_ELEMENTS {
	
	public static void main(String[] args) {
		System.out.println(searchInSortedRotatedWithDup(new int[] {3, 3, 3, 1, 2, 3}, 3));
	}

	public static int searchInSortedRotatedWithDup(int[] a, int x) {

		SearchInRotatedSortedArray_FOR_DISTINCT_ELEMENTS.DUPLICATES_ALLOWED = true;

		return SearchInRotatedSortedArray_FOR_DISTINCT_ELEMENTS.search_v2(a, x);

	}

}
