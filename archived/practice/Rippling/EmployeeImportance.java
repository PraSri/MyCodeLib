package Rippling;

import java.util.*;

// https://leetcode.com/problems/employee-importance/editorial/
public class EmployeeImportance {

    private Map<Integer, Employee> employeeMap;

    public int getImportance(List<Employee> employees, int id) {

        employeeMap = new HashMap<>();
        for (Employee e : employees) {
            employeeMap.put(e.id, e);
        }

        return dfs(id);
    }

    private int dfs(int id) {
        Employee emp = employeeMap.get(id);
        int total = emp.importance;
        for (Integer subOrdinate : emp.getSubordinates()) {
            total += dfs(subOrdinate);
        }
        return total;
    }

    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImportance() {
            return importance;
        }

        public void setImportance(int importance) {
            this.importance = importance;
        }

        public List<Integer> getSubordinates() {
            return subordinates;
        }

        public void setSubordinates(List<Integer> subordinates) {
            this.subordinates = subordinates;
        }
    }
}
