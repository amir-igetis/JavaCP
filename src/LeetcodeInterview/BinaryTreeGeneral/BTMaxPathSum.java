package LeetcodeInterview.BinaryTreeGeneral;

public class BTMaxPathSum {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(1);

        int ans = maxPathSum(root);

        System.out.println(ans);
    }

    private static int maxSumII;

    static int maxPathSum(TreeNode root) {
        maxSumII = Integer.MIN_VALUE;
        maxPathSumHelperIII(root);
        return maxSumII;
    }

    private static int maxPathSumHelperIII(TreeNode node) {
        if (node == null) return 0; // Base case

        int leftSum = Math.max(maxPathSumHelperIII(node.left), 0);
        int rightSum = Math.max(maxPathSumHelperIII(node.right), 0);

        maxSumII = Math.max(maxSumII, node.val + leftSum + rightSum);

        return node.val + Math.max(leftSum, rightSum);
    }
}
