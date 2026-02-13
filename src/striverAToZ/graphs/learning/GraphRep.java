package striverAToZ.graphs.learning;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphRep {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
//            for directed graph just adj[i].add(v);
            adj[u].add(v);
            adj[v].add(u);
        }
        sc.close();
    }
}
