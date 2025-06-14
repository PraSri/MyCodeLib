package searchingAlgorithms;

import java.util.*;

public class AllocateBooks {

    public static void main(String[] args) {
    }

    public static int books(int[] A, int B) {
        int n = A.length;
        int sum = 0;
        for (int x : A)
            sum += x;
        int s = 0;
        int e = sum;
        int res = Integer.MAX_VALUE;
        while (s < e) {
            int mid = (s + e) / 2;
            if (isPossible(A, n, B, mid)) {
                res = Math.min(mid, res);
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static boolean isPossible(int[] a, int n, int b, int cur_min) {
        int cur_sum = 0;
        int student_count = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] > cur_min) return false;
            if (cur_sum + a[i] > cur_min) {
                student_count++;
                cur_sum = a[i];
                if (student_count > b) {
                    return false;
                }
            } else {
                cur_sum += a[i];
            }
        }
        return true;
    }

    public static class Main {
        public static int countStudents(ArrayList<Integer> arr, int pages) {
            int n = arr.size(); // size of array
            int students = 1;
            long pagesStudent = 0;
            for (int i = 0; i < n; i++) {
                if (pagesStudent + arr.get(i) <= pages) {
                    // add pages to current student
                    pagesStudent += arr.get(i);
                } else {
                    // add pages to next student
                    students++;
                    pagesStudent = arr.get(i);
                }
            }
            return students;
        }

        public static int findPages(ArrayList<Integer> arr, int n, int m) {
            // book allocation impossible
            if (m > n) return -1;

            int low = Collections.max(arr);
            int high = arr.stream().mapToInt(Integer::intValue).sum();
            while (low <= high) {
                int mid = (low + high) / 2;
                int students = countStudents(arr, mid);
                if (students > m) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return low;
        }

        public static void main(String[] args) {
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(25, 46, 28, 49, 24));
            int n = 5;
            int m = 4;
            int ans = findPages(arr, n, m);
            System.out.println("The answer is: " + ans);
        }
    }


}
