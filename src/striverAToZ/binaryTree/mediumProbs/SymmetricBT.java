package striverAToZ.binaryTree.mediumProbs;

public class SymmetricBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(10);
        root.left.left.right = new TreeNode(5);

        TreeNode rootI = new TreeNode(1);
        rootI.left = new TreeNode(2);
        rootI.left.left = new TreeNode(4);
        rootI.left.right = new TreeNode(10);
        rootI.left.left.right = new TreeNode(5);

        System.out.println(isSymmetricMain(root));
    }

    static boolean isSymmetricMain(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return root1 == root2;
        return (root1.val == root2.val) &&
                isSymmetric(root1.left, root2.right)
                && isSymmetric(root1.right, root2.left);
    }


}
