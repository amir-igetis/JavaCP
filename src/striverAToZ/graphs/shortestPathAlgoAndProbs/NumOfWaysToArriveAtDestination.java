package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.*;

public class NumOfWaysToArriveAtDestination {

    /// Problem Statement: You are in a city that consists of n intersections numbered from 0 to n - 1 with
    /// bi-directional roads between some intersections. The inputs are generated such that you can reach any
    /// intersection from any other intersection and that there is at most one road between any two intersections.
    ///
    /// You are given an integer n and a 2D integer array ‘roads’ where roads(i) = (ui, vi, timei) means that
    /// there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how
    /// many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
    ///
    /// Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer
    /// may be large, return it modulo 109 + 7.

    public static void main(String[] args) {

        // Driver Code
        int n = 7;

        // Define the edges (source, destination, weight)
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3},
                {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};

        // Call the method to count the number of shortest paths
        int ans = CheapestFLight(n, roads, 0, 3, 1);

        // Output the result
        System.out.println(ans);
    }

    /// Time Complexity: O(E * log(V)), as we are using simple Dijkstra's algorithm here, the time complexity will
    /// be of the order E * log(V). Where E = Number of edges and V = Number of vertices.
    ///
    /// Space Complexity: O(N), for the dist array, ways array, and approximate complexity for the priority queue.
    /// Where N = Number of nodes.

    static int CheapestFLight(int n, int[][] roads, int src, int dst, int K) {

        // Create the adjacency list to represent airports and flights as a graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            adj.get(road[0]).add(new int[]{road[1], road[2]});
            adj.get(road[1]).add(new int[]{road[0], road[2]});
        }

        // Create a priority queue (min heap) for Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Initialize the distance array and ways array
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] ways = new int[n];
        dist[src] = 0;
        ways[src] = 1;
        // Push the source node with distance 0
        pq.add(new int[]{0, src});

        // Define the modulo value for large numbers
        int mod = (int) (1e9 + 7);

        // Perform Dijkstra's algorithm
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int dis = current[0];
            int node = current[1];

            // Iterate through adjacent nodes
            for (int[] neighbor : adj.get(node)) {
                // Next destination node
                int adjNode = neighbor[0];
                // Cost of the flight to the next destination
                int edW = neighbor[1];

                // If a shorter path is found, update the distance and number of ways
                if (dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    // Push the new node with updated distance
                    pq.add(new int[]{dis + edW, adjNode});
                    // Copy the number of ways to the new node
                    ways[adjNode] = ways[node];
                }
                // If the same shortest path is found, update the number of ways
                else if (dis + edW == dist[adjNode]) {
                    // Increment the number of ways
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        // Return the number of ways to reach the last node modulo 10^9 + 7
        return ways[dst] % mod;
    }

}
