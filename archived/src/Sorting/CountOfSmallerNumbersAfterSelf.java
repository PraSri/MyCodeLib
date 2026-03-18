package Sorting;

public class CountOfSmallerNumbersAfterSelf {

	public static class Pair {
		int value, index;

		public Pair(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	public static int[] solve(int[] A) {

		int n = A.length;

		Pair[] items = new Pair[n];

		for (int i = 0; i < n; i++) {
			items[i] = new Pair(A[i], i);
		}

		int[] count = new int[n];

		mergeSort(0, n - 1, items, count);

		return count;

	}

	private static void mergeSort(int s, int e, Pair[] items, int[] count) {

		if (s >= e) {
			return;
		}

		int mid = (s + e) / 2;

		mergeSort(s, mid, items, count);
		mergeSort(mid + 1, e, items, count);
		merge(s, e, mid, items, count);

	}

	private static void merge(int s, int e, int mid, Pair[] items, int[] count) {

		int low = s, lowend = mid, high = mid + 1, highend = e;
		int n = highend - low + 1;
		Pair[] temp = new Pair[n];

		int rtCt = 0, i = low, j = high, idx = 0;

		while (i <= lowend && j <= highend) {
			if (items[i].value > items[j].value) {
				rtCt++;
				temp[idx++] = items[j++];
			} else {
				count[items[i].index] += rtCt;
				temp[idx++] = items[i++];
			}
		}

		while (i <= lowend) {
			count[items[i].index] += rtCt;
			temp[idx++] = items[i++];
		}

		while (j <= highend) {
			temp[idx++] = items[j++];
		}

		System.arraycopy(temp, 0, items, low, n);

	}

	public static void main(String[] args) {

		for (int i : solve(new int[] { 1, 5, 4, 2, 1 })) {

			System.out.print(i + " ");

		}

	}

}
