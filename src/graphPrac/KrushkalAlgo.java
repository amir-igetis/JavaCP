package graphPrac;

import java.util.ArrayList;
import java.util.Arrays;

public class KrushkalAlgo {

    public static void main(String[] args) {
        int v = 6;
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < v; v++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(new int[]{1,2,2});

    }

    static int spanningTree(int v, ArrayList<ArrayList<int[]>> adj) {
        return 1;
    }
}
