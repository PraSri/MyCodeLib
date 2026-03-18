import java.util.*;

public class Coupang {

    public List<Integer> mergeKListsDivide(List<List<Integer>> lists) {
        if (lists == null || lists.size() == 0) return new ArrayList<>();
        return merge(lists, 0, lists.size() - 1);
    }

    // Distance between closest pair of islands

    private List<Integer> merge(List<List<Integer>> lists, int l, int r) {
        if (l == r) return lists.get(l);

        int mid = l + (r - l) / 2;

        List<Integer> left = merge(lists, l, mid);
        List<Integer> right = merge(lists, mid + 1, r);

        return mergeTwo(left, right);
    }

    //java optimized solution for Merge K sorted list of integers.

    private List<Integer> mergeTwo(List<Integer> a, List<Integer> b) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i) < b.get(j)) res.add(a.get(i++));
            else res.add(b.get(j++));
        }

        while (i < a.size()) res.add(a.get(i++));
        while (j < b.size()) res.add(b.get(j++));

        return res;
    }

    /**
     * solve this in java - Given two strings, str, pattern, find # of discontinous matches of pattern in str. None of the matched letters can be next to each other in str. examples: pattern="cat", str="catapult", result=1, explantation: CatApulT pattern="cat", str="catatapult", result=2, explantation: CatcAtapulT, CatcatApulT, catCatApulT
     *
     */

    public class DiscontinuousMatchRecursive {

        public static int countMatches(String str, String pattern) {
            int n = str.length();
            int m = pattern.length();

            Integer[][] dp = new Integer[n][m];

            return dfs(0, 0, str, pattern, dp);
        }

        private static int dfs(int i, int j, String str, String pattern, Integer[][] dp) {

            // Base cases
            if (j == pattern.length()) return 1;
            if (i >= str.length()) return 0;

            if (dp[i][j] != null) return dp[i][j];

            int ans = 0;

            // 1. Skip current char
            ans += dfs(i + 1, j, str, pattern, dp);

            // 2. Match current char
            if (str.charAt(i) == pattern.charAt(j)) {
                ans += dfs(i + 2, j + 1, str, pattern, dp);
            }

            return dp[i][j] = ans;
        }

        public static void main(String[] args) {
            System.out.println(countMatches("catapult", "cat"));     // 1
            System.out.println(countMatches("catatapult", "cat"));   // 2
        }
    }

    /**
     * Given a grid[][] containing 0s and 1s, where '0' represents water and '1' represents the land. Given that an island is a group of land (1s) surrounded by water (0s) on all sides.
     * <p>
     * The task is to find the distance between the two closest islands such that:
     * <p>
     * Distance between two islands is the minimum number of '0' between two islands.
     * Only 4 - directional movement is allowed.
     * There are at least 2 islands present in the grid.
     *
     *
     */

    // Java code to implement the approach


    class GFG {
        static int row;
        static int col;
        static int[] dirx = {0, 1, 0, -1};
        static int[] diry = {1, 0, -1, 0};

        // Function to find closest distance
        static void closestDistance(int[][] grid) {
            row = grid.length;
            col = grid[0].length;

            int id = 1;
            Queue<Pair> q = new ArrayDeque<Pair>();
            int[][] visited = new int[row][col];

            // Distance array to store distance
            // From nearest island
            int[][] distance = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1
                            && visited[i][j] == 0) {
                        dfs(grid, visited, q, i, j, id);
                        id++;
                    }
                }
            }

            // To store minimal distance
            // b/w closest islands
            int ans = bfs(grid, visited, distance, q);
            System.out.println(ans);
        }

        // Dfs function to add all island elements
        // In queue and marking them visited with id
        static void dfs(int[][] grid, int[][] visited,
                        Queue<Pair> q, int i,
                        int j, int id) {
            visited[i][j] = id;
            q.add(new Pair(i, j, id));
            for (int idx = 0; idx < 4; idx++) {
                int x = i + dirx[idx];
                int y = j + diry[idx];
                if (isValid(x, y) && grid[x][y] == 1
                        && visited[x][y] == 0) {
                    dfs(grid, visited, q, x, y, id);
                }
            }
        }

        // Bfs function to expand every island and
        // Maintaining distance array
        static int bfs(int[][] grid, int[][] visited,
                       int[][] distance, Queue<Pair> q) {
            while (q.size() != 0) {
                Pair p = q.remove();
                for (int i = 0; i < 4; i++) {
                    int x = p.x + dirx[i];
                    int y = p.y + diry[i];
                    if (isValid(x, y)
                            && visited[x][y] == 0) {
                        q.add(new Pair(x, y,
                                p.identity));
                        distance[x][y]
                                = distance[p.x][p.y] + 1;
                        visited[x][y]
                                = p.identity;
                    } else if (isValid(x, y)
                            && visited[x][y] != 0
                            && visited[x][y]
                            != visited[p.x][p.y]) {
                        return distance[x][y]
                                + distance[p.x][p.y];
                    }
                }
            }
            return -1;
        }

        // Function to check if point
        // Is inside grid
        static boolean isValid(int x, int y) {
            if (x < 0 || x >= row
                    || y < 0 || y >= col)
                return false;
            return true;
        }

        // Driver Code
        public static void main(String[] args) {
            int[][] grid = {{1, 0, 0, 0, 1},
                    {1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1}};
            closestDistance(grid);
        }

        // A class to represent coordinates of
        // element in matrix
        static class Pair {
            int x;
            int y;
            int identity;

            Pair(int x, int y, int identity) {
                this.x = x;
                this.y = y;
                this.identity = identity;
            }
        }
    }

    public class MergeKSortedLists {

        public static List<Integer> mergeKLists(List<List<Integer>> lists) {
            List<Integer> result = new ArrayList<>();

            PriorityQueue<Node> minHeap = new PriorityQueue<>(
                    (a, b) -> a.value - b.value
            );

            // Step 1: add first element of each list
            for (int i = 0; i < lists.size(); i++) {
                if (!lists.get(i).isEmpty()) {
                    minHeap.offer(new Node(lists.get(i).get(0), i, 0));
                }
            }

            // Step 2: process heap
            while (!minHeap.isEmpty()) {
                Node current = minHeap.poll();
                result.add(current.value);

                int nextIndex = current.elementIndex + 1;

                if (nextIndex < lists.get(current.listIndex).size()) {
                    minHeap.offer(new Node(
                            lists.get(current.listIndex).get(nextIndex),
                            current.listIndex,
                            nextIndex
                    ));
                }
            }

            return result;
        }

        public static void main(String[] args) {
            List<List<Integer>> lists = Arrays.asList(
                    Arrays.asList(1, 4, 7),
                    Arrays.asList(2, 5, 8),
                    Arrays.asList(3, 6, 9)
            );

            System.out.println(mergeKLists(lists));
        }

        static class Node {
            int value;
            int listIndex;
            int elementIndex;

            Node(int value, int listIndex, int elementIndex) {
                this.value = value;
                this.listIndex = listIndex;
                this.elementIndex = elementIndex;
            }
        }
    }


    import java.util.*;

    class LFUCache {

        class Node {
            int key, value, freq;
            Node prev, next;

            Node(int k, int v) {
                key = k;
                value = v;
                freq = 1;
            }
        }

        class DoublyLinkedList {
            Node head, tail;
            int size;

            DoublyLinkedList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            void add(Node node) {
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
                size++;
            }

            void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            Node removeLast() {
                if (size > 0) {
                    Node last = tail.prev;
                    remove(last);
                    return last;
                }
                return null;
            }
        }

        int capacity, minFreq;
        Map<Integer, Node> cache;
        Map<Integer, DoublyLinkedList> freqMap;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            freqMap = new HashMap<>();
            minFreq = 0;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) return -1;

            Node node = cache.get(key);
            update(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) return;

            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                update(node);
            } else {
                if (cache.size() == capacity) {
                    DoublyLinkedList minList = freqMap.get(minFreq);
                    Node toRemove = minList.removeLast();
                    cache.remove(toRemove.key);
                }

                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                minFreq = 1;

                freqMap.putIfAbsent(1, new DoublyLinkedList());
                freqMap.get(1).add(newNode);
            }
        }

        private void update(Node node) {
            int freq = node.freq;
            DoublyLinkedList list = freqMap.get(freq);
            list.remove(node);

            if (freq == minFreq && list.size == 0) {
                minFreq++;
            }

            node.freq++;
            freqMap.putIfAbsent(node.freq, new DoublyLinkedList());
            freqMap.get(node.freq).add(node);
        }
    }
}
