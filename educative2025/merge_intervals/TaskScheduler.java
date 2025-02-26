package p0s07cu;

import java.util.*;
import java.util.Map.Entry;

public class TaskScheduler {

  public static int leastTime(char[] tasks, int n) {
    // Initialize a HashMap to store the frequencies of the tasks
    Map<Character, Integer> frequencies = new HashMap<>();

    // Store the frequency of each task
    for (char t : tasks) {
      frequencies.put(t, frequencies.getOrDefault(t, 0) + 1);
    }

    // Sort the frequencies
    List<Map.Entry<Character, Integer>> sortedFrequencies = new ArrayList<>(frequencies.entrySet());
    sortedFrequencies.sort(Entry.comparingByValue());

    // Store the max frequency
    int maxFreq = sortedFrequencies.get(sortedFrequencies.size() - 1).getValue();
    sortedFrequencies.remove(sortedFrequencies.size() - 1);

    // Compute the maximum possible idle time
    int idleTime = (maxFreq - 1) * n;

    // Iterate over the frequencies array and update the idle time
    while (!sortedFrequencies.isEmpty() && idleTime > 0) {
      idleTime -=
          Math.min(maxFreq - 1, sortedFrequencies.get(sortedFrequencies.size() - 1).getValue());
      sortedFrequencies.remove(sortedFrequencies.size() - 1);
    }
    idleTime = Math.max(0, idleTime);

    // Return the total time, which is the sum of the busy time and idle time
    return tasks.length + idleTime;
  }

  // Driver code
  public static void main(String[] args) {
    char[][] allTasks = {
        {'A', 'A', 'B', 'B'},
        {'A', 'A', 'A', 'B', 'B', 'C', 'C'},
        {'S', 'I', 'V', 'U', 'W', 'D', 'U', 'X'},
        {'M', 'A', 'B', 'M', 'A', 'A', 'Y', 'B', 'M'},
        {'A', 'K', 'X', 'M', 'W', 'D', 'X', 'B', 'D', 'C', 'O', 'Z', 'D', 'E', 'Q'}};

    int[] allNs = {2, 1, 0, 3, 3};

    for (int i = 0; i < allTasks.length; i++) {
      System.out.print((i + 1) + ".\tTasks: ");
      char[] tasks = allTasks[i];
      for (int j = 0; j < tasks.length; j++) {
        System.out.print(tasks[j]);
        if (j != tasks.length - 1) {
          System.out.print(", ");
        }
      }
      System.out.println("\n\tn: " + allNs[i]);

      int minTime = leastTime(allTasks[i], allNs[i]);
      System.out.println("\tMinimum time required to execute the tasks: " + minTime);
      System.out.println('-' + String.join("", Collections.nCopies(100, "-")) + '\n');
    }
  }
}
