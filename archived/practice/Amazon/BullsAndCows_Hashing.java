package Amazon;


public class BullsAndCows_Hashing {

	public static void main(String[] args) {

		// https://leetcode.com/problems/bulls-and-cows/

		int x = '1' - '0';
		System.out.println(x);
		System.out.println(getHint("1807", "7810"));
		System.out.println(getHint("1123", "0111"));
		System.out.println(getHint("1122", "1222"));

	}

	public static String getHint(String secret, String guess) {

		int n = secret.length();
		int bulls = 0, cows = 0;

		int[] f = new int[10];

		for (char c : secret.toCharArray()) {
			f[c - '0']++;
		}

		for (int i = 0; i < n; i++) {

			if (secret.charAt(i) == guess.charAt(i)) {
				bulls++;
				f[guess.charAt(i) - '0']--;
			}
		}

		for (int i = 0; i < n; i++) {

			if (secret.charAt(i) != guess.charAt(i) && f[guess.charAt(i) - '0'] > 0) {

				cows++;
				
				f[guess.charAt(i) - '0']--;

			}
		}

		return bulls + "" + "A" + "" + cows + "B";

	}

}
