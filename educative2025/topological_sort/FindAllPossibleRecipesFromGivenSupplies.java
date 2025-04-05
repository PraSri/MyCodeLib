package topological_sort;

import java.util.*;

public class FindAllPossibleRecipesFromGivenSupplies {

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        Set<String> recipeSet = new HashSet<>();

        // add supply nodes to graph
        for(String supply : supplies) {
            graph.put(supply, new ArrayList<>());
            indegree.put(supply, 0);
        }

        // add recipe nodes to graph
        for(String recipe : recipes) {
            graph.put(recipe, new ArrayList<>());
            indegree.put(recipe, 0);
            recipeSet.add(recipe);
        }

        // add ingredient edges to graph
        for(int i = 0;i<ingredients.size();i++){
            for(String currIn: ingredients.get(i)) {
                graph.putIfAbsent(currIn, new ArrayList<>());
                graph.get(currIn).add(recipes[i]);
                indegree.put(recipes[i], indegree.get(recipes[i]) + 1);
            }
        }

        // standard topological sort
        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();

        for(String currNode: indegree.keySet()) {
            if(indegree.get(currNode) == 0) {
                queue.add(currNode);
            }
        }

        while (!queue.isEmpty()) {
            String currNode = queue.poll();
            if(recipeSet.contains(currNode)) {
                result.add(currNode);
            }
            for(String neighbour: graph.get(currNode)) {
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if(indegree.get(neighbour) == 0) {
                    queue.add(neighbour);
                }
            }
        }

        return result;

    }

}
