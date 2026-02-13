package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxProductOfSplittedBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        System.out.println(maxProduct(root));
    }

    private static long totalSum = 0;
    private static long maxProduct = 0;
    private static final int MOD = (int) 1e9 + 7;

    static int maxProduct(TreeNode root) {
        totalSum = getTotalSum(root);
        getSubtreeSum(root);
        return (int) (maxProduct % MOD);
    }

    private static long getSubtreeSum(TreeNode node) {
        if (node == null) return 0;
        long left = getSubtreeSum(node.left);
        long right = getSubtreeSum(node.right);
        long subSum = node.val + left + right;
        long product = subSum * (totalSum - subSum);
        maxProduct = Math.max(maxProduct, product);
        return subSum;
    }

    private static long getTotalSum(TreeNode node) {
        if (node == null)
            return 0;
        return node.val + getTotalSum(node.left)
                + getTotalSum(node.right);
    }
}
