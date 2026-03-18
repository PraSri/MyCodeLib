package sort_and_search;

import java.util.Arrays;

public class MaximumNumberIntegersChooseFromRangeI {

    public static int maxCount(int[] banned, int n, int maxSum) {

        // Replace this placeholder return statement with your code
        Arrays.sort(banned);

        int count = 0;

        for(int i = 1; i<=n; i++) {
            // if i is in banned,skip
            if(binarySearch(0, banned.length-1, i, banned)) {
                continue;
            }
            maxSum -= i;
            if(maxSum<0)
                break;
            count++;
        }

        return count;
    }

    private static boolean binarySearch(int s, int e, int x, int[] a) {
        while(s<=e) {
            int mid = s + (e-s)/2;
            if(a[mid] == x) {
                return true;
            } else if(x > a[mid]) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return false;
    }

}
