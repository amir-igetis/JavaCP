package striverAToZ.binarySearchTree.practiceProblems;

public class InsertNodeInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

//        System.out.println(insert(root, 12));
        TreeNode node = insert(root, 12);
        printList(node);

    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }

    static TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }
        if (root.val == key)
            return root;

        if (root.val > key)
            root.left = insert(root.left, key);
        else root.right = insert(root.right, key);
        return root;
    }
}
