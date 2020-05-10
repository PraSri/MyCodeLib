package String;

public class SameFormatString {

	public static void main(String[] args) {

		System.out.println(solve_v2("HIRED", "HHHHIIIIRRRRREEEEDDD"));
		System.out.println(solve_v2("HIRE", "HHHHRRREEE"));

	}

	// this is givining TLE

	/*
	 * 
	 * 
	 * The main consept that you have used is correct but this problem has a
	 * constraint that you have to use constant space.
	 * 
	 * By building a new string t you are using extra space. And also the whole
	 * string puilding process is causing it to give tle.
	 * 
	 * Think of a different implementation of this approch in which you dont have to
	 * actually build a string.
	 * 
	 * 
	 * 
	 */

	public static int solve(final String A, final String B) {

		int n = B.length();

		String t = String.valueOf(B.charAt(0));

		for (int i = 1; i < n; i++) {

			if (B.charAt(i) != B.charAt(i - 1)) {

				t = t + String.valueOf(B.charAt(i));

			}

		}

//			System.out.println(t);

		if (t.equals(A))

			return 1;

		return 0;

	}

	public static int solve_v2(final String A, final String B) {

		int n = A.length();
		int m = B.length();
		int j = 0;
		int i = 0;

		// "HIRED", "DDHHHHIIIIRRRRREEEEDDD"

		while (i < n) {
			int c = 0;
			// count how many times current char present in B
			while(j<m && B.charAt(j)==A.charAt(i)) {
				j++;
				c++;
			}
			// if count is not greater than one return unmatched
			if(c==0) {
				return 0;
			}
			
			i++;
		}
		
		// check if A is all used in B
		if(i!=n || j!=m) {
			return 0;
		}
		
		return 1;

	}

}
