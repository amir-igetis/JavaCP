package striverAToZ.recursion.hadQuestions;

import java.util.Arrays;

public class MColoringProb {

    /// Problem Statement: Given an undirected graph and a number m, determine if the graph can be colored with at most m colors such that no two adjacent vertices of the graph are colored with the same color.

    public static void main(String[] args) {
        int N = 4;  // Number of nodes
        int m = 3;  // Maximum number of colors

        // Create a sample graph with edges (0,1), (1,2), (2,3), (3,0), (0,2)
        boolean[][] graph = new boolean[101][101];
        graph[0][1] = graph[1][0] = true;
        graph[1][2] = graph[2][1] = true;
        graph[2][3] = graph[3][2] = true;
        graph[3][0] = graph[0][3] = true;
        graph[0][2] = graph[2][0] = true;

        // Output if the graph can be colored with at most m colors
        System.out.println(graphColoring(graph, m, N));

    }

    /// Time Complexity: O(m^N), where m is the number of colors and N is the number of nodes in the graph. This is because we try to color each node with m different colors, leading to an exponential time complexity in the worst case.
    ///
    /// Space Complexity: O(N), where N is the number of nodes in the graph. This is due to the recursion stack and the color array used to store the colors assigned to each node.
    static boolean graphColoring(boolean[][] graph, int m, int N) {
        int[] color = new int[N];
        Arrays.fill(color, 0);
        // Start solving from node 0
        if (solve(0, color, m, N, graph)) return true;
        return false;
    }

    private static boolean isSafe(int node, int[] color, boolean[][] graph, int n, int col) {
        for (int k = 0; k < n; k++) {
            // Check if adjacent node has the same color
            if (k != node && graph[k][node] && color[k] == col) {
                return false;
            }
        }
        return true;  // Safe to assign the color
    }

    // Recursive function to solve the coloring problem
    private static boolean solve(int node, int[] color, int m, int N, boolean[][] graph) {
        // If all nodes are assigned colors, return true
        if (node == N) {
            return true;
        }

        // Try different colors for the node
        for (int i = 1; i <= m; i++) {
            if (isSafe(node, color, graph, N, i)) {
                color[node] = i;
                // Recursively check for the next node
                if (solve(node + 1, color, m, N, graph)) return true;
                color[node] = 0;  // Backtrack if the color assignment fails
            }
        }
        return false;  // If no solution is found
    }
}