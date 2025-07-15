package union_find;

import java.util.*;

public class TheSkylineProblem {
    public static class UnionFind {
        private final int[] root;

        public UnionFind(int N) {
            root = new int[N];
            for (int i = 0; i < N; i++) {
                root[i] = i;
            }
        }

        public int find(int x) {
            if (root[x] != x) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            root[find(x)] = find(y);
        }
    }


    public static int[][] getSkyline(int[][] buildings) {
        // Extract all unique x-coordinates (both left and right edges) and sort them.
        TreeSet<Integer> coordsSet = new TreeSet<>();
        for (int[] building : buildings) {
            coordsSet.add(building[0]);
            coordsSet.add(building[1]);
        }
        List<Integer> coordinates = new ArrayList<>(coordsSet);
        int n = coordinates.size();

        // Initialize the heights array (to store the height at each x-coordinate).
        int[] heights = new int[n];

        // Create a map to quickly look up the index of a coordinate in the sorted list.
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(coordinates.get(i), i);
        }

        // Sort buildings by their height in descrighting order, so we process taller buildings first.
        Arrays.sort(buildings, (a, b) -> b[2] - a[2]);

        UnionFind uf = new UnionFind(n);
        List<int[]> skyline = new ArrayList<>();

        // Process each building.
        for (int[] building : buildings) {
            // Convert the left and right x-coordinates of the building to their respective indexes in the sorted list.
            int leftX = building[0], rightX = building[1], height = building[2];
            int left = indexMap.get(leftX), right = indexMap.get(rightX);

            // Update the height for all x-coordinates between left and right.
            while (left < right) {
                // Find the current root of left.
                left = uf.find(left);

                // If left is still less than right, it means this segment hasn't been fully merged.
                if (left < right) {
                    // Merge the left index with the right index (connect these two parts of the skyline).
                    uf.union(left, right);
                    // Update the height at the current left index to the current building's height.
                    heights[left] = height;
                    // Move to the next index.
                    left++;
                }
            }
        }

        // Build the final skyline by looping through the heights.
        for (int i = 0; i < n; i++) {
            // nly add points to the skyline when the height changes from the previous point.
            if (i == 0 || heights[i] != heights[i - 1]) {
                skyline.add(new int[]{coordinates.get(i), heights[i]});
            }
        }

        return skyline.toArray(new int[0][]);
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] buildings = {
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                {{1, 7, 9}, {3, 4, 11}, {5, 9, 10}, {12, 16, 19}},
                {{1, 2, 1}, {2, 4, 6}, {8, 13, 18}},
                {{1, 4, 7}},
                {{2, 13, 5}, {5, 8, 11}, {8, 10, 1}, {10, 11, 12}}
        };

        for (int i = 0; i < buildings.length; i++) {
            System.out.print((i + 1) + ".\tBuildings: [");
            for (int j = 0; j < buildings[i].length; j++) {
                System.out.print(Arrays.toString(buildings[i][j]));
                if (j < buildings[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            System.out.print("\n\n\tSkyline: [");

            int[][] skyline = getSkyline(buildings[i]);
            for (int j = 0; j < skyline.length; j++) {
                System.out.print(Arrays.toString(skyline[j]));
                if (j < skyline.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            System.out.println();
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
