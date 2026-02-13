package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class MaxNumOfKDivisibleComponents {
    public static void main(String[] args) {
        int[][] edges = {{0, 2}, {1, 2}, {1, 3}, {2, 4}};
        int[] values = {1, 8, 1, 4, 4};
        int k = 6, n = 5;
        System.out.println(maxKDivisibleComponents(n, edges, values, k));
    }

    static int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        return dfs(0, -1, graph, values, k)[1];

    }

    private static int[] dfs(int node, int parent, List<List<Integer>> graph, int[] values, int k) {
        int sum = values[node];
        int count = 0;
        for (int nei : graph.get(node)) {
            if (nei == parent)
                continue;

            int[] child = dfs(nei, node, graph, values, k);

            sum += child[0];
            count += child[1];
        }

        if (sum % k == 0) {
            count++;
            sum = 0;
        }

        return new int[]{sum % k, count};
    }
}
