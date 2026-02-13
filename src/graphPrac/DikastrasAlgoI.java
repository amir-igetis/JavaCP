package graphPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DikastrasAlgoI {
    public static void main(String[] args) {
        int v = 6;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1, 4);
        addEdge(adj, 0, 2, 4);
        addEdge(adj, 1, 2, 2);
        addEdge(adj, 2, 3, 3);
        addEdge(adj, 2, 5, 6);
        addEdge(adj, 2, 4, 1);
        addEdge(adj, 3, 5, 2);
        addEdge(adj, 4, 5, 3);

        int source = 0;
        int[] res = dikastras(adj, v, source);
        for (int i : res) {
            System.out.print(i + ",");
        }
        System.out.println();

    }

    static int[] dikastras(ArrayList<ArrayList<Pair>> adj, int v, int source) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        pq.add(new Pair(source, 0));
        while (!pq.isEmpty()) {
            int distance = pq.peek().weight;
            int node = pq.peek().node;
            pq.remove();
            for (int i = 0; i < adj.get(node).size(); i++) {
                int edgeWeight = adj.get(node).get(i).weight;
                int adjNode = adj.get(node).get(i).node;
                if (distance + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = distance + edgeWeight;
                    pq.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    }

    private static void addEdge(ArrayList<ArrayList<Pair>> adj, int u, int v, int weight) {
        adj.get(u).add(new Pair(v, weight));
        adj.get(v).add(new Pair(u, weight));
    }
}
