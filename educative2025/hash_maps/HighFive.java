package hash_maps;

import java.util.*;

public class HighFive {

    // Function to calculate the top five averages for each student
    public static int[][] highFive(int[][] items) {
        // Use a HashMap to store scores for each student ID
        Map<Integer, List<Integer>> scoresMap = new HashMap<>();
        int maxId = 0;

        // Populate the scores map and find the maximum student ID
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            scoresMap.putIfAbsent(id, new ArrayList<>());
            scoresMap.get(id).add(score);
            maxId = Math.max(maxId, id);
        }

        // List to store the result dynamically
        List<int[]> resultList = new ArrayList<>();

        // Iterate through all possible IDs from 1 to maxId (inclusive)
        for (int i = 1; i <= maxId; ++i) {
            if (scoresMap.containsKey(i)) {
                // Use a priority queue (max-heap) to find the top 5 scores
                PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                pq.addAll(scoresMap.get(i));

                int sum = 0;
                for (int j = 0; j < 5 && !pq.isEmpty(); ++j) {
                    sum += pq.poll();
                }
                int average = sum / 5;
                resultList.add(new int[]{i, average});
            }
        }

        // Convert the result list to an int[][] array
        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] testCases = {
                {{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}},
                {{1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100}},
                {{3, 70}, {4, 60}, {3, 80}, {4, 90}, {3, 100}, {4, 85}, {3, 90}, {4, 100}, {3, 75}, {4, 95}},
                {{5, 60}, {6, 50}, {5, 90}, {6, 70}, {5, 80}, {6, 60}, {5, 70}, {6, 80}, {5, 100}, {6, 100}},
                {{8, 88}, {8, 77}, {9, 85}, {8, 95}, {9, 100}, {8, 100}, {9, 95}, {9, 80}, {8, 92}, {9, 88}}
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.print((i + 1) + ".\titems: ");
            for (int[] item : testCases[i]) {
                System.out.print("[" + item[0] + ", " + item[1] + "] ");
            }
            System.out.print("\n\tThe top five averages are: ");
            int[][] result = highFive(testCases[i]);
            for (int[] res : result) {
                System.out.print("[" + res[0] + ", " + res[1] + "] ");
            }
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

}
