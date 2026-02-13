package LeetcodeInterview.binaryTreeBFS;

import LeetcodeInterview.BinaryTreeGeneral.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLvlOrderTrav {
    public static void main(String[] args) {
//
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> ans = zigzagLevelOrderI(root);
        for (List<Integer> i : ans) {
            for (int j : i)
                System.out.print(j + ", ");
            System.out.println();
        }
    }

    static List<List<Integer>> zigzagLevelOrderI(TreeNode root) {
        List<List<Integer>> arr = new ArrayList<>();
        if (root == null) return arr;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int levelNum = q.size();
            List<Integer> subList = new ArrayList<>();

            for (int i = 0; i < levelNum; i++) {
                if (q.peek().left != null)
                    q.offer(q.peek().left);
                if (q.peek().right != null)
                    q.offer(q.peek().right);
                if (flag)
                    subList.add(q.poll().val);
                else subList.add(0, q.poll().val);
            }
            flag = !flag;
            arr.add(subList);
        }
        return arr;
    }
}
