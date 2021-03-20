package Amazon;

public class FirstUniqueCharacterInString {

	public static void main(String[] args) {

	}

	public int firstUniqChar(String s) {

		int[] f = new int[200];

		for (char c : s.toCharArray()) {
			f[c - '0']++;
		}

		for (int i = 0; i < s.length(); i++) {
			if (f[s.charAt(i) - '0'] == 1) {
				return i;
			}
		}

		return -1;

	}

	public class Lookup {
		public int index, count;

		public Lookup(int index, int count) {
			this.index = index;
			this.count = count;
		}
	}

	public int firstUniqCharSingleStringScan(String s) {

		Lookup[] f = new Lookup[256];

		for (int i = 0; i < s.length(); i++) {

			if (f[s.charAt(i) - '0'] == null) {
				Lookup l = new Lookup(i, 1);
				f[s.charAt(i) - '0'] = l;
			} else {
				f[s.charAt(i) - '0'].count++;
			}

		}

		int res = Integer.MAX_VALUE;

		for (int i = 0; i < 256; i++) {
			if (f[i] != null && f[i].count == 1) {
				if (f[i].index < res) {
					res = f[i].index;
				}
			}
		}

		return res == Integer.MAX_VALUE ? -1 : res;

	}

}
