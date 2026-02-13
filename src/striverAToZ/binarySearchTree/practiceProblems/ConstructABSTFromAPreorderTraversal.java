package striverAToZ.binarySearchTree.practiceProblems;

public class ConstructABSTFromAPreorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

//        TreeNode TreeNode = lca(root, 5, 8);
        int[] pre = {10, 5, 1, 8, 15, 17};
        int size = pre.length;
        TreeNode node = post_order(pre, size);
//        System.out.println(TreeNode.val);
        printList(node);
    }

    static TreeNode post_order(int pre[], int size) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 1; i < size; i++) {
            helper(root, pre[i]);
        }
        return root;
    }

    private static TreeNode helper(TreeNode root, int k) {
        if (root == null) {
            return new TreeNode(k);
        }
        if (root.val < k) {
            root.right = helper(root.right, k);
        } else {
            root.left = helper(root.left, k);
        }
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
