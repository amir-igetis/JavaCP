package striverAToZ.binarySearchTree.practiceProblems;

public class CheckIfTreeIsBSTOrBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);
        System.out.println(isValidBST(root));
    }

    // soln for
    // soln for
    // https://practice.geeksforgeeks.org/problems/check-for-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=check-for-bst
    static boolean solve(TreeNode root, int min, int max) {
        if (root == null)
            return true;
        // every TreeNode's value should be in the range of (min,max) for the bst
        // if any TreeNode's value is greater than and equal to max value or less than and
        // equal to min value,then it is not bst
        if (root.val >= max || root.val <= min)
            return false;
        // Both the left and right subtrees must also be binary search trees
        // the left subtree of TreeNode's value is less than root's values
        boolean left = solve(root.left, min, root.val);
        // the right subtree of TreeNode's value is greater than root's value
        boolean right = solve(root.right, root.val, max);
        return (left && right);
    }

    static boolean isBST(TreeNode root) {
        // code here.
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        return solve(root, min, max);
    }

    // soln for
    // https://leetcode.com/problems/validate-binary-search-tree/description/
    static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode root, TreeNode minTreeNode, TreeNode maxTreeNode) {
        if (root == null)
            return true;
        if (minTreeNode != null && root.val <= minTreeNode.val)
            return false;
        if (maxTreeNode != null && root.val >= maxTreeNode.val)
            return false;

        return //
                isValidBST(root.left, minTreeNode, root) && //
                        isValidBST(root.right, root, maxTreeNode);

    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }
}
