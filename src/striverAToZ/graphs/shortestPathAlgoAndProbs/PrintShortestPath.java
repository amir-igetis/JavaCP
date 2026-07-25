package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PrintShortestPath {
    public static void main(String[] args) {
        int n = 5, m = 6;
        int[][] edges = {
                {1, 2, 2}, {2, 5, 5}, {2, 3, 4}, {1, 4, 1}, {4, 3, 3}, {3, 5, 1}
        };
        List<Integer> ans = shortestPath(n, m, edges);
        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static List<Integer> shortestPath(int n, int m, int[][] edges) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.x - b.x);
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = (int) 1e9 + 7;
            parent[i] = i;
        }
        dist[1] = 0;
        pq.add(new Pair(0, 1));
        while (!pq.isEmpty()) {
            Pair it = pq.poll();
            int node = it.y;
            int dis = it.x;
            for (Pair iter : adj.get(node)) {
                int adjNode = iter.x;
                int edW = iter.y;
                if (dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    pq.add(new Pair(dis + edW, adjNode));
                    parent[adjNode] = node;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        if (dist[n] == (int) 1e9 + 7) {
            path.add(-1);
            return path;
        }
        int node = n;
        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        Collections.reverse(path);
        return path;
    }

    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
