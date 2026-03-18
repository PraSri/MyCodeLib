package Rippling;

import java.util.*;

public class DesignExcelSheetFormula {

    static class Excel {
        private int H, W;
        private Cell[][] cells;

        // Define the cell structure.
        private static class Cell {
            int val;
            // If formula is not null, it maps a dependency (encoded as "row,col") to its count.
            Map<String, Integer> formula;
            // Map of dependent cells: key is "row,col" and value is the count.
            Map<String, Integer> children;

            Cell() {
                val = 0;
                formula = null;
                children = new HashMap<>();
            }
        }

        public Excel(int H, String W) {
            this.H = H;
            this.W = W.charAt(0) - 'A' + 1;
            cells = new Cell[H][this.W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < this.W; j++) {
                    cells[i][j] = new Cell();
                }
            }
        }

        // Helper to encode cell coordinates as a string "i,j"
        private String encode(int i, int j) {
            return i + "," + j;
        }

        private void addChild(int depI, int depJ, int childI, int childJ, int count) {
            String key = encode(childI, childJ);
            cells[depI][depJ].children.put(key, cells[depI][depJ].children.getOrDefault(key, 0) + count);
        }

        private void removeChild(int depI, int depJ, int childI, int childJ) {
            String key = encode(childI, childJ);
            cells[depI][depJ].children.remove(key);
        }

        private void propagate(int i, int j, int delta) {
            // Propagate the change (delta) to all cells that depend on the given cell.
            for (Map.Entry<String, Integer> entry : cells[i][j].children.entrySet()) {
                String childKey = entry.getKey();
                int count = entry.getValue();
                // Decode the child's coordinates.
                String[] parts = childKey.split(",");
                int childI = Integer.parseInt(parts[0]);
                int childJ = Integer.parseInt(parts[1]);
                cells[childI][childJ].val += delta * count;
                propagate(childI, childJ, delta * count);
            }
        }

        public void set(int r, String c, int v) {
            int i = r - 1;
            int j = c.charAt(0) - 'A';
            // If this cell has a formula, remove dependency links.
            if (cells[i][j].formula != null) {
                for (String dep : cells[i][j].formula.keySet()) {
                    String[] parts = dep.split(",");
                    int depI = Integer.parseInt(parts[0]);
                    int depJ = Integer.parseInt(parts[1]);
                    removeChild(depI, depJ, i, j);
                }
                cells[i][j].formula = null;
            }
            int oldVal = cells[i][j].val;
            cells[i][j].val = v;
            propagate(i, j, v - oldVal);
        }

        public int sum(int r, String c, String[] strs) {
            int i = r - 1;
            int j = c.charAt(0) - 'A';
            // Remove old formula dependency if exists.
            if (cells[i][j].formula != null) {
                for (String dep : cells[i][j].formula.keySet()) {
                    String[] parts = dep.split(",");
                    int depI = Integer.parseInt(parts[0]);
                    int depJ = Integer.parseInt(parts[1]);
                    removeChild(depI, depJ, i, j);
                }
            }
            Map<String, Integer> formula = new HashMap<>();
            // Build the new formula.
            for (String s : strs) {
                if (s.contains(":")) {//A1:B2
                    String[] parts = s.split(":");
                    String start = parts[0]; // A1
                    String end = parts[1]; // B2
                    int startI = Integer.parseInt(start.substring(1)) - 1;
                    int startJ = start.charAt(0) - 'A';
                    int endI = Integer.parseInt(end.substring(1)) - 1;
                    int endJ = end.charAt(0) - 'A';
                    for (int x = startI; x <= endI; x++) {
                        for (int y = startJ; y <= endJ; y++) {
                            String key = encode(x, y);
                            formula.put(key, formula.getOrDefault(key, 0) + 1);
                        }
                    }
                } else {
                    int x = Integer.parseInt(s.substring(1)) - 1;
                    int y = s.charAt(0) - 'A';
                    String key = encode(x, y);
                    formula.put(key, formula.getOrDefault(key, 0) + 1);
                }
            }
            // Save the new formula in the cell.
            cells[i][j].formula = formula;
            int total = 0;
            // For each dependency, add this cell as a child and compute the total.
            for (Map.Entry<String, Integer> entry : formula.entrySet()) {
                String key = entry.getKey();
                int count = entry.getValue();
                String[] parts = key.split(",");
                int depI = Integer.parseInt(parts[0]);
                int depJ = Integer.parseInt(parts[1]);
                total += cells[depI][depJ].val * count;
                addChild(depI, depJ, i, j, count);
            }
            int oldVal = cells[i][j].val;
            cells[i][j].val = total;
            propagate(i, j, total - oldVal);
            return total;
        }

        public int get(int r, String c) {
            int i = r - 1;
            int j = c.charAt(0) - 'A';
            return cells[i][j].val;
        }
    }

    public static class Solution {
        public static void main(String[] args) {
            Excel excel = new Excel(3, "C");
            excel.set(1, "A", 2);
            System.out.println("sum(3, 'C', [\"A1\", \"A1:B2\"]) = " +
                    excel.sum(3, "C", new String[]{"A1", "A1:B2"})); // Expected: 8
            excel.set(2, "B", 2);
            System.out.println("get(3, 'C') = " + excel.get(3, "C")); // Expected: 6
        }
    }
}
