package striverAToZ.binaryTree.mediumProbs;

public class MaxPathSum {
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
        root.left.left = new TreeNode(5);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(17);

        System.out.println(maxPathSum(root));
    }

    static int maxPathSum(TreeNode root) {
        int[] maxVal = new int[1];
        maxVal[0] = Integer.MIN_VALUE;
        maxPathDown(root, maxVal);
        return maxVal[0];
    }

    static int maxPathDown(TreeNode root, int[] maxValue) {
        if (root == null)
            return 0;
        int lf = Math.max(0, maxPathDown(root.left, maxValue));
        int rh = Math.max(0, maxPathDown(root.right, maxValue));
        maxValue[0] = Math.max(maxValue[0], lf + rh + root.val);
        return Math.max(lf, rh) + root.val;
    }
}
