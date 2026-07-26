package striverAToZ.graphs.mstOrDisjointSet;

import java.util.*;

public class AccountsMerge {
    public static void main(String[] args) {
        // Input accounts
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "j1@com", "j2@com", "j3@com"));
        accounts.add(Arrays.asList("John", "j4@com"));
        accounts.add(Arrays.asList("Raj", "r1@com", "r2@com"));
        accounts.add(Arrays.asList("John", "j1@com", "j5@com"));
        accounts.add(Arrays.asList("Raj", "r2@com", "r3@com"));
        accounts.add(Arrays.asList("Mary", "m1@com"));

        // Call function
        List<List<String>> ans = accountsMerge(accounts);

        // Print output
        for (List<String> acc : ans) {
            System.out.print(acc.get(0) + ":");
            for (int i = 1; i < acc.size(); i++) {
                System.out.print(acc.get(i) + " ");
            }
            System.out.println();
        }
    }

    /// tc O(N+E) + O(E*4ɑ) + O(N*(ElogE + E)) where N = no. of indices or nodes and E = no. of emails, sc O(N)+ O(N) +O(2N) ~ O(N) where N = no. of nodes/indices
    static List<List<String>> accountsMerge(List<List<String>> details) {
        // Number of accounts
        int n = details.size();

        // Create Disjoint Set
        DisjointSet ds = new DisjointSet(n);

        // Map to store email -> account index
        Map<String, Integer> mapMailNode = new HashMap<>();

        // Step 1: Union accounts having common emails
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < details.get(i).size(); j++) {
                String mail = details.get(i).get(j);

                if (!mapMailNode.containsKey(mail)) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        // Step 2: Group emails under ultimate parent
        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++)
            mergedMail[i] = new ArrayList<>();

        for (Map.Entry<String, Integer> it :
                mapMailNode.entrySet()) {
            String mail = it.getKey();
            int node = ds.findUPar(it.getValue());
            mergedMail[node].add(mail);
        }

        // Step 3: Prepare final merged result
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].isEmpty()) continue;

            Collections.sort(mergedMail[i]);

            List<String> temp = new ArrayList<>();
            temp.add(details.get(i).get(0));

            for (String mail : mergedMail[i]) {
                temp.add(mail);
            }
            ans.add(temp);
        }

        // Sort final answer
        ans.sort((a, b) -> a.get(0).compareTo(b.get(0)));
        return ans;
    }

    /// Disjoint Set (Union-Find) implementation
    private static class DisjointSet {
        int[] rank;
        int[] parent;
        int[] size;

        // Constructor to initialize DSU
        DisjointSet(int n) {
            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];

            // Initialize every node
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // Function to find ultimate parent with path compression
        int findUPar(int node) {
            if (node == parent[node])
                return node;
            parent[node] = findUPar(parent[node]);
            return parent[node];
        }

        // Function to perform union by rank
        void unionByRank(int u, int v) {
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else if (rank[ulp_v] < rank[ulp_u]) {
                parent[ulp_v] = ulp_u;
            } else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }

        // Function to perform union by size
        void unionBySize(int u, int v) {
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }
}
