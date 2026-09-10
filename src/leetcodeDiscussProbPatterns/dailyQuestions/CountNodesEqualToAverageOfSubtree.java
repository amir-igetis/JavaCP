package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountNodesEqualToAverageOfSubtree {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(8);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(1);
        head.right = new TreeNode(5);
        head.right.right = new TreeNode(6);

        System.out.println(averageOfSubtree(head)); // Expected output: 5
    }


    // DFS
    /// Here, N is the number of nodes in the binary tree.
    ///
    /// Time complexity O(N)
    ///
    /// We need to iterate over each node in the binary tree only once, and all other operations, like finding the average, are O(1), and hence the total time complexity is equal to O(N).
    ///
    /// Space complexity O(N)
    ///
    /// Recursion requires some stack space, and the maximum number of active stack calls would be equal to N (one for each node). The space required by the pair is O(1) and hence the total space complexity is equal to O(N).
    static int count = 0;

    static int averageOfSubtree(TreeNode root) {
        count = 0; // Reset count here so it works across multiple test cases
        postOrder(root);
        return count;
    }

    /**
     * DFS Traversal
     * Returns an int array where:
     * index 0 = sum of nodes in the subtree
     * index 1 = number of nodes in the subtree
     */
    private static int[] postOrder(TreeNode root) {
        // Base case: null node has 0 sum and 0 count
        if (root == null) {
            return new int[]{0, 0};
        }

        // First iterate over left and right subtrees.
        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);

        // Calculate the sum and count for the current subtree
        int nodeSum = left[0] + right[0] + root.val;
        int nodeCount = left[1] + right[1] + 1;

        // Check if the average of the subtree is equal to the node value.
        // We use integer division as per the problem description.
        if (root.val == (nodeSum / nodeCount)) {
            count++;
        }

        // Return the sum of nodes and the count in the subtree.
        return new int[]{nodeSum, nodeCount};
    }
}