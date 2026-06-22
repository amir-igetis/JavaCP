package striverAToZ.graphs.dfsBfsProbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionInUndirectedGraphBFS {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        CycleDetectionInUndirectedGraphBFS obj = new CycleDetectionInUndirectedGraphBFS();
        boolean ans = obj.isCycle(4, adj);
        if (ans) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    // striver code
    // soln for
    // https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=detect-cycle-in-an-undirected-graph
    private static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int s, boolean[] vis, int[] parent) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, -1));
        vis[s] = true;
        // until the queue is empty
        while (!q.isEmpty()) {
            // source node and its parent node
            int node = q.peek().first;
            int par = q.peek().second;
            q.remove();

            // go to all the adjacent nodes
            for (Integer it : adj.get(node)) {
                if (vis[it] == false) {
                    q.add(new Node(it, node));
                    vis[it] = true;
                }

                // if adjacent node is visited and is not its own patent node
                else if (par != it) {
                    return true;
                }
            }
        }
        return false;
    }

    // function to detect cycle in an undirected graph
    static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Arrays.fill(vis, false);
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        for (int i = 0; i < V; i++) {
            if (vis[i] == false) {
                if (checkForCycle(adj, i, vis, parent)) {
                    return true;
                }
            }
        }
        return false;
    } // tc O(n + 2e) + o(n), Where N = Nodes, 2E is for total degrees as we traverse
    // all adjacent nodes. In the case of connected components of a graph, it will
    // take another O(N) time.
    // sc O(n) + O(n) ~ O(n), Space for queue data structure and visited array

    private static class Node {
        int first;
        int second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }

    }
}


