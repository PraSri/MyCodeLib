package union_find;

import java.util.*;

public class AccountsMerge {

    public static class UnionFind {
        private int[] parents;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int node) {
            if (parents[node] == node) {
                return node;
            }
            return find(parents[node]);
        }

        public void union(int node1, int node2) {
            int rootNode1 = find(node1);
            int rootNode2 = find(node2);
            if (rootNode1 != rootNode2) {
                parents[rootNode2] = rootNode1;
            }
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind uf = new UnionFind(accounts.size());

        // Create a map for mapping emails to their parent IDs
        Map<String, Integer> emailMapping = new HashMap<>();
        for (int i = 0; i < accounts.size(); ++i) {
            List<String> emails = accounts.get(i);

            // If the email already exists in the map, take union
            for (int j = 1; j < emails.size(); ++j) {
                if (emailMapping.containsKey(emails.get(j))) {

                    // Before we take the union, make sure both the accounts have the same name
                    if (!accounts.get(i).get(0).equals(accounts.get(emailMapping.get(emails.get(j))).get(0))) {
                        return new ArrayList<>(); // Return empty list to indicate an error
                    }
                    uf.union(emailMapping.get(emails.get(j)), i);
                }
                // Add email with its ID to the map
                emailMapping.put(emails.get(j), i);
            }
        }

        // Create a map to store the merged accounts
        Map<Integer, List<String>> mergedAccounts = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailMapping.entrySet()) {
            int root = uf.find(entry.getValue());
            mergedAccounts.computeIfAbsent(root, k -> new ArrayList<>()).add(entry.getKey());
        }

        // Sort the merged accounts
        List<List<String>> finalMerged = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : mergedAccounts.entrySet()) {
            int parent = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(parent).get(0));
            merged.addAll(emails);
            finalMerged.add(merged);
        }

        return finalMerged;
    }

    // driver code
    public static void main(String[] args) {
        List<List<List<String>>> allAccounts = new ArrayList<>();
        allAccounts.add(Arrays.asList(
                Arrays.asList("Emma", "emma@mail.com", "emma_work@mail.com"),
                Arrays.asList("Bob", "bob_home@mail.com", "bob123@mail.com"),
                Arrays.asList("Emma", "emma_art@mail.com", "emma_work@mail.com"),
                Arrays.asList("Bob", "bob321@mail.com")
        ));
        allAccounts.add(Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com")
        ));
        allAccounts.add(Arrays.asList(
                Arrays.asList("Sarah", "sarah@mail.com", "sh@mail.com"),
                Arrays.asList("Sarah", "sarah1@mail.com", "sarahh@mail.com"),
                Arrays.asList("Sarah", "sh3@mail.com")
        ));
        allAccounts.add(Arrays.asList(
                Arrays.asList("Alice", "alice@mail.com"),
                Arrays.asList("Alice", "alice_alice@mail.com", "alice@mail.com"),
                Arrays.asList("Alice", "alice@mail.com", "alice123@mail.com", "aalicee@mail.com")

        ));
        allAccounts.add(Arrays.asList(
                Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"),
                Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"),
                Arrays.asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"),
                Arrays.asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"),
                Arrays.asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co")
        ));

        for (int i = 0; i < allAccounts.size(); ++i) {
            System.out.println(i + 1 + ". \tAccounts:\n\t[");
            print2DArray(allAccounts.get(i));
            System.out.println("\t]");

            List<List<String>> merged = accountsMerge(allAccounts.get(i));

            if (merged.isEmpty()) {
                System.out.println("Error!\nAccounts sharing some email(s) should have the same names.\n");
                return;
            }

            System.out.println("\n\tMerged accounts:\n\t[");
            print2DArray(merged);
            System.out.println("\t]\n");
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
        }
    }

    // Helper method to print a 2D array
    private static void print2DArray(List<List<String>> array) {
        for (List<String> row : array) {
            System.out.println("\t\t" + row);
        }
    }
}
