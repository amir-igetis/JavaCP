package graphPrac;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphAdjacencyList {

    public static void main(String[] args) {
        int vertex, edges;
        Scanner sc = new Scanner(System.in);

        vertex = sc.nextInt();
        edges = sc.nextInt();
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            adjLs.add(new ArrayList<>());
        }

        int u, v;
        for (int i = 0; i < edges; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
        }

//        weighted undirected, directed weighted, directed graph we can do everything

        System.out.println(adjLs);
    }

}
