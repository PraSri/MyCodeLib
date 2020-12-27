package Hashing;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring_Using_Queue("geeksforgeeks"));
		System.out.println(lengthOfLongestSubstring_Using_Set("geeksforgeeks"));
		System.out.println(
				lengthOfLongestSubstring("geeksforgeeks") == lengthOfLongestSubstring_Using_Queue("geeksforgeeks"));

	}

	public static int lengthOfLongestSubstring(String str) {

		int n = str.length();
		if (n == 0)
			return 0;

		// s=start , e = end , c = counter = this counter keep track of ocurring char
		// which appear more than once so this increase by i.e. as any char repeats ..
		// it will trigger while loop which now update start pointer decrese counter as
		// it get repeated char
		// maxLength keep the max window size from e-s
		int s = 0, e = 0, c = 0, maxLength = -1;

		// for ex : geeksforgeeks ans = 7 (eksforg)
		// keep count of chars in window
		int[] f = new int[256];

		while (e < n) {
			if (f[str.charAt(e)] > 0) {
				c++;
			}
			f[str.charAt(e)]++;
			e++;
			while (c > 0) {

				if (f[str.charAt(s)] > 1) {
					c--;
				}
				f[str.charAt(s)]--;
				s++;

			}

			maxLength = Math.max(maxLength, e - s);
		}

		return maxLength;

	}

	// O(n^2)
	public static int lengthOfLongestSubstring_Using_Queue(String s) {
		Queue<Character> queue = new LinkedList<Character>();
		int res = 0;
		for (char c : s.toCharArray()) {
			while (queue.contains(c)) {
				queue.poll();
			}
			queue.offer(c);
			res = Math.max(res, queue.size());
		}
		return res;
	}

	public static int lengthOfLongestSubstring_Using_Set(String s) {
		Set<Character> set = new HashSet<Character>();
		int res = 0;
		for (char c : s.toCharArray()) {
			while (set.contains(c)) {
				set.remove(c);
			}
			set.add(c);
			res = Math.max(res, set.size());
		}
		return res;
	}

}
