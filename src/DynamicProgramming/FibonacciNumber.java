package DynamicProgramming;

import java.util.Scanner;

public class FibonacciNumber {

	public static void main(String[] args) {

		int n;

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		int[] a = new int[n + 1];

		a[0] = 0;
		a[1] = 1;
		a[2] = 1;
		if(n==1) {
			System.out.println(1);
			return ;
		}

		if (n > 2)

			for (int i = 3; i <= n; i++) {
				a[i] = a[i - 1] + a[i - 2];
			}

		System.out.println(a[n]);
	}

}
