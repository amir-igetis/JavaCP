package striverAToZ.binaryTree.mediumProbs;

import striverAToZ.binaryTree.traversals.IntroToTrees;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(17);

        System.out.println(maxDepthRecI(root));
    }

    static int maxDepthRec(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepthRec(root.left);
        int right = maxDepthRec(root.right);
        return Math.max(left, right) + 1;
    }

    static int maxDepthRecI(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.peek();
                q.poll();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            level++;
        }
        return level;
    }
}
