package LeetcodeInterview.BinaryTreeGeneral;

import java.util.Stack;

public class PathSum {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(1);

        int targetSum = 1;

        System.out.println();
    }

    static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && targetSum == root.val) {
            return true;
        }

        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    static boolean hasPathSumI(TreeNode root, int targetSum) {
        if (root == null) return false;
        Stack<TreeNode> nodeSt = new Stack<>();
        Stack<Integer> sumSt = new Stack<>();

        nodeSt.push(root);
        sumSt.push(targetSum - root.val);

        while (!nodeSt.isEmpty()) {
            TreeNode node = nodeSt.pop();
            int currSum = sumSt.pop();
            if (node.left == null && node.right == null && currSum == 0)
                return true;

            if (node.right != null) {
                nodeSt.push(node.right);
                sumSt.push(currSum - node.right.val);
            }

            if (node.left != null) {
                nodeSt.push(node.left);
                sumSt.push(currSum - node.left.val);
            }
        }

        return false;
    }
}
