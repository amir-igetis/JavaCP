package striverAToZ.binaryTree.hardProbs;

public class CountTotalNodesInACompleteBT {
    public static void main(String[] args) {
        // Create binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);


        // Get total number of nodes
        int totalNodes = countNodes(root);

        // Output the result
        System.out.println("Total number of nodes in the Complete Binary Tree: " + totalNodes);
    }

    // Brute force

    // Helper function to count nodes using inorder traversal
    static void inorder(TreeNode root, int[] count) {
        // If current node is null, stop recursion
        if (root == null) {
            return;
        }
        // Increment count for current node
        count[0]++;
        // Recursively count nodes in left subtree
        inorder(root.left, count);
        // Recursively count nodes in right subtree
        inorder(root.right, count);
    }

    // Function to count total number of nodes
    private static int countNodes(TreeNode root) {
        // If tree is empty, return 0
        if (root == null) {
            return 0;
        }
        // Array used to pass count by reference
        int[] count = new int[1];
        // Perform inorder traversal to count nodes
        inorder(root, count);
        // Return the count value
        return count[0];
    } // tc & sc O(n)

    ///  striver code

    static int countNodesI(TreeNode root) {
        // If tree is empty, return 0
        if (root == null) {
            return 0;
        }
        // Get left height
        int lh = findHeightLeftI(root);
        // Get right height
        int rh = findHeightRightI(root);
        // If heights match, use perfect binary tree formula
        if (lh == rh) {
            return (1 << lh) - 1;
        }
        // Otherwise, recursively count left and right subtrees
        return 1 + countNodesI(root.left) + countNodesI(root.right);
    }

    // Helper to find height from leftmost path
    private static int findHeightLeftI(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    // Helper to find height from rightmost path
    private static int findHeightRightI(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}

