package LeetcodeInterview.BinaryTreeGeneral;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(1);

        System.out.println(isSymmetric(root));
    }

    static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        return isSymmetricHelper(root.left, root.right);
    }

    private static boolean isSymmetricHelper(TreeNode root1, TreeNode root2) {
//        if (root1 == null || root2 == null)
//            return root1 == root2;
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        return (root1.val == root2.val) &&
                isSymmetricHelper(root1.left, root2.right)
                && isSymmetricHelper(root1.right, root2.left);
    }

    static boolean isSymmetricI(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }
}
