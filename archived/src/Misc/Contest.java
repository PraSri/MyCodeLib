package Misc;

public class Contest {
	
	public static void main(String[] args) {
		Double d = 0.0;
		int i = 3;
		int y = 5;
		Double f = (double) (i/y);
		float t = 3/5;
		System.out.println(t);
		System.out.println(f);
	}

	public static int solve(int A, int B) {

		long a = 1;// f1
		long b = 1;// f2
		long sum = 0;
		int i = 3;
		int ct = 0;
		while (i <= B) {
			sum = a%10 + b%10; // f3
			
			System.out.println(i + " - " + sum + " ");
			a = b;
			b = sum;
			if (i >= A) {
				String s = String.valueOf(sum);
				String sb = s.substring(s.length() - 1, s.length());
				int x = Integer.parseInt(sb);
				System.out.println(i + " - " + " x = " + x);
				if(x>10)
				x = x % 10;
				if (x == 2 || x == 3 || x == 5 || x == 7) {
					ct++;
				}
			}
			i++;
		}
		return ct;
	}

}

