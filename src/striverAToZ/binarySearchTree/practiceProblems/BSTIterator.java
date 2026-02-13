package striverAToZ.binarySearchTree.practiceProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BSTIterator {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

        BSTIterator(root);
        printList(root);
    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }

    // soln for https://leetcode.com/problems/binary-search-tree-iterator/solutions/
    private static Stack<TreeNode> stack = new Stack<TreeNode>();

    static void BSTIterator(TreeNode root) {
        pushAll(root);
    }

    /**
     * return whether we have a next smallest number
     */
    private static boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * return the next smallest number
     */
    private static int next() {
        TreeNode tmpTreeNode = stack.pop();
        pushAll(tmpTreeNode.right);
        return tmpTreeNode.val;
    }

    private static void pushAll(TreeNode TreeNode) {
        for (; TreeNode != null; stack.push(TreeNode), TreeNode = TreeNode.left)
            ;
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/merge-two-bst-s/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=merge-two-bst-s
    static List<Integer> merge(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        TreeNode r1 = root1, r2 = root2;
        helper(r1, s1);
        helper(r2, s2);
        TreeNode curr = null;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (s1.peek().val < s2.peek().val) {
                curr = s1.pop();
                ans.add(curr.val);
                if (curr.right != null) {
                    helper(curr.right, s1);
                }
            } else if (s1.peek().val > s2.peek().val) {
                curr = s2.pop();
                ans.add(curr.val);
                if (curr.right != null) {
                    helper(curr.right, s2);
                }
            } else {
                curr = s1.pop();
                ans.add(curr.val);
                if (curr.right != null) {
                    helper(curr.right, s1);
                }
                curr = s2.pop();
                ans.add(curr.val);
                if (curr.right != null) {
                    helper(curr.right, s2);
                }
            }
        }

        curr = null;
        while (!s1.isEmpty()) {
            curr = s1.pop();
            ans.add(curr.val);
            if (curr.right != null)
                helper(curr.right, s1);

        }
        while (!s2.isEmpty()) {
            curr = s2.pop();
            ans.add(curr.val);
            if (curr.right != null)
                helper(curr.right, s2);

        }
        return ans;
    }

    private static void helper(TreeNode root, Stack<TreeNode> st) {
        TreeNode curr = root;
        while (curr != null) {
            st.push(curr);
            curr = curr.left;
        }
    }

}
