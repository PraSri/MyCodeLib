import java.util.*;

public class MinimumMachines {

  /**
   * We are given an input array, tasks, where tasks[i] = [ s t a r t i , e n d i ]
   * represents the start and end times of n tasks. 
   * Our goal is to schedule these tasks on machines given the following criteria: 
   * A machine can execute only one task at a time. 
   * A machine can begin executing a new task immediately after completing the previous one. 
   * An unlimited number of machines are available. Find the minimum number of machines 
   * required to complete these n tasks
   * */

    public static int minimumMachines(int[][] tasks) {
        // Sort tasks by start time
        Arrays.sort(tasks, Comparator.comparingInt(a -> a[0]));

        // Min-heap to track machines by end time
        PriorityQueue<Integer> machines = new PriorityQueue<>();

        // Iterate over each task in the sorted tasks array
        for (int[] task : tasks) {
            // Extract the start and end times of the current task
            int start = task[0];
            int end = task[1];

            // Check if the machine with the earliest finish time is free
            if (!machines.isEmpty() && machines.peek() <= start)
                // Reuse machine
                machines.poll();

            // Assign a machine to the current task
            machines.add(end);
        }

        // Return the size of the heap representing the minimum number of machines required
        return machines.size();
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] inputTasksList = {
            {{1, 1}, {5, 5}, {8, 8}, {4, 4}, {6, 6}, {10, 10}, {7, 7}},
            {{1, 7}, {1, 7}, {1, 7}, {1, 7}, {1, 7}, {1, 7}},
            {{1, 7}, {8, 13}, {5, 6}, {10, 14}, {6, 7}},
            {{1, 3}, {3, 5}, {5, 9}, {9, 12}, {12, 13}, {13, 16}, {16, 17}},
            {{12, 13}, {13, 15}, {17, 20}, {13, 14}, {19, 21}, {18, 20}}
        };

        for (int i = 0; i < inputTasksList.length; ++i) {
            System.out.print((i + 1) + ".\t Tasks: [");
            int[][] tasks = inputTasksList[i];
            for (int j = 0; j < tasks.length; ++j) {
                System.out.print("[" + tasks[j][0] + ", " + tasks[j][1] + "]");
                if (j < tasks.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println("\t Minimum number of machines: " + minimumMachines(tasks));
            System.out.println(String.join("", Collections.nCopies(100, "-")));
        }
    }
}
