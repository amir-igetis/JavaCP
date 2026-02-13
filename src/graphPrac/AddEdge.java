package graphPrac;

import java.util.ArrayList;

public class AddEdge {
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    int u; int v;

    AddEdge() {}
    AddEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        this.adj = adj;
        this.u = u;
        this.v = v;
    }

    public void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
