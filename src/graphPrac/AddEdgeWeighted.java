package graphPrac;

import java.util.ArrayList;

public class AddEdgeWeighted {
    ArrayList<ArrayList<ArrayList<Integer>>> adj;
    int u; int v;
    int weight;

    public AddEdgeWeighted() {
    }

    public AddEdgeWeighted(ArrayList<ArrayList<ArrayList<Integer>>> adj, int u, int v, int weight) {
        this.adj = adj;
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    
    public void addEdgeWeight(ArrayList<ArrayList<ArrayList<Integer>>> adj, int u, int v, int weight) {
        ArrayList<Integer> edge1 = new ArrayList<>();
        edge1.add(v);
        edge1.add(weight);
        adj.get(u).add(edge1);

        ArrayList<Integer> edge2 = new ArrayList<>();
        edge2.add(u);
        edge2.add(weight);
        adj.get(v).add(edge2);
    }
}
