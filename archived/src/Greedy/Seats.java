package Greedy;

public class Seats {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Ques. There is a row of seats represented by string A. Assume that it
	 * contains N seats adjacent to each other. There is a group of people who are
	 * already seated in that row randomly. i.e. some are sitting together & some
	 * are scattered.
	 * 
	 * An occupied seat is marked with a character 'x' and an unoccupied seat is
	 * marked with a dot ('.')
	 * 
	 * Now your target is to make the whole group sit together i.e. next to each
	 * other, without having any vacant seat between them in such a way that the
	 * total number of hops or jumps to move them should be minimum.
	 * 
	 * In one jump a person can move to the adjacent seat (if available).
	 * 
	 * NOTE: 1. Return your answer modulo 10^7 + 3.
	 * 
	 * Hint: create array of seated positions let it be x it will be already sorted
	 * find median of X ...... ans += abs(pos[mid]-pos[i]) - abs(mid-i)
	 * 
	 * 
	 * 
	 */
	public int seats(String A) {

		return findMinJump(A.toCharArray());

	}

	private int findMinJump(char[] a) {

		int[] x = new int[a.length];
		int k = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 'x') {
				x[k] = i;
				k++;
			}
		}

		int mod = 10000003;
		int mid = k / 2;
		int ans = 0;
		for (int i = 0; i < k; i++) {
			ans += (Math.abs(x[mid] - x[i]) % mod - Math.abs(mid - i) % mod) % mod;
		}

		return ans % mod;
	}

}
