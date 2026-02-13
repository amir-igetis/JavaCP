package striverAToZ.binaryTree.hardProbs;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTFromInorderAndPreorder {
    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] preorder = {3, 9, 20, 15, 7};

        TreeNode root = buildTree(preorder, inorder);

        System.out.println("Inorder of Unique Binary Tree Created:");
        printInorder(root);

    }

    static TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map to store value -> index from inorder
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        // Start the recursive construction
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    // Helper method
    private static TreeNode build(int[] preorder, int preStart, int preEnd,
                                  int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // Base condition
        if (preStart > preEnd || inStart > inEnd) return null;

        // First element of preorder is the root
        TreeNode root = new TreeNode(preorder[preStart]);

        // Get inorder index of root
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        // Build left and right subtrees
        root.left = build(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);
        root.right = build(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);

        return root;
    }

    // Method to print inorder traversal
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    } // tc and sc O(N)


}
