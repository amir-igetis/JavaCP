package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.*;

public class ShortestPathInUndirectedGraphWithUnitWeights {
    public static void main(String[] args) {
        int N = 9, M = 10;
        int[][] edges = {
                {0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6},
                {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}
        };
        int[] ans = shortestPath(edges, N, M, 0);
        for (int i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    // bfs O(M) sc O(N)
    static int[] shortestPath(int[][] edges, int N, int M, int src) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++)
            adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, (int) 1e9 + 7);
        dist[src] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                if (dist[node] + 1 < dist[neighbor]) {
                    dist[neighbor] = dist[node] + 1;
                    q.add(neighbor);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (dist[i] == (int) 1e9 + 7)
                dist[i] = -1;
        }
        return dist;
    }
}
