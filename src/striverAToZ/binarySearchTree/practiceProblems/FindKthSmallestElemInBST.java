package striverAToZ.binarySearchTree.practiceProblems;

public class FindKthSmallestElemInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

//        System.out.println(insert(root, 12));
//        TreeNode node = delete(root, 15);
        int node = kthSmallest(root, 1);

//        printList(node);
        System.out.println(node);


    }

    static int ans = Integer.MIN_VALUE, count = 0;

    static int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        if (ans == Integer.MIN_VALUE)
            return -1;
        return ans;
    }

    private static void inorder(TreeNode root, int k) {
        if (root == null)
            return;
        inorder(root.left, k);
        count++;
        if (count == k) {
            ans = root.val;
            return;
        }
        inorder(root.right, k);
    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);

    }
}
