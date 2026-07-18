package striverAToZ.graphs.topoSortProbs;

import java.util.*;

public class DetectCycleInDirectedGraph {
    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1);
        if (hasCycle(V, adj))
            System.out.println("Cycle Detected");
        else System.out.println("No Cycle");

        if (hasCycleI(V, adj))
            System.out.println("Cycle Detected");
        else System.out.println("No Cycle");
    }

    // DFS

    private static boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] pathVis) {
        vis[node] = 1;
        pathVis[node] = 1;

        for (int it : adj.get(node)) {

            if (vis[it] == 0) {
                if (dfs(it, adj, vis, pathVis))
                    return true;
            } else if (pathVis[it] == 1) {
                return true;
            }
        }

        pathVis[node] = 0;
        return false;
    }

    static boolean hasCycleI(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        int[] pathVis = new int[V];

        for (int i = 0; i < V; i++) {

            if (vis[i] == 0) {

                if (dfs(i, adj, vis, pathVis))
                    return true;
            }
        }

        return false;
    }

    // BFS
    static boolean hasCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int v : adj.get(i))
                indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;
            for (int neighbot : adj.get(node)) {
                indegree[neighbot]--;
                if (indegree[neighbot] == 0)
                    q.add(neighbot);
            }
        }
        return count != V;
    }
}
