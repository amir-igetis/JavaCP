package striverAToZ.graphs;

import java.util.*;

public class ShortestPathInUndirectedGraphWithUnitWeights {
    public static void main(String[] args) {
        int N = 9, M = 10;
        int[][] edges = {
                {0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6},
                {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}
        };

        System.out.println(Arrays.toString(shortestPath(edges, N, M, 0)));

    }

    static int[] shortestPath(int[][] edges, int n, int m, int src) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);

        }

        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                if (distance[node] + 1 < distance[neighbor]) {
                    distance[neighbor] = distance[node] + 1;
                    q.add(neighbor);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (distance[i] == Integer.MAX_VALUE)
                distance[i] = -1;
        }
        return distance;
    }
}
