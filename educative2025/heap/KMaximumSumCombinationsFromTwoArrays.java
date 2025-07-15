package heap;

import java.util.*;

public class KMaximumSumCombinationsFromTwoArrays {

    /**
     * You are given two integer arrays, arr1 and arr2, each of size n
     * .You are also given an integer k. Your task is to return the k
     * largest sum combinations that can be formed by adding one element from
     * arr1 and one element from arr2, for all possible pairs (arr1[i] + arr2[j]), where
     * 0?i,j<n
     * .
     * TC :  O(nlog?n)+O(klog?k)
     * SC: O(k)
     */

    public List<Integer> maxCombinations(List<Integer> arr1, List<Integer> arr2, int k) {
        // sort both in descending order
        arr1.sort((a, b) -> b - a);
        arr2.sort((a, b) -> b - a);

        List<int[]> pq = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        pq.add(new int[]{arr1.get(0) + arr2.get(0), 0, 0});
        visited.add("0,0");

        List<Integer> result = new ArrayList<>();

        for (int count = 0; count < k; count++) {
            int[] top = pq.remove(0);
            int sum = top[0];
            int i = top[1];
            int j = top[2];
            result.add(sum);
            if (i + 1 < arr1.size() && !visited.contains((i + 1) + "," + j)) {
                pq.add(new int[]{arr1.get(i + 1) + arr2.get(j), i + 1, j});
                visited.add((i + 1) + "," + j);
            }
            if (j + 1 < arr2.size() && !visited.contains(i + "," + (j + 1))) {
                pq.add(new int[]{arr1.get(i) + arr2.get(j + 1), i, j + 1});
                visited.add(i + "," + j + 1);
            }
            pq.sort((a, b) -> Integer.compare(b[0], a[0]));
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> arr1List = new ArrayList<>();
        arr1List.add(Arrays.asList(1, 4, 2));
        arr1List.add(Arrays.asList(10, 15, 30));
        arr1List.add(Arrays.asList(1, 1, 1));
        arr1List.add(Arrays.asList(5, 7));
        arr1List.add(Arrays.asList(1, 2, 3));

        List<List<Integer>> arr2List = new ArrayList<>();
        arr2List.add(Arrays.asList(3, 6, 5));
        arr2List.add(Arrays.asList(20, 25, 10));
        arr2List.add(Arrays.asList(1, 1, 1));
        arr2List.add(Arrays.asList(8, 3));
        arr2List.add(Arrays.asList(4, 5, 6));

        List<Integer> kList = Arrays.asList(3, 2, 2, 1, 3);

        for (int i = 0; i < arr1List.size(); i++) {
            List<Integer> arr1 = arr1List.get(i);
            List<Integer> arr2 = arr2List.get(i);
            int k = kList.get(i);

            System.out.println((i + 1) + ".\t arr1: " + arr1 + ", arr2: " + arr2 + ", k: " + k);

            KMaximumSumCombinationsFromTwoArrays obj = new KMaximumSumCombinationsFromTwoArrays();
            List<Integer> result = obj.maxCombinations(arr1, arr2, k);

            System.out.println("\n\t Top " + k + " Maximum Sums: " + result);
            System.out.println("-".repeat(100));
        }
    }
}
