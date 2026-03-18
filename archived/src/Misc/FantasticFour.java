package Misc;

import java.util.HashMap;
import java.util.Map;

public class FantasticFour {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println(4 - (2 % 4));
		System.out.println(solve(new int[] { 1, 3, 4, 4, 2, 2 }));
//		System.out.println(solve(new int[] { 4, 2, 2 }));
//		System.out.println(solve(new int[] { 1,2,3,1,2,3,8 }));
		System.out.println(solve(new int[] {1,1,3,2 }));
		System.out.println(solve(new int[] { 3,3,3,3}));

	}

	public static int solve(int[] A) {

		int n = A.length;
		int sum = 0;
		int[] mod = new int[4];
		for (int i : A) {
			int m = i%4;
			mod[m]++;
			sum = sum + i%4;
		}
		if(sum%4!=0) {
			return -1;
		}
		int ct = 0;
		if(mod[1]>mod[3]) {
			ct+=mod[3];
		}else {
			ct+=mod[1];
		}
		mod[1]-=ct;
		mod[3]-=ct;
		mod[2]+=mod[1]/2;
		mod[2]+=mod[3]/2;
		
		ct+=mod[1]/2;
		ct+=mod[3]/2;
		ct+=mod[2]/2;

		return ct;
	}

}
