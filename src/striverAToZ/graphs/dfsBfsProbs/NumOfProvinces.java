package striverAToZ.graphs.dfsBfsProbs;

import java.util.ArrayList;
import java.util.List;

// Question No. - 01
public class NumOfProvinces {
    public static void main(String[] args) {
        int[][] adj = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };

        // Number of vertices
        int V = 3;

        System.out.println(numOfProvinces(adj, V));
    }

    static int numOfProvinces(int[][] adj, int V) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adjList.add(new ArrayList<>());

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adj[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        boolean[] vis = new boolean[V];
        int count = 1;
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                count++;
                dfs(i, adjList, vis);
            }
        }
        return count;
    }

    private static void dfs(int node, List<List<Integer>> adjList, boolean[] vis) {
        vis[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!vis[neighbor])
                dfs(neighbor, adjList, vis);
        }
    }
}
