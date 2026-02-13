package LeetcodeInterview.BinaryTreeGeneral;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorder {
    public static void main(String[] args) {
//
        int[] preorder = {3, 9, 20, 15, 7}, inorder = {9, 3, 15, 20, 7};

        TreeNode ans = buildTree(preorder, inorder);
        System.out.println(ans.val);
    }

    private static HashMap<Integer, Integer> inorderMap;
    private static int preorderIndex = 0;

    static TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return constructTree(preorder, 0, inorder.length - 1);
    }

    private static TreeNode constructTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderMap.get(rootVal);
        root.left = constructTree(preorder, left, inorderIndex - 1);
        root.right = constructTree(preorder, inorderIndex + 1, right);

        return root;
    }


    private static int preorderIndex1;

    // A map to store the value -> index mapping for the inorder array.
    // This allows for O(1) lookup of an element's position in inorder.
    private static Map<Integer, Integer> inorderMap1;

    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        // Initialize the preorder index to 0, as the first element is the root of the entire tree.
        preorderIndex1 = 0;

        // Build the inorder map for quick lookups.
        inorderMap1 = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap1.put(inorder[i], i);
        }

        // Start the recursive building process.
        // The initial inorder range covers the entire inorder array.
        return build1(preorder, 0, inorder.length - 1);
    }

    /**
     * Recursive helper function to build the tree.
     *
     * @param preorder     The preorder traversal array.
     * @param inorderStart The starting index of the current subtree in the inorder array.
     * @param inorderEnd   The ending index of the current subtree in the inorder array.
     * @return The root of the constructed subtree.
     */
    private static TreeNode build1(int[] preorder, int inorderStart, int inorderEnd) {
        // Base case: If the inorder segment is empty or invalid, return null.
        // This signifies that there's no subtree to build.
        if (inorderStart > inorderEnd) {
            return null;
        }

        // The current root's value is taken from the preorder array at preorderIndex,
        // and then we increment preorderIndex for the next recursive call.
        int rootVal = preorder[preorderIndex1];
        preorderIndex1++;
        TreeNode root = new TreeNode(rootVal);

        // Find the root's index in the inorder traversal.
        // This index divides the inorder array into left and right subtrees.
        int rootInInorderIndex = inorderMap.get(rootVal);

        // Recursively build the left subtree.
        // The inorder range for the left subtree is from inorderStart to rootInInorderIndex - 1.
        root.left = build1(preorder, inorderStart, rootInInorderIndex - 1);

        // Recursively build the right subtree.
        // The inorder range for the right subtree is from rootInInorderIndex + 1 to inorderEnd.
        root.right = build1(preorder, rootInInorderIndex + 1, inorderEnd);

        // Return the constructed root of the current subtree.
        return root;
    }
}
