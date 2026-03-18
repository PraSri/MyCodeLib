package cyclic_sort;

import java.util.*;

public class FindFirstKMissingNumbers {
    public static List<Integer> firstKMissingNumbers(int[] arr, int k) {

        // Replace this placeholder return statement with your code

        int n = arr.length;
        int i = 0;
        while (i < n) {
            int correctPos = arr[i] - 1;
            if (correctPos >= 0 && correctPos < n && arr[i] != arr[correctPos]) {
                int temp = arr[i];
                arr[i] = arr[correctPos];
                arr[correctPos] = temp;
            } else {
                i++;
            }
        }

        List<Integer> missing = new ArrayList<>();

        Set<Integer> otherNumber = new HashSet<>();

        for (i = 0; i < arr.length & missing.size() < k; i++) {
            if (arr[i] != i + 1) {
                missing.add(i + 1);
                otherNumber.add(arr[i]);
            }
        }

        for (int j = i; missing.size() < k; j++) {

            if (!otherNumber.contains(j + 1)) {
                missing.add(j + 1);
            }
        }

        return missing;
    }

    public int findKthPositive(int[] arr, int k) {
        // Replace this placeholder return statement with your code

        int n = arr.length;
        int i = 0;
        while (i < n) {
            int correctPos = arr[i] - 1;
            if (correctPos >= 0 && correctPos < n && arr[i] != arr[correctPos]) {
                int temp = arr[i];
                arr[i] = arr[correctPos];
                arr[correctPos] = temp;
            } else {
                i++;
            }
        }

        List<Integer> missing = new ArrayList<>();

        Set<Integer> otherNumber = new HashSet<>();

        for (i = 0; i < arr.length & missing.size() < k; i++) {
            if (arr[i] != i + 1) {
                missing.add(i + 1);
                otherNumber.add(arr[i]);
            }
        }

        for (int j = i; missing.size() < k; j++) {

            if (!otherNumber.contains(j + 1)) {
                missing.add(j + 1);
            }
        }

        return missing.get(k - 1);
    }

    // log n solution

    public int findKthPositiveLogN(int[] A, int k) {
        int l = 0, r = A.length, m;
        while (l < r) {
            m = (l + r) / 2;
            if (A[m] - 1 - m < k)
                l = m + 1;
            else
                r = m;
        }
        return l + k;
    }

    public static void main(String[] args) {
        System.out.println(firstKMissingNumbers(new int[]{1, 2, 3, 4, 5}, 6));
    }
}
