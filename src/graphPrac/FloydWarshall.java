package graphPrac;

import java.util.Arrays;

public class FloydWarshall {

    public static void main(String[] args) {
        int V = 4; // Number of vertices
        int[][] graph = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        };

        FloydWarshall fw = new FloydWarshall();
        fw.floydWarshall(graph, V);
    }

    final static int INF = 99999; // Represents infinity

    void floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];

        // Step 1: Initialize the solution matrix same as input graph matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Step 2: Update the distance matrix considering each vertex as an intermediate vertex
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // If the path through vertex k is shorter, update dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Step 3: Print the shortest distance matrix
        printSolution(dist, V);
    }

    void printSolution(int[][] dist, int V) {
        System.out.println("The following matrix shows the shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
