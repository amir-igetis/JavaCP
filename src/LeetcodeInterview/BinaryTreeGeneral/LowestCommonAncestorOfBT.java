package LeetcodeInterview.BinaryTreeGeneral;

public class LowestCommonAncestorOfBT {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(1);

        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);

        TreeNode ans = lowestCommonAncestor(root, p, q);
        System.out.println(ans.val);
    }

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            return root;

//        return (left != null) ? left : right;

        if (left != null) return left;
        else return right;
    }

}
