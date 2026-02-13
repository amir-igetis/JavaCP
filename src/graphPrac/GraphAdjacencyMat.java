package graphPrac;

import java.util.Arrays;
import java.util.Scanner;

public class GraphAdjacencyMat {
    public static void main(String[] args) {
        int vertex, edges;

        Scanner sc = new Scanner(System.in);

        vertex = sc.nextInt();
        edges = sc.nextInt();

//        adjcency matrix
        int[][] adj = new int[vertex][vertex];
        for (int i = 0; i < vertex; i++) {
            Arrays.fill(adj[i], 0);
        }
//        Unirected Graph
        /*
        int u, v;
        for (int i = 0; i < edges ; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            adj[u][v] = 1;
            adj[v][u] = 1;
        }
        */

//        Unirected Weighted Graph
        /*
        int u, v, weight;
        for (int i = 0; i < edges ; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            weight = sc.nextInt();
            adj[u][v] = weight;
            adj[v][u] = weight;
        }
         */

//        Directed weighted Graph
        /*
        int u, v, weight;
        for (int i = 0; i < edges ; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            weight = sc.nextInt();
            adj[u][v] = weight;
        }

         */

//        Directed Graph
        int u, v;
        for (int i = 0; i < edges; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            adj[u][v] = 1;
        }


        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }

    }
}
