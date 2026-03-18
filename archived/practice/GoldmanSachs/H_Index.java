package GoldmanSachs;

public class H_Index {

	public static void main(String[] args) {

		int[] citations = new int[] { 3, 0, 6, 1, 5 };

		System.out.println(hIndex2(citations));

	}

// https://leetcode.com/problems/h-index-ii/discuss/71063/Standard-binary-search/73201
	// bucket sort // counting sort
	public static int hIndex(int[] citations) {
		int n = citations.length;
		int[] buckets = new int[n + 1];
		for (int c : citations) {
			if (c >= n) {
				buckets[n]++;
			} else {
				buckets[c]++;
			}
		}
		int count = 0;
		for (int i = n; i >= 0; i--) {
			count += buckets[i];
			if (count >= i) {
				return i;
			}
		}
		return 0;
	}

	/**** H-Index II >> Citations are sorted ********/

	public static int hIndex2(int[] c) {
		int n = c.length;

		int s = 0, e = n - 1;

		while (s < e) {

			int m = (s + e) / 2;

			// n-m is done to reverse the index , i.e. for n=5 0->5 1-> 4....
			// we have to find first element >= index or c[index]>=index or c[mid]>=n-mid
			if (c[m] < n - m) {
				s = m + 1;
			} else {
				e = m;
			}
		}

		if (s < n && c[s] >= n - s)
			return n - s;
		else
			return 0;
	}

}
