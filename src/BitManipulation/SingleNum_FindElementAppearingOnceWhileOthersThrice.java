package BitManipulation;

public class SingleNum_FindElementAppearingOnceWhileOthersThrice {

	public static void main(String[] args) {

	}

	/***
	 * Given an array of integers, every element appears thrice except for one which
	 * occurs once.
	 * 
	 * Find that element which does not appear thrice.
	 * 
	 * NOTE: Your algorithm should have a linear runtime complexity.
	 * 
	 * Could you implement it without using extra memory?
	 * 
	 * 
	 */

	public int singleNumber_v1(final int[] A) {

		int n = A.length;

		int[] c = new int[64];

		for (int i = 0; i < n; i++) {

			int j = 0;

			int num = A[i];

			while (num > 0) {

				int isolateIthBit = num & 1;

				// if isolated it bit is 1 then it will increase the count at c[j]

				c[j] += isolateIthBit;

				num >>= 1;

				j++;

			}

		}

		// now c[] will have count of 1s

		// intuition : if any element occurs thrice then it will contribute 3 1s/0s due
		// to the element ocuuring once we will have 3x+1 1s/0s

		// c[] will have 1s count as => 3x or 3x+1 or 3x+3

		// so if we take c[]%3 left over remainder 1s will be of unique element ocuuring
		// once

		// below code is actually just binary to decimal conversion after c[]%3
		int power = 1;
		int ans = 0;
		for (int i = 0; i < 64; i++) {
			c[i] %= 3;
			ans += c[i] * power;
			power <<= 1;
		}

		return ans;

	}

	public int singleNumber_v2(final int[] A) {
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			int c = 0;
			for (int j = 0; j < A.length; j++) {
				if (((A[j] >> i) & 1) == 1) {
					c++;
				}
			}
			if (c % 3 == 1) {
				ans += (1 << i);
			}
		}
		return ans;
	}
}
