package striverAToZ.binaryTree.mediumProbs;

public class CheckTwoTreeIdentical {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(17);

        System.out.println(isIdentical(root, root));
    }

    static boolean isIdentical(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        return ((node1.val == node2.val) &&
                isIdentical(node1.left, node2.left)
                && isIdentical(node1.right, node2.right));
    }
}
