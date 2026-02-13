package leetcodeDiscussProbPatterns.dailyQuestions;

public class BalancedBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(isBalanced(root));
    }

    static boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != 1;
    }

    private static int dfsHeight(TreeNode root) {
        if (root == null)
            return 0;
        int lh = dfsHeight(root.left);
        int rh = dfsHeight(root.right);
        if (lh == -1 || rh == -1)
            return -1;
        if (Math.abs(lh - rh) > 1)
            return -1;

        return Math.max(lh, rh) + 1;
    }
}
