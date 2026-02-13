package LeetcodeInterview.BinaryTreeGeneral;

public class CountCompleteTreeNodes {
    public static void main(String[] args) {
//
    }

    //    recursion
    static int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int countNodesI(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) + countNodesI(root.right);
        } else {
            return (1 << rightHeight) + countNodesI(root.left);
        }
    }

    private static int getHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }
}
