package striverAToZ.graphs.topoSortProbs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {

    /// Problem Statement: Determine the order of characters in an alien language given a sorted list of words from its dictionary.

    public static void main(String[] args) {
        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};

        String ans = findOrder(dict, N, K);

        for (char ch : ans.toCharArray()) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }

    //    O(N*len)+O(K+E), where N is the number of words in the dictionary, ‘len’ is the length up to the index where the first inequality occurs, K = no. of nodes, and E = no. of edges.
//    Space Complexity: O(K) + O(K)+O(K)+O(K) ~ O(4K), O(K)
    private static List<Integer> topoSort(int V, List<List<Integer>> adj) {
        // Array to keep track of indegree (number of incoming edges) of each node
        int[] indegree = new int[V];

        // Calculate indegree for each vertex
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Queue to store nodes with indegree = 0 (i.e., nodes that can be processed first)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // List to store topological order
        List<Integer> topo = new ArrayList<>();

        // Process the queue
        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node); // Add node to result

            // Decrease indegree of its neighbors
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        return topo;
    }

    // Function to find the order of characters in the alien dictionary
    static String findOrder(String[] dict, int N, int K) {
        // Adjacency list for graph representation
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph by comparing adjacent words in dictionary
        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());

            for (int ptr = 0; ptr < len; ptr++) {
                if (s1.charAt(ptr) != s2.charAt(ptr)) {
                    // Add edge s1[ptr] -> s2[ptr]
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        // Perform topological sort
        List<Integer> topo = topoSort(K, adj);

        // Convert numeric representation back to characters
        StringBuilder ans = new StringBuilder();
        for (int node : topo) {
            ans.append((char) (node + 'a'));
        }

        return ans.toString();
    }


    /// ans to
    /// https://www.geeksforgeeks.org/problems/alien-dictionary/1
    static String findOrderI(String[] words) {

        // Graph of 26 lowercase letters
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }

        boolean[] present = new boolean[26];
        int presentCount = 0;

        // Mark all characters that appear
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (!present[ch - 'a']) {
                    present[ch - 'a'] = true;
                    presentCount++;
                }
            }
        }

        // Build graph
        for (int i = 0; i < words.length - 1; i++) {

            String s1 = words[i];
            String s2 = words[i + 1];

            int len = Math.min(s1.length(), s2.length());

            boolean found = false;

            for (int j = 0; j < len; j++) {

                if (s1.charAt(j) != s2.charAt(j)) {

                    int u = s1.charAt(j) - 'a';
                    int v = s2.charAt(j) - 'a';

                    adj.get(u).add(v);
                    found = true;
                    break;
                }
            }

            // Invalid case: longer word comes before its prefix
            if (!found && s1.length() > s2.length()) {
                return "";
            }
        }

        ArrayList<Integer> topo = topoSortI(adj, present);

        // Cycle detected
        if (topo.size() != presentCount) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (int node : topo) {
            ans.append((char) (node + 'a'));
        }

        return ans.toString();
    }

    private static ArrayList<Integer> topoSortI(ArrayList<ArrayList<Integer>> adj,
                                                boolean[] present) {

        int[] indegree = new int[26];

        for (int i = 0; i < 26; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < 26; i++) {
            if (present[i] && indegree[i] == 0) {
                q.offer(i);
            }
        }

        ArrayList<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {

            int node = q.poll();
            topo.add(node);

            for (int it : adj.get(node)) {
                indegree[it]--;

                if (indegree[it] == 0) {
                    q.offer(it);
                }
            }
        }

        return topo;
    }
}
