package GoldmanSachs;

public class FindSecondMinInArray {

	public static int getSecondMin(int[] a) {

		int n = a.length;
		int mn = Integer.MAX_VALUE; // curr min
		int mn1 = Integer.MAX_VALUE; // prev min

		for (int i = 0; i < n; i++) {
			if (a[i] < mn) { // if u find any number lesser than curr min
				mn1 = mn; // then hand over ur curr min to prev min
				mn = a[i]; // assign curr min with this number
			}
		}

		for (int i = 0; i < n; i++) {
			if (a[i] < mn1 && a[i] != mn) { // if u have prev min as ur min
				// which is not equal to curr min than its 2nd min in array
				mn1 = a[i];
			}
		}

		return mn1;
	}
}
