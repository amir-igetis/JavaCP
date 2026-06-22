package striverAToZ.graphs.dfsBfsProbs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponentProbInMat {
    public static void main(String[] args) {
        int V = 5;

        // List of undirected edges
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};

        System.out.println(countComponents(V, edges));
    }


    // do it using dfs HW
    static int countComponentsI(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] vis = new boolean[V];
        int components = 0;
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                components++;
                dfs(i, adj, vis);
            }
        }
        return components;
    }

    private static void dfs(int node, List<List<Integer>> adjList, boolean[] vis) {
        vis[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!vis[neighbor])
                dfs(neighbor, adjList, vis);
        }
    }

    // using bfs O(v + e)
    static int countComponents(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] vis = new boolean[V];
        int components = 0;
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                components++;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                vis[i] = true;
                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int neighbor : adj.get(node)) {
                        if (!vis[neighbor]) {
                            vis[neighbor] = true;
                            q.offer(neighbor);
                        }
                    }
                }
            }
        }
        return components;
    }
}
