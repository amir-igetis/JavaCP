package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathInDag {
    public static void main(String[] args) {
        int N = 6, M = 7;

        // Edge list with weights
        int[][] edges = {
                {0, 1, 2}, {0, 4, 1}, {4, 5, 4},
                {4, 2, 2}, {1, 2, 3}, {2, 3, 6}, {5, 3, 1}
        };

        int[] ans = shortestPath(N, M, edges);
        for (int i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    // using topo sort tc O(N+M) sc O(N)
    static int[] shortestPath(int N, int M, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new int[]{v, wt});
        }
        boolean[] vis = new boolean[N];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (!vis[i])
                topoSort(i, adj, vis, st);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, (int) 1e9 + 7);
        dist[0] = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (dist[node] != (int) 1e9 + 7) {
                for (int[] neighbor : adj.get(node)) {
                    int v = neighbor[0];
                    int wt = neighbor[1];
                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (dist[i] == (int) 1e9 + 7)
                dist[i] = -1;

        }
        return dist;
    }

    private static void topoSort(int node, List<List<int[]>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (int[] neighbor : adj.get(node)) {
            if (!vis[neighbor[0]])
                topoSort(neighbor[0], adj, vis, st);
        }
        st.push(node);
    }
}
