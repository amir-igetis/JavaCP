package striverAToZ.binarySearchTree.practiceProblems;

public class CeilInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);
        System.out.println(findCeil(root, 9));
    }

    static int findCeil(TreeNode root, int key) {
        int ceil = Integer.MIN_VALUE;
//        int ceil = -1;
        while (root != null) {
            if (root.val == key) {
                ceil = root.val;
                return ceil;
            }
            if (key > root.val)
                root = root.right;
            else {
                ceil = root.val;
                root = root.left;
            }
        }
        return ceil;
    }
}
