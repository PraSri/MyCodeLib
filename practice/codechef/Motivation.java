package codechef;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Motivation {

	public static void main(String[] args) {

		int ans = getMovie(2, 2, new int[] { 1, 50 }, new int[] { 2, 100 });
		System.out.println(ans);

//		Scanner sc = new Scanner(System.in);
//
//		int t = sc.nextInt();
//		while (t > 0) {
//			int n = sc.nextInt();
//			int x = sc.nextInt();
//			int[] s = new int[n];
//			int[] r = new int[n];
//			for (int i = 0; i < n; i++) {
//				s[i] = sc.nextInt();
//				r[i] = sc.nextInt();
//			}
//			int ans = getMovie(n, x, s, r);
//			System.out.println(ans);
//			t--;
//		}

	}

	public static class Pair {
		public int s, r;

		public Pair(int s, int r) {
			this.s = s;
			this.r = r;
		}

		@Override
		public String toString() {
			return s + "-" + r;
		}

	}

	public static int getMovie(int n, int x, int[] s, int[] r) {

		List<Pair> l = new ArrayList<Pair>();
		for (int i = 0; i < n; i++) {
			Pair p = new Pair(s[i], r[i]);
			l.add(p);
		}

		Collections.sort(l, new Comparator<Pair>() {

			@Override
			public int compare(Pair p1, Pair p2) {
				return p2.r - p1.r;
			}
		});

		for (int i = 0; i < n; i++) {
			if (l.get(i).s <= x) {
				return l.get(i).r;
			}
		}

//		System.out.println(l);

		return 0;

	}

}
