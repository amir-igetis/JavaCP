package striverAToZ.binarySearchTree.practiceProblems;

public class FloorInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);
        System.out.println(findFloor(root, 9));
    }

    static int findFloor(TreeNode root, int key) {
        int floor = Integer.MAX_VALUE;
        while (root != null) {
            if (root.val == key) {
                floor = root.val;
                return floor;
            } else if (root.val > key) {
                root = root.left;
            } else {
                floor = root.val;
                root = root.right;
            }
        }
        return floor;
    }
}
