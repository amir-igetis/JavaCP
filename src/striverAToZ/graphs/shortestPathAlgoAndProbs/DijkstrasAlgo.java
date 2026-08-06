package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.*;

public class DijkstrasAlgo {


    /// Question 3 and 4
    /// Problem Statement: Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj
    /// where adj(i) is a list of lists containing two integers where the first integer of each list j denotes there is
    /// an edge between i and j, second integers corresponds to the weight of that edge. You are given the source
    /// vertex S and You have to Find the shortest distance of all the vertex from the source vertex S. You have to
    /// return a list of integers denoting the shortest distance between each node and Source vertex S.
    ///
    /// Note: The Graph doesn’t contain any negative weight cycle

    public static void main(String[] args) {
        int V = 3, E = 3, S = 2;

        // Create adjacency list to represent the graph
        ArrayList<int[]>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Add edges to the graph
        adj[0].add(new int[]{1, 1});
        adj[0].add(new int[]{2, 6});
        adj[1].add(new int[]{2, 3});
        adj[1].add(new int[]{0, 1});
        adj[2].add(new int[]{1, 3});
        adj[2].add(new int[]{0, 6});

        int[] ans = dijkstraI(V, adj, S);
        for (int i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    /// Time Complexity: O(E log V), as each edge leads to at most one insertion in the priority queue, which takes log V time.
    ///
    /// Space Complexity: O(V + E), due to the distance array and adjacency list storing all vertices and edges.

    static int[] dijkstra(int V, ArrayList<int[]>[] adj, int S) {

        ///  min heap the smallest value always on the top
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[S] = 0;
        pq.offer(new int[]{0, S});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int dis = curr[0];
            int node = curr[1];

            for (int[] edge : adj[node]) {
                int adjNode = edge[0];
                int weight = edge[1];

                if (dis + weight < dist[adjNode]) {
                    dist[adjNode] = dis + weight;
                    pq.offer(new int[]{dist[adjNode],
                            adjNode});
                }
            }
        }

        return dist;
    }

    /// using set
    static int[] dijkstraI(int V, ArrayList<int[]>[] adj, int src) {

        TreeSet<int[]> st = new TreeSet<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9);

        dist[src] = 0;
        st.add(new int[]{0, src});

        while (!st.isEmpty()) {

            int[] cur = st.pollFirst();

            int dis = cur[0];
            int node = cur[1];

            for (int[] it : adj[node]) {

                int adjNode = it[0];
                int edgeW = it[1];

                if (dis + edgeW < dist[adjNode]) {

                    if (dist[adjNode] != (int)1e9) {
                        st.remove(new int[]{dist[adjNode], adjNode});
                    }

                    dist[adjNode] = dis + edgeW;
                    st.add(new int[]{dist[adjNode], adjNode});
                }
            }
        }

        return dist;
    }
}
