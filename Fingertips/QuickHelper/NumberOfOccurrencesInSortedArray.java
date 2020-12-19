package QuickHelper;

public class NumberOfOccurrencesInSortedArray {

	public static void main(String[] args) {
		int[] a = new int[] { 4, 4, 8, 8, 8, 15, 16, 23, 23, 42 };
		int x = 1;
		System.out.println(getNumberOfOccurrencesInSortedArray(a, x));
	}

	public static int getNumberOfOccurrencesInSortedArray(int[] a, int x) {
		int l = LastOccurrenceInSortedArray.getLastOccurrenceInSortedArray(a, x);
		if (l == -1)
			return 0;
		int f = FirstOccurrenceInSortedArray.getFirstOccurrenceInSortedArray(a, x);
		return l - f + 1;
	}

}
