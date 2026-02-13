package striverAToZ.binarySearchTree.practiceProblems;

public class LargestBSTInBT {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

        System.out.println(largestBST(root));

    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/largest-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=largest-bst
    static int largestBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = largestBST(root.left);
        int right = largestBST(root.right);
        if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return left + right + 1;
        }
        return Math.max(left, right);
    }

    private static boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        boolean left = isBST(root.left, min, root.val);
        boolean right = isBST(root.right, root.val, max);
        return left && right;
    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }


}
