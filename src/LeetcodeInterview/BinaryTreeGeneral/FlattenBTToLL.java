package LeetcodeInterview.BinaryTreeGeneral;

public class FlattenBTToLL {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.right = new TreeNode(7);

        flatten(root);
        System.out.println(root.val);

    }

    static void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode rightMost = curr.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = curr.right;

                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

//    private static void printList(TreeNode root) {
//        if (root == null)
//    }
}
