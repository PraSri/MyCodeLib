package FirstAttemptSolved;

import QuickHelper.FirstOccurrenceInSortedArray;
import QuickHelper.LastOccurrenceInSortedArray;

public class CountNumberOfOccurrencesInSortedArray {

	public static void main(String[] args) {
		CountNumberOfOccurrencesInSortedArray ob = new CountNumberOfOccurrencesInSortedArray();
		int last = LastOccurrenceInSortedArray.getLastOccurrenceInSortedArray(new int[] { 1, 1, 1, 2, 2, 3 }, 3);
		System.out.println(last);
		int first = FirstOccurrenceInSortedArray.getFirstOccurrenceInSortedArray(new int[] { 1, 1, 1, 2, 2, 3 }, 3);
		System.out.println(first);
		int count = ob.getCountNumberOfOccurrencesInSortedArray(new int[] { 1, 1, 1, 2, 2, 3 }, 2);
		System.out.println(count);
	}

	public int getCountNumberOfOccurrencesInSortedArray(int[] a, int x) {

		// 1. get the first occurence of x in sorted array a using binary search

		int firstIndex = FirstOccurrenceInSortedArray.getFirstOccurrenceInSortedArray(a, x);
		
		if(firstIndex==-1)
			return 0;

		// 2. get the last occurence of x in sorted array a using binary search

		int lastIndex = LastOccurrenceInSortedArray.getLastOccurrenceInSortedArray(a, x);

		// 3. get the count = lastIndex - firstIndex + 1

		int count = lastIndex - firstIndex + 1;

		return count;

	}




}
