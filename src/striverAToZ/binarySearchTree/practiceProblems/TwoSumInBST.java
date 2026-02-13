package striverAToZ.binarySearchTree.practiceProblems;

import java.util.Stack;

public class TwoSumInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

//        findTarget(root, 17);
        System.out.println(findTarget(root, 13));

        printList(root);
    }


    private static class BSTIterator {

        private Stack<TreeNode> stack = new Stack<>();
        private boolean reverse;

        public BSTIterator(TreeNode root, boolean isReverse) {
            reverse = isReverse;
            pushAll(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode tmpNode = stack.pop();

            if (!reverse)
                pushAll(tmpNode.right);
            else
                pushAll(tmpNode.left);

            return tmpNode.val;
        }

        private void pushAll(TreeNode node) {
            while (node != null) {
                stack.push(node);
                if (reverse)
                    node = node.right;
                else
                    node = node.left;
            }
        }
    }

    static boolean findTarget(TreeNode root, int k) {

        if (root == null)
            return false;

        BSTIterator l = new BSTIterator(root, false); // smallest
        BSTIterator r = new BSTIterator(root, true);  // largest

        int i = l.next();
        int j = r.next();

        while (i < j) {
            int sum = i + j;

            if (sum == k)
                return true;
            else if (sum < k)
                i = l.next();
            else
                j = r.next();
        }

        return false;
    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }
}
