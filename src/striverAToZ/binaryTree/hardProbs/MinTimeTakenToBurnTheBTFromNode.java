package striverAToZ.binaryTree.hardProbs;

import java.util.*;

public class MinTimeTakenToBurnTheBTFromNode {
    public static void main(String[] args) {
        // Build the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.right = new TreeNode(7);

        // Create object of Solution class

        // Define the target node to start burning
        int target = 1;

        // Print the result
        System.out.println("Minimum time to burn the tree: " + minTime(root, target));
    }

    //Function to calculate minimum time to burn the entire tree from the target node
    public static int minTime(TreeNode root, int target) {
        // Map to build an undirected graph (adjacency list)
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Build graph from binary tree
        buildGraph(root, null, graph);

        // Set to track visited (burned) nodes
        Set<Integer> visited = new HashSet<>();

        // Queue for BFS starting from the target node
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);

        // Variable to count total time taken to burn all nodes
        int time = 0;

        // BFS level order traversal to simulate fire spread
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burned = false;

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                // Traverse all connected neighbors of the current node
                for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                        burned = true;
                    }
                }
            }

            // If new nodes got burned, increment time
            if (burned) {
                time++;
            }
        }

        // Return total time taken to burn the binary tree
        return time;
    }

    // Helper method to build an undirected graph from binary tree
    private static void buildGraph(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        // Base case: if current node is null, return
        if (node == null) return;

        // If parent exists, add bidirectional connection
        if (parent != null) {
            graph.computeIfAbsent(node.val, k -> new ArrayList<>()).add(parent.val);
            graph.computeIfAbsent(parent.val, k -> new ArrayList<>()).add(node.val);
        }

        // Recursively build graph for left and right children
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }
}


