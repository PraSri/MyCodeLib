package sort_and_search;

import java.util.Arrays;

public class FindDistanceValueBetweenTwoArrays {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distance = 0;
        for(int num: arr1) {
            int left = 0, right = arr2.length-1;
            boolean valid = true;
            while (left <= right) {
                int mid = left + (right-left)/2;
                if(arr2[mid] == num) {
                    valid = false;
                    break;
                }else if(arr2[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if(left < arr2.length && Math.abs(arr2[left] - num) <= d) {
                valid = false;
            }
            if(right >= 0 && Math.abs(arr2[right] - num) <= d) {
                valid = false;
            }
            if(valid) {
                distance++;
            }
        }
        return distance;
    }

}
