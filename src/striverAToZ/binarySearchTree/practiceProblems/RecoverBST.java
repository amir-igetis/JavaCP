package striverAToZ.binarySearchTree.practiceProblems;

public class RecoverBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);


        printList(root);
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/fixed-two-TreeNodes-of-a-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=fixed-two-TreeNodes-of-a-bst
    private static TreeNode first;
    private static TreeNode middle;
    private static TreeNode last;
    private static TreeNode prev; // prev var to store previous nod

    private void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        if (prev != null && (root.val < prev.val)) // first voilation
        {
            if (first == null) { // if this is first voilation then change fisrt and middle
                first = prev;
                middle = root;
            } else { // if this is 2nd voilation then change last
                last = root;
            }
        }
        prev = root;
        inorder(root.right);
    }

    // Function to fix a given BST where two TreeNodes are swapped.
    public TreeNode correctBST(TreeNode root) {
        TreeNode dummy = root;
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);

        inorder(root);

        if (last != null) { // exchange first and last
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else { // exchange first and middle
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
        return dummy;
    }

    // using morris
    // soln for https://leetcode.com/problems/recover-binary-search-tree/
    public void recoverTree(TreeNode root) {
        TreeNode pred = null;
        TreeNode x = null; // 1st wrong TreeNode
        TreeNode y = null; // 2nd wrong TreeNode

        while (root != null) {
            if (root.left == null) {
                // Start the main logic
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null)
                        x = pred;
                }
                pred = root;
                // End of the main logic
                root = root.right;
            } else {
                TreeNode morrisPred = findPredecessor(root);
                if (morrisPred.right == null) {
                    morrisPred.right = root; // Connect it!
                    root = root.left;
                } else { // Already connected before
                    // Start the main logic
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null)
                            x = pred;
                    }
                    pred = root;
                    // End of the main logic
                    morrisPred.right = null; // Break the connection
                    root = root.right;
                }
            }
        }

        swap(x, y);
    }

    private TreeNode findPredecessor(TreeNode root) {
        TreeNode pred = root.left;
        while (pred.right != null && pred.right != root)
            pred = pred.right;
        return pred;
    }

    private void swap(TreeNode x, TreeNode y) {
        final int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }
}
