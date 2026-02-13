package LeetcodeInterview.binarySearchTree;

import LeetcodeInterview.BinaryTreeGeneral.TreeNode;

import java.util.Stack;

public class KthSmallestElementInABst {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);
        int k = 1;

        int ans = kthSmallestI(root, k);
        System.out.println(ans);
    }

    private int count = 0;
    private int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null) return;

        inorder(node.left, k); // Left subtree (smaller elements)

        count++; // Process current node
        if (count == k) {
            result = node.val;
            return;
        }

        inorder(node.right, k);
    }

    static int kthSmallestI(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            count++;
            if (count == k) return current.val;

            current = current.right;
        }

        return -1;
    }
}
