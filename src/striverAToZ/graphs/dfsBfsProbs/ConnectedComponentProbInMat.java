package striverAToZ.graphs.dfsBfsProbs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Question No. - 02
public class ConnectedComponentProbInMat {

    /// Problem Statement: Given an undirected Graph consisting of V vertices numbered from 0 to V-1 and E edges.
    /// The ith edge is represented by (ai,bi),
    ///  denoting a edge between vertex ai and bi. We say two vertices u and v belong to a same component if there is
    /// a path from u to v or v to u. Find the number of connected components in the graph.
    ///
    /// A connected component is a subgraph of a graph in which there exists a path between any two vertices,
    /// and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

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
