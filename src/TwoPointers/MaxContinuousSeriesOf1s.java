package TwoPointers;

public class MaxContinuousSeriesOf1s {

	public static void main(String[] args) {

		int[] A = new int[] { 1, 1, 0, 1, 1, 0, 0, 1, 1, 1 };
		int B = 1;
		for (int i : maxone(A, B)) {
			System.out.print(i + " ");
		}

	}

	public static int[] maxone(int[] A, int B) {

		int n = A.length;
		int r = 0, l = 0;
		int best = -1;
		int zc = 0;
		int s = 0, e = 0;

		while (r < n) {

			if (zc <= B) {
				if (A[r] == 0) {
					zc++;
				}
				r++;
			}

			if (zc > B) {
				if (A[l] == 0) {
					zc--;
				}
				l++;
			}

			if (r - l + 1 > best) {
				best = r - l + 1;
				s = l;
				e = r;
			}

		}
		int k = 0;
		int[] ans = new int[e - s];
		for (int x = s; x < e; x++) {
			ans[k++] = x;
		}

		return ans;

	}

//	public static int[] maxone(int[] A, int B) {
//
//		int n = A.length;
//		int ct = 0;
//		int i = 0;
//		int endIndex = Integer.MAX_VALUE;
//		int curMax = Integer.MIN_VALUE;
//		int startIndex = 0;
//		int best = 0, l = Integer.MAX_VALUE, m = Integer.MAX_VALUE;
//		while (i < n) {
//			int j = i;
//			int end = 0;
//			ct = 0;
//			while (j < n && A[j] == 1) {
//				j++;
//				ct++;
//			}
//			System.out.println("j= " + j + " ct = " + ct);
//			end = j - 1;
//			if (ct > curMax) {
//				curMax = ct;
//				endIndex = end;
//				startIndex = Math.abs(endIndex - curMax) + 1;
//				if (curMax + B < n)
//					best = curMax + B;
//				endIndex = startIndex + best;
//				if (startIndex < l && endIndex < m) {
//					l = startIndex;
//					m = endIndex;
//				}
//
//			}
//			i = j + 1;
//		}
//
//		System.out.println("curMax = " + curMax + " startIndex =" + startIndex + " endIndex = " + endIndex);
//		System.out.println("best = " + best + " l =" + l + " m = " + m);
//		int[] ans = new int[curMax];
//		for (int x = 0; x < ans.length; x++) {
//			ans[x] = startIndex;
//			startIndex++;
//		}
//		return ans;
//
//	}

}
