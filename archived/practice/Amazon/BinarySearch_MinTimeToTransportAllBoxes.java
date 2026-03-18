package Amazon;

import java.util.Arrays;

public class BinarySearch_MinTimeToTransportAllBoxes {

	public static void main(String[] args) {

		int[] box = new int[] { 10, 2, 16, 19 };
		int[] truck = new int[] { 29, 25 };
		int ans = BinarySearch_MinTimeToTransportAllBoxes.minTimeToTransport(box, truck);
		System.out.println(ans);

	}

	/***
	 * Given two arrays box[] and truck[] where box[i] represents the weight of the
	 * ith box and truck[i] represents the maximum load that the ith truck can
	 * carry. Now each truck takes 1 hour to transport a box from source to
	 * destination and another one hour to come back. Now given that all the boxes
	 * are kept at the source, the task is to find the minimum time required to
	 * transport all the boxes from source to the destination. Note that there will
	 * always be some time in which the boxes can be transported and only a single
	 * box can be carried by a truck at any instance of time.
	 ***************/

	/***
	 * 
	 * Input: box[] = {7, 6, 5, 4, 3}, truck[] = {10, 3} Output: 7 1st hour:
	 * truck[0] carries box[0] and truck[1] carries box[4] 2nd hour: Both truck are
	 * back on the source location. Now, truck[1] cannot carry anymore boxes as all
	 * the remaining boxes have weights more than the capacity of truck[1]. So,
	 * truck[0] will carry box[1] and box[2] in total of four hours.
	 * (source-destination and then destination-source) And finally box[3] will take
	 * another hour to reach the destination. So, total time taken = 2 + 4 + 1 = 7
	 * 
	 *************/

	public static int minTimeToTransport(int[] box, int[] truck) {
		int n = box.length;
		int m = truck.length;
		Arrays.sort(box);
		Arrays.sort(truck);
		// in worst case time can goto 2 times of number of boxes
		int s = 0, e = 2 * n;
		int minTime = 0;

		while (s <= e) {
			int mid = s + (e - s) / 2;
			// is it possible to deliver in time mid
			if (isPossible(box, truck, n, m, mid)) {
				// if yes
				minTime = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return minTime;
	}

	// 3,4,5,6,7
	// 3,10
	private static boolean isPossible(int[] box, int[] truck, int n, int m, int mid) {

		int tc = 0;//truck count
		int bc = 0;// box count
		while (tc < m) {
			int time = 0;
			while (time < mid) {
				if (bc >= n) {
					// as box count reach total box return true and this mid time is possible ans
					return true;
				}
				if (truck[tc] >= box[bc]) {
					bc++;
				}
				time += 2;
			}

			tc++;
		}
		if (bc == n)
			return true;

		return false;
	}

}
