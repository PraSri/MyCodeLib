package topological_sort;


import java.util.*;

public class CompilationOrder {

    public static String repeat(String str, int pValue) {
        String out = "";
        for (int i = 0; i < pValue; i++) {
            out += str;
        }
        return out;
    }

    public static List<Character> findCompilationOrder(ArrayList<ArrayList<Character>> dependencies) {
        List<Character> sortedOrder = new ArrayList<>();

        // create a graph with adjacency list
        // example: A -> [B,C], C -> [D]
        HashMap<Character, List<Character>> graph = new HashMap<>();

        // indegree of each vertices, means number of incoming edges a vertex has
        HashMap<Character, Integer> inDegree = new HashMap<>();

        // initialize graph and in degree maps
        for( int x = 0; x < dependencies.size(); x++){
            char parent = dependencies.get(x).get(0);
            char child = dependencies.get(x).get(1);
            graph.put(parent, new ArrayList<>());
            graph.put(child, new ArrayList<>());
            inDegree.put(parent, 0);
            inDegree.put(child, 0);
        }

        if (graph.size() <= 0) {
            return sortedOrder;
        }

        // build graph and fill indegree map
        for (int dependency = 0; dependency < dependencies.size(); dependency++) {
            char parent = dependencies.get(dependency).get(1);
            char child = dependencies.get(dependency).get(0);
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        // maintain a queue for sources, for topological sort
        Queue<Character> sources = new LinkedList<>();

        // all sources will have indegree as 0, push those into the queue
        for (char key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                sources.add(key);
            }
        }

        while (!sources.isEmpty()) {
            char vertex = sources.poll();

            sortedOrder.add(vertex);

            for (int child = 0; child < graph.get(vertex).size(); child++) {
                inDegree.put(graph.get(vertex).get(child), inDegree.get(graph.get(vertex).get(child)) - 1);
                if (inDegree.get(graph.get(vertex).get(child)) == 0) {
                    sources.add(graph.get(vertex).get(child));
                }
            }
        }

        // means cyclic or non connected graph
        if (sortedOrder.size() != graph.size()) {
            return new ArrayList<>();
        }

        return sortedOrder;
    }

    public static void main(String args[]) {
        ArrayList<ArrayList<ArrayList<Character>>> dependencies = new ArrayList<ArrayList<ArrayList<Character>>>() {
            {
                add(new ArrayList<ArrayList<Character>>() {
                    {
                        add(new ArrayList<Character>(Arrays.asList('B', 'A')));
                        add(new ArrayList<Character>(Arrays.asList('C', 'A')));
                        add(new ArrayList<Character>(Arrays.asList('D', 'C')));
                        add(new ArrayList<Character>(Arrays.asList('E', 'D')));
                        add(new ArrayList<Character>(Arrays.asList('E', 'B')));
                    }
                });
                add(new ArrayList<ArrayList<Character>>() {
                    {
                        add(new ArrayList<Character>(Arrays.asList('B', 'A')));
                        add(new ArrayList<Character>(Arrays.asList('C', 'A')));
                        add(new ArrayList<Character>(Arrays.asList('D', 'B')));
                        add(new ArrayList<Character>(Arrays.asList('E', 'B')));
                        add(new ArrayList<Character>(Arrays.asList('E', 'D')));
                        add(new ArrayList<Character>(Arrays.asList('E', 'C')));
                        add(new ArrayList<Character>(Arrays.asList('F', 'D')));
                        add(new ArrayList<Character>(Arrays.asList('F', 'E')));
                        add(new ArrayList<Character>(Arrays.asList('F', 'C')));
                    }
                });
                add(new ArrayList<ArrayList<Character>>() {
                    {
                        add(new ArrayList<Character>(Arrays.asList('A', 'B')));
                        add(new ArrayList<Character>(Arrays.asList('B', 'A')));
                    }
                });
                add(new ArrayList<ArrayList<Character>>() {
                    {
                        add(new ArrayList<Character>(Arrays.asList('B', 'C')));
                        add(new ArrayList<Character>(Arrays.asList('C', 'A')));
                        add(new ArrayList<Character>(Arrays.asList('A', 'F')));
                    }
                });
                add(new ArrayList<ArrayList<Character>>() {
                    {
                        add(new ArrayList<Character>(Arrays.asList('C', 'C')));
                    }
                });
            }
        };
        for (int i = 0; i < dependencies.size(); i++) {
            System.out.println(i + 1 + ".\tdependencies: " + dependencies.get(i));
            System.out.println("\tCompilation Order: " + findCompilationOrder(dependencies.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
