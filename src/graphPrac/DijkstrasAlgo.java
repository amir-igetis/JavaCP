package graphPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstrasAlgo {
    public static void main(String[] args) {
        int v = 6; // Total vertices should be one less than 7 as per the edge definition
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }

        addEdgeWeighted(adj, 0, 1, 4);
        addEdgeWeighted(adj, 0, 2, 3);
        addEdgeWeighted(adj, 1, 2, 1);
        addEdgeWeighted(adj, 1, 3, 2);
        addEdgeWeighted(adj, 2, 3, 4);
        addEdgeWeighted(adj, 3, 4, 2);
        addEdgeWeighted(adj, 4, 5, 6);

        int source = 0;
        ArrayList<Integer> res = dijkstra(adj, v + 1, source);
        System.out.println("Distance from sources are : ");
        for (int i = 0; i < res.size(); i++)
            System.out.println(i + "\t" + res.get(i));
    }

    static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Pair>> adj, int v, int source) {
        int[] dist = new int[v];
        boolean[] vis = new boolean[v];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(vis, false);
        dist[source] = 0;
        pq.add(new Pair(source, 0));
        while (!pq.isEmpty()) {
            int u = pq.poll().node;
            if (vis[u]) continue;
            vis[u] = true;
            for (Pair i : adj.get(u)) {
                int vertex = i.node;
                int weight = i.weight;
                if (!vis[vertex] && dist[u] + weight < dist[vertex]) {
                    dist[vertex] = dist[u] + weight;
                    pq.add(new Pair(vertex, dist[vertex]));
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i : dist) {
            ans.add(i);
        }
        return ans;
    }

    private static void addEdgeWeighted(ArrayList<ArrayList<Pair>> adj, int u, int v, int weight) {
        adj.get(u).add(new Pair(v, weight));
        adj.get(v).add(new Pair(u, weight));
    }
}