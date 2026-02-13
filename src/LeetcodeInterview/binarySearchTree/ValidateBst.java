package LeetcodeInterview.binarySearchTree;

import LeetcodeInterview.BinaryTreeGeneral.TreeNode;

import java.util.Stack;

public class ValidateBst {

    public static void main(String[] args) {
//

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(isValidBST(root));
    }

    static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return validate(node.left, min, node.val)
                && validate(node.right, node.val, max);
    }

    static boolean isValidBSTI(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        Integer prev = null;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if (prev != null && current.val <= prev)
                return false;
            prev = current.val;

            current = current.right;
        }

        return true;
    }
}
