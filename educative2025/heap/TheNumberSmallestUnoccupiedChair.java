import java.util.*;

public class TheNumberSmallestUnoccupiedChair {
    public static int smallestChair(int[][] times, int targetFriend) {
        // Step 1: Sort friends by arrival time
        // We enumerate times to keep track of original indices (friend IDs)
        List<int[]> sortedFriends = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            sortedFriends.add(new int[]{i, times[i][0], times[i][1]});
        }
        sortedFriends.sort(Comparator.comparingInt(a -> a[1]));

        // Step 2: Initialize Min-Heaps
        PriorityQueue<Integer> availableChairs = new PriorityQueue<>(); // Stores available chair numbers (min-heap)
        PriorityQueue<int[]> occupiedChairs = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // Stores (leaving_time, chair_number) (min-heap)

        // Tracks the next unused chair number
        int chairIndex = 0;

        // Step 3: Process each friend in order of arrival time
        for (int[] friendData : sortedFriends) {
            int friendId = friendData[0];
            int arrival = friendData[1];
            int leaving = friendData[2];

            // Step 4: Free up chairs that should be released before the current friend's arrival
            while (!occupiedChairs.isEmpty() && occupiedChairs.peek()[0] <= arrival) {
                int freedChair = occupiedChairs.poll()[1];
                availableChairs.offer(freedChair); // Add it to available chairs
            }

            // Step 5: Assign the smallest available chair
            int assignedChair;
            if (!availableChairs.isEmpty()) {
                assignedChair = availableChairs.poll();
            } else {
                assignedChair = chairIndex; // Use a new chair if none are available
                chairIndex++; // Move to the next available chair number
            }

            // Step 6: Store the chair assignment with leaving time
            occupiedChairs.offer(new int[]{leaving, assignedChair});

            // Step 7: Check if this is the target friend
            if (friendId == targetFriend) {
                return assignedChair; // Return the chair number assigned to the target friend
            }
        }
        return -1;
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] testCases = {
            {{3, 6}, {1, 6}, {4, 5}, {2, 4}, {5, 7}},
            {{3, 5}, {2, 6}, {1, 7}},
            {{5, 10}, {2, 3}, {3, 8}, {1, 6}},
            {{1, 2}, {2, 3}, {3, 4}, {4, 5}},
            {{1, 10}, {2, 3}, {3, 4}, {4, 5}, {5, 6}}
        };
        int[] targetFriends = {4, 0, 3, 2, 4};

        for (int i = 0; i < testCases.length; i++) {
            int result = smallestChair(testCases[i], targetFriends[i]);
            System.out.println((i + 1) + ".\t Times: " + Arrays.deepToString(testCases[i]) + 
                "\n\t Target friend: " + targetFriends[i] + "\n\n\t Chair number: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
