package custom_data_structures;

import java.util.*;

public class RangeModule {

    private List<int[]> ranges;

    public RangeModule() {
        ranges = new ArrayList<>();
    }

    // Helper function to find the indexes of ranges that might overlap the given interval [left, right)
    private int[] checkIntervals(int left, int right) {
        int minRange = 0, maxRange = ranges.size() - 1;
        int mid = ranges.size() / 2;

        while (mid >= 1) {
            while (minRange + mid < ranges.size() && ranges.get(minRange + mid - 1)[1] < left) {
                minRange += mid;
            }
            while (maxRange - mid >= 0 && ranges.get(maxRange - mid + 1)[0] > right) {
                maxRange -= mid;
            }
            mid /= 2;
        }

        return new int[]{minRange, maxRange};
    }

    // Function to add a range
    public void addRange(int left, int right) {

        // If the list is empty or the new range does not overlap with the last range, append it
        if (ranges.isEmpty() || ranges.get(ranges.size() - 1)[1] < left) {
            ranges.add(new int[]{left, right});
            return;
        }

        // If the new range does not overlap with the first range, insert it at the beginning
        if (ranges.get(0)[0] > right) {
            ranges.add(0, new int[]{left, right});
            return;
        }

        int[] indices = checkIntervals(left, right);
        int minRange = indices[0];
        int maxRange = indices[1];

        // Merge the new range with the overlapping or touching ranges
        int updatedLeft = Math.min(ranges.get(minRange)[0], left);
        int updatedRight = Math.max(ranges.get(maxRange)[1], right);
        ranges.subList(minRange, maxRange + 1).clear();
        ranges.add(minRange, new int[]{updatedLeft, updatedRight});
    }

    // Function to search for a range
    public boolean queryRange(int left, int right) {
        if (ranges.isEmpty())
            return false;

        // Check if the range [left, right) is within the found range
        int[] indices = checkIntervals(left, right);
        int minRange = indices[0];
        return ranges.get(minRange)[0] <= left && right <= ranges.get(minRange)[1];
    }

    // Function to remove a range
    public void removeRange(int left, int right) {
        if (ranges.isEmpty() || ranges.get(0)[0] > right || ranges.get(ranges.size() - 1)[1] < left) return;

        int[] indices = checkIntervals(left, right);
        int minRange = indices[0];
        int maxRange = indices[1];

        List<int[]> updatedRanges = new ArrayList<>();
        for (int k = minRange; k <= maxRange; ++k) {

            // Add the part of the current range that is before the interval to be removed
            if (ranges.get(k)[0] < left) {
                updatedRanges.add(new int[]{ranges.get(k)[0], left});
            }

            // Add the part of the current range that is after the interval to be removed
            if (ranges.get(k)[1] > right) {
                updatedRanges.add(new int[]{right, ranges.get(k)[1]});
            }
        }

        // Replace the overlapping ranges with the new ranges
        ranges.subList(minRange, maxRange + 1).clear();
        ranges.addAll(minRange, updatedRanges);
    }

    public List<int[]> getRanges() {
        return ranges;
    }

    // Driver code
    public static void main(String[] args) {
        List<List<String>> calls = Arrays.asList(
                Arrays.asList("Add Range", "Add Range", "Remove Range", "Add Range", "Query Range", "Add Range"),
                Arrays.asList("Query Range", "Add Range", "Query Range", "Query Range", "Add Range"),
                Arrays.asList("Add Range", "Add Range", "Remove Range", "Remove Range", "Query Range", "Add Range"),
                Arrays.asList("Remove Range", "Add Range", "Remove Range"),
                Arrays.asList("Query Range", "Add Range", "Add Range", "Add Range", "Add Range", "Query Range", "Add Range", "Query Range")
        );

        List<List<int[]>> intervals = Arrays.asList(
                Arrays.asList(new int[]{2, 5}, new int[]{1, 3}, new int[]{3, 4}, new int[]{3, 7}, new int[]{6, 8}, new int[]{3, 9}),
                Arrays.asList(new int[]{1, 2}, new int[]{1, 3}, new int[]{2, 3}, new int[]{3, 4}, new int[]{3, 8}),
                Arrays.asList(new int[]{1, 5}, new int[]{7, 9}, new int[]{2, 6}, new int[]{3, 9}, new int[]{6, 8}, new int[]{4, 9}),
                Arrays.asList(new int[]{4, 9}, new int[]{5, 8}, new int[]{6, 9}),
                Arrays.asList(new int[]{12, 14}, new int[]{11, 13}, new int[]{23, 24}, new int[]{13, 27}, new int[]{16, 68}, new int[]{3, 9}, new int[]{14, 20}, new int[]{14, 19})
        );

        for (int i = 0; i < calls.size(); i++) {
            RangeModule track = new RangeModule();
            System.out.println((i + 1) + ".");

            for (int j = 0; j < calls.get(i).size(); j++) {
                if (calls.get(i).get(j).equals("Add Range")) {
                    System.out.println("\tAdd Range: [" + intervals.get(i).get(j)[0] + ", " + intervals.get(i).get(j)[1] + "]");
                    track.addRange(intervals.get(i).get(j)[0], intervals.get(i).get(j)[1]);
                    System.out.print("\tUpdated ranges: ");
                    for (int[] range : track.getRanges()) {
                        System.out.print("[" + range[0] + ", " + range[1] + "] ");
                    }
                    System.out.println();
                } else if (calls.get(i).get(j).equals("Remove Range")) {
                    System.out.println("\tRemove Range: [" + intervals.get(i).get(j)[0] + ", " + intervals.get(i).get(j)[1] + "]");
                    track.removeRange(intervals.get(i).get(j)[0], intervals.get(i).get(j)[1]);
                    System.out.print("\tUpdated ranges: ");
                    for (int[] range : track.getRanges()) {
                        System.out.print("[" + range[0] + ", " + range[1] + "] ");
                    }
                    System.out.println();
                } else if (calls.get(i).get(j).equals("Query Range")) {
                    System.out.println("\tQuery Range: [" + intervals.get(i).get(j)[0] + ", " + intervals.get(i).get(j)[1] + "]");
                    boolean found = track.queryRange(intervals.get(i).get(j)[0], intervals.get(i).get(j)[1]);
                    System.out.println("\tRange Found: " + (found ? "True" : "False"));
                }
            }

            System.out.println(String.join("", Collections.nCopies(100, "-")));
        }
    }
}

