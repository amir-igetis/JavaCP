package LeetcodeInterview.binaryTreeBFS;

import LeetcodeInterview.BinaryTreeGeneral.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        List<Integer> ans = rightSideView(root);
        for (Integer i : ans)
            System.out.print(i + ", ");
        System.out.println();
    }

    static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode rightMost = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                rightMost = node;
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            res.add(rightMost.val);
        }
        return res;
    }
}
