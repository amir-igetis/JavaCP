package striverAToZ.binarySearchTree.practiceProblems;

public class LCAInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

        TreeNode node = lca(root, 5, 8);
        System.out.println(node.val);
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=lowest-common-ancestor
    static TreeNode lca(TreeNode root, int n1, int n2) {
        TreeNode temp = root;
        if (root == null)
            return root;
        while (temp != null) {
            if (n1 < temp.val && n2 < temp.val)
                temp = temp.left;
            else if (n1 > temp.val && n2 > temp.val)
                temp = temp.right;
            else
                return temp;
        }
        return root;
    }

    // soln for
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > Math.max(p.val, q.val))
            return lowestCommonAncestor(root.left, p, q);
        if (root.val < Math.min(p.val, q.val))
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }
}
