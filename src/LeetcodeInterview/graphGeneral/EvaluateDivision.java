package LeetcodeInterview.graphGeneral;

import java.util.*;

public class EvaluateDivision {
    public static void main(String[] args) {
//

        List<List<String>> equations, queries;
        double[] values;

    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String A = equations.get(i).get(0);
            String B = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(A, new HashMap<>());
            graph.putIfAbsent(B, new HashMap<>());
            graph.get(A).put(B, value);
            graph.get(B).put(A, 1.0 / value);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String C = queries.get(i).get(0);
            String D = queries.get(i).get(1);
            if (!graph.containsKey(C) || !graph.containsKey(D)) {
                result[i] = -1.0;
            } else {
                Set<String> visited = new HashSet<>();
                result[i] = dfs(graph, C, D, visited);
            }
        }
        return result;
    }

    private static double dfs(Map<String, Map<String, Double>> graph, String cur, String target, Set<String> visited) {
        if (cur.equals(target)) return 1.0;
        visited.add(cur);

        for (Map.Entry<String, Double> neighbor : graph.get(cur).entrySet()) {
            if (!visited.contains(neighbor.getKey())) {
                double result = dfs(graph, neighbor.getKey(), target, visited);
                if (result != -1.0) {
                    return result * neighbor.getValue();
                }
            }
        }
        return -1.0;
    }

    //    BFS
    static double[] calcEquationI(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // Step 1: Build the graph
        for (int i = 0; i < equations.size(); i++) {
            String A = equations.get(i).get(0);
            String B = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(A, new HashMap<>());
            graph.putIfAbsent(B, new HashMap<>());
            graph.get(A).put(B, value);
            graph.get(B).put(A, 1.0 / value);
        }

        // Step 2: Process queries using BFS
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String C = queries.get(i).get(0);
            String D = queries.get(i).get(1);
            if (!graph.containsKey(C) || !graph.containsKey(D)) {
                result[i] = -1.0;
            } else {
                result[i] = bfs(graph, C, D);
            }
        }
        return result;
    }

    private static double bfs(Map<String, Map<String, Double>> graph, String start, String target) {
        Queue<Pair<String, Double>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Pair<>(start, 1.0));

        while (!queue.isEmpty()) {
            Pair<String, Double> node = queue.poll();
            String cur = node.getKey();
            double curValue = node.getValue();

            if (cur.equals(target)) return curValue;
            visited.add(cur);

            for (Map.Entry<String, Double> neighbor : graph.get(cur).entrySet()) {
                if (!visited.contains(neighbor.getKey())) {
                    queue.offer(new Pair<>(neighbor.getKey(), curValue * neighbor.getValue()));
                }
            }
        }
        return -1.0;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
