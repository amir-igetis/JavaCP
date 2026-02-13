package striverAToZ.binaryTree.mediumProbs;

public class DiameterOfBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(17);

        System.out.println(diameterBT(root));
    }


    static int diameterBT(TreeNode root) {
        int[] diameter = new int[1];
        height(root, diameter);
        return diameter[0];
    }

    // soln for https://leetcode.com/problems/diameter-of-binary-tree/description/
    // soln for
    // https://practice.geeksforgeeks.org/problems/diameter-of-binary-tree/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=diameter-of-binary-tree
    private static int height(TreeNode node, int[] diameter) {
        if (node == null) {
            return 0;
        }
        int lh = height(node.left, diameter);
        int rh = height(node.right, diameter);
        // for gfg add write this line insted of usual Math.max line
        diameter[0] = Math.max(diameter[0], lh + rh);
        // use below line in leetcode
        // diameter[0] = Math.max(diameter[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    // anoter way to do the question
    static int maxDia = 5;

    static int diameterBTI(TreeNode root) {
        maxDia = 0;
        helper(root);
        return maxDia;
    }

    private static int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = helper(node.left);
        int rh = helper(node.right);
        maxDia = Math.max(maxDia, 1 + lh + rh);
        return 1 + Math.max(lh, rh);
    }
}
