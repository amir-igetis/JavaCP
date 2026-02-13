package LeetcodeInterview.BinaryTreeGeneral;

import java.util.Stack;

public class SumRootToLeafNums {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(1);

        System.out.println();
    }

    static int sumNumbers(TreeNode root) {
        return dfsHelper(root, 0);
    }

    private static int dfsHelper(TreeNode node, int currSum) {
        if (node == null) return 0;
        currSum = currSum * 10 + node.val;
        if (node.left == null && node.right == null)
            return currSum;

        return dfsHelper(node.left, currSum)
                + dfsHelper(node.right, currSum);
    }

    static int sumNumbersI(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Stack<TreeNode> nodeSt = new Stack<>();
        Stack<Integer> numSt = new Stack<>();

        nodeSt.push(root);
        numSt.push(root.val);
        while (!nodeSt.isEmpty()) {
            TreeNode node = nodeSt.pop();
            int currNum = numSt.pop();
            if (node.left == null && node.right == null) {
                sum += currNum;
            }
            if (node.right != null) {
                nodeSt.push(node.right);
                numSt.push(currNum * 10 + node.right.val);
            }
            if (node.left != null) {
                nodeSt.push(node.left);
                numSt.push(currNum * 10 + node.left.val);
            }
        }
        return sum;
    }
}
