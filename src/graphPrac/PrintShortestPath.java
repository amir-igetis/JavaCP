package graphPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class PrintShortestPath {
    public static void main(String[] args) {
        int v = 5;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 1, 2, 2);
        addEdge(adj, 1, 4, 1);
        addEdge(adj, 2, 3, 4);
        addEdge(adj, 2, 5, 5);
        addEdge(adj, 3, 5, 1);
        addEdge(adj, 4, 3, 3);

        int source = 1;

        int[] ans = printShortestPath(adj, v, source);
        for (int i = 1; i <= v; i++) {
            if (i != source) {
                System.out.print("Shortest path to " + i + ": ");
                printPath(i, ans);
                System.out.println();
            }
        }
    }

    static int[] printShortestPath(ArrayList<ArrayList<Pair>> adj, int v, int s) {
        int[] dist = new int[v + 1];
        int[] parent = new int[v + 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        parent[s] = s;
        pq.add(new Pair(s, 0));
        while (!pq.isEmpty()) {
            int node = pq.peek().node;
            int distance = pq.peek().weight;
            pq.remove();
            for (int i = 0; i < adj.get(node).size(); i++) {
                int edgeWeight = adj.get(node).get(i).weight;
                int newNode = adj.get(node).get(i).node;
                if (edgeWeight + distance < dist[newNode]) {
                    dist[newNode] = edgeWeight + distance;
                    pq.add(new Pair(newNode, dist[newNode]));
                    parent[newNode] = node;
                }
            }
        }
        return parent;
    }

    private static void printPath(int node, int[] parent) {
        Stack<Integer> path = new Stack<>();
        while (parent[node] != node) {
            path.push(node);
            node = parent[node];
        }
        path.push(node); // Push the source node

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    private static void addEdge(ArrayList<ArrayList<Pair>> adj, int u, int v, int weight) {
        adj.get(u).add(new Pair(v, weight));
        adj.get(v).add(new Pair(u, weight));
    }
}
