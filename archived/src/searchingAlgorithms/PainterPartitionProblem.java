package searchingAlgorithms;

public class PainterPartitionProblem {

	public static void main(String[] args) {

	}

	public static int paint(int A, int B, int[] C) {
		long mod = 10000003;
		int n = C.length;
		int mv = Integer.MIN_VALUE;
		long sum = 0;
		for (int x : C) {
			if (x > mv) {
				mv = x;
			}
			sum = sum % mod + x % mod;
		}

		long s = mv * B;
		long e = sum * B;
		long ans = s;
		while (s <= e) {
			long mid = (s + e) / 2;
			if (isPossible(A, C, mid / B)) {
				ans = mid % mod;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return (int) (ans % mod);

	}

	private static boolean isPossible(int a, int[] c, long l) {
		
		// numberOfPainter<=a
		
		int t = 0 , pc = 1;
		
		for(int i = 0 ;i<c.length;i++) {
			t = t + c[i];
			if(t>l) {
				t = c[i];
				pc++;
			}
		}
		
		if(pc>a)
			return false;

		return true;
	}

}
