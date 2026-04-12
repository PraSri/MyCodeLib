package uber;

import java.util.*;

/**
 * Employee Hierarchy Manager
 * Supports:
 * - Query total employees (direct + indirect) under a manager in O(1)
 * - Change manager of an employee (moves entire subtree)
 * - Add new employee-manager relationships
 */
public class EmployeeHierarchy {
    // parent map: employee -> direct manager (null for root)
    private final Map<String, String> parent = new HashMap<>();
    // size map: employee -> total number of employees in its subtree (including itself)
    private final Map<String, Integer> subtreeSize = new HashMap<>();
    // direct reports: manager -> list of immediate subordinates
    private final Map<String, List<String>> directReports = new HashMap<>();

    /**
     * Initializes the hierarchy from a list of edges.
     * Each edge [a, b] means a is manager of b.
     */
    public EmployeeHierarchy(List<List<String>> edges) {
        for (List<String> edge : edges) {
            String manager = edge.get(0);
            String employee = edge.get(1);
            addNewRelation(manager, employee);
        }
    }

    // ---------- Main method to demonstrate usage ----------
    public static void main(String[] args) {
        // Example edges: [a,b], [b,c], [d,f], [f,g]
        List<List<String>> edges = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("d", "f"),
                Arrays.asList("f", "g")
        );

        EmployeeHierarchy hierarchy = new EmployeeHierarchy(edges);
        System.out.println("Initial hierarchy:");
        hierarchy.printHierarchy();

        // Query: employees under a
        System.out.println("Employees under a: " + hierarchy.getEmployeeCountUnder("a")); // 2 (b and c)
        // Query: under b
        System.out.println("Employees under b: " + hierarchy.getEmployeeCountUnder("b")); // 1 (c)
        // Query: under d
        System.out.println("Employees under d: " + hierarchy.getEmployeeCountUnder("d")); // 2 (f and g)

        // Change manager: move c under f
        System.out.println("\nMoving c under f...");
        hierarchy.changeManager("c", "f");
        hierarchy.printHierarchy();
        System.out.println("Employees under b: " + hierarchy.getEmployeeCountUnder("b")); // 0 (c moved away)
        System.out.println("Employees under f: " + hierarchy.getEmployeeCountUnder("f")); // 2 (g and c)
        System.out.println("Employees under a: " + hierarchy.getEmployeeCountUnder("a")); // 1 (b only)

        // Add new relation: c -> d (but d already exists as manager? Actually we add new employee? Let's add new employee x under a)
        System.out.println("\nAdding new employee x under a...");
        hierarchy.addNewRelation("a", "x");
        System.out.println("Employees under a: " + hierarchy.getEmployeeCountUnder("a")); // 2 (b and x)
        hierarchy.printHierarchy();
    }

    /**
     * Adds a new employee under a manager.
     * Assumes the employee does not already exist in the hierarchy.
     */
    public void addNewRelation(String manager, String employee) {
        if (parent.containsKey(employee)) {
            throw new IllegalArgumentException("Employee " + employee + " already exists");
        }
        // Initialize new employee
        parent.put(employee, manager);
        subtreeSize.put(employee, 1);
        // Add to direct reports of manager
        directReports.computeIfAbsent(manager, k -> new ArrayList<>()).add(employee);
        // Update subtree sizes of all ancestors of manager
        updateAncestors(manager, 1);
    }

    /**
     * Returns the total number of employees (direct + indirect) under the given manager.
     * Excludes the manager himself.
     */
    public int getEmployeeCountUnder(String manager) {
        if (!subtreeSize.containsKey(manager)) {
            return 0;
        }
        return subtreeSize.get(manager) - 1;
    }

    /**
     * Changes the manager of an employee.
     * The entire subtree rooted at that employee moves to the new manager.
     */
    public void changeManager(String employee, String newManager) {
        if (!parent.containsKey(employee)) {
            throw new IllegalArgumentException("Employee " + employee + " does not exist");
        }
        if (!parent.containsKey(newManager)) {
            throw new IllegalArgumentException("Manager " + newManager + " does not exist");
        }
        if (employee.equals(newManager)) {
            throw new IllegalArgumentException("Cannot set an employee as their own manager");
        }
        // Check for cycle: newManager cannot be a descendant of employee
        if (isDescendant(employee, newManager)) {
            throw new IllegalArgumentException("Cannot move manager under its own subordinate");
        }

        String oldManager = parent.get(employee);
        int movedSubtreeSize = subtreeSize.get(employee);

        // Remove from old manager's direct reports
        if (oldManager != null) {
            directReports.get(oldManager).remove(employee);
            // Update ancestors of old manager (including old manager) by subtracting moved subtree size
            updateAncestors(oldManager, -movedSubtreeSize);
        }

        // Attach to new manager
        parent.put(employee, newManager);
        directReports.computeIfAbsent(newManager, k -> new ArrayList<>()).add(employee);
        // Update ancestors of new manager (including new manager) by adding moved subtree size
        updateAncestors(newManager, movedSubtreeSize);
    }

    // Helper: Update subtree sizes for all ancestors of a node (including the node itself)
    private void updateAncestors(String node, int delta) {
        String current = node;
        while (current != null) {
            subtreeSize.put(current, subtreeSize.get(current) + delta);
            current = parent.get(current);
        }
    }

    // Helper: Check if 'ancestor' is an ancestor of 'descendant'
    private boolean isDescendant(String ancestor, String descendant) {
        String current = descendant;
        while (current != null) {
            if (current.equals(ancestor)) {
                return true;
            }
            current = parent.get(current);
        }
        return false;
    }

    // Optional: Print the hierarchy for debugging
    public void printHierarchy() {
        for (String mgr : directReports.keySet()) {
            System.out.println(mgr + " -> " + directReports.get(mgr));
        }
        System.out.println("Sizes: " + subtreeSize);
    }
}
