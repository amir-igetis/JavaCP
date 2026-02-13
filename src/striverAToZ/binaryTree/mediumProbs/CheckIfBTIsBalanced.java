package striverAToZ.binaryTree.mediumProbs;

public class CheckIfBTIsBalanced {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(7);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(17);

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(5);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(17);

        System.out.println(isBalanced(root));
    }

    static boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    static int dfsHeight(TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = dfsHeight(root.left);
        int rightHeight = dfsHeight(root.right);

        if (leftHeight == -1 || rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
