package striverAToZ.binaryTree.hardProbs;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTFromPostorderAndInorder {
    public static void main(String[] args) {

        int[] inorder = {40, 20, 50, 10, 60, 30};
        int[] postorder = {40, 50, 20, 60, 30, 10};

        System.out.print("Inorder Array: ");
        for (int x : inorder) System.out.print(x + " ");

        System.out.print("\nPostorder Array: ");
        for (int x : postorder) System.out.print(x + " ");
        System.out.println();

        TreeNode root = buildTree(inorder, postorder);

        System.out.println("Inorder of Constructed Tree:");
        printInorder(root);
    }

    //    tc and sc O(N)
    static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length)
            return null;

        // Map value -> index in inorder
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return build(
                inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1,
                indexMap
        );
    }

    private static TreeNode build(
            int[] inorder, int is, int ie,
            int[] postorder, int ps, int pe,
            Map<Integer, Integer> indexMap
    ) {
        if (ps > pe || is > ie)
            return null;

        // Last element in postorder is root
        TreeNode root = new TreeNode(postorder[pe]);

        int inRoot = indexMap.get(postorder[pe]);
        int numsLeft = inRoot - is;

        // Build left subtree
        root.left = build(
                inorder, is, inRoot - 1,
                postorder, ps, ps + numsLeft - 1,
                indexMap
        );

        // Build right subtree
        root.right = build(
                inorder, inRoot + 1, ie,
                postorder, ps + numsLeft, pe - 1,
                indexMap
        );

        return root;
    }

    // Inorder printer
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }


}
