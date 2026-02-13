package graphPrac;

import java.util.ArrayList;

public class DisjointSet {
    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) return node;

        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ulpU = findUPar(u);
        int ulpV = findUPar(v);
        if (ulpU == ulpV) return;
        if (rank.get(ulpU) < rank.get(ulpV))
            parent.set(ulpU, ulpV);
        else if (rank.get(ulpV) < rank.get(ulpU))
            parent.set(ulpV, ulpU);
        else {
            parent.set(ulpV, ulpU);
            int rankU = rank.get(ulpU);
            rank.set(ulpU, rankU + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ulpU = findUPar(u);
        int ulpV = findUPar(v);
        if (ulpU == ulpV) return;
        if (size.get(ulpU) < size.get(ulpV)) {
            parent.set(ulpU, ulpV);
            size.set(ulpV, size.get(ulpV) + size.get(ulpU));
        } else {
            parent.set(ulpV, ulpU);
            size.set(ulpU, size.get(ulpU) + size.get(ulpV));
        }
    }
}

//class Main {
//    public static void main (String[] args) {
//        DisjointSet ds = new DisjointSet(7);
//        ds.unionByRank(1, 2);
//        ds.unionByRank(2, 3);
//        ds.unionByRank(4, 5);
//        ds.unionByRank(6, 7);
//        ds.unionByRank(5, 6);
//
//        // if 3 and 7 same or not
//        if (ds.findUPar(3) == ds.findUPar(7)) {
//            System.out.println("Same");
//        } else
//            System.out.println("Not Same");
//
//        ds.unionByRank(3, 7);
//        if (ds.findUPar(3) == ds.findUPar(7)) {
//            System.out.println("Same");
//        } else
//            System.out.println("Not Same");
//    }
//}
