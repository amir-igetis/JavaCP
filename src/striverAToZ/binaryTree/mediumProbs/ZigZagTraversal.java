package striverAToZ.binaryTree.mediumProbs;

import java.util.*;

public class ZigZagTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(17);

        List<List<Integer>> ans = zigZagLevelOrder(root);
        for (List<Integer> i : ans) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();

        }

        List<Integer> res = zigZagLevelOrderI(root);
        for (int i : res)
            System.out.print(i + " ");

        System.out.println();
    }

    static List<List<Integer>> zigZagLevelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> arr = new ArrayList<>();

        if (root == null) return arr;
        q.offer(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int levelNum = q.size();
            List<Integer> subList = new ArrayList<>();

            for (int i = 0; i < levelNum; i++) {
                // int index = 1;
                TreeNode node = q.poll();
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
                if (flag)
                    subList.add(node.val);
                else subList.add(0, node.val);
            }
            flag = !flag;
            arr.add(subList);
        }
        return arr;
    }

    // soln for https://practice.geeksforgeeks.org/problems/zigzag-tree-traversal/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=zigzag-tree-traversal
    static List<Integer> zigZagLevelOrderI(TreeNode root) {
        //Add your code here.
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        int level = 1;
        q.add(root);

        while (!q.isEmpty()) {
            int s = q.size();
            ArrayList<Integer> a = new ArrayList<>();
            for (int i = 0; i < s; i++) {
                TreeNode temp = q.remove();
                a.add(temp.val);
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            if (level % 2 == 0) {
                Collections.reverse(a);
                ans.addAll(a);
            } else {
                ans.addAll(a);
            }
            level++;
        }
        return ans;
    }

    // another soln for gfg question
    static List<Integer> zigZagLevelOrderII(TreeNode root) {
        //Add your code here.
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> wrapList = new ArrayList<>();
        if (root == null) return wrapList;
        q.offer(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int levelNum = q.size();
            Stack<Integer> st = new Stack<>();
            while (levelNum > 0) {
                TreeNode temp = q.poll();
                if (flag) {
                    wrapList.add(temp.val);
                } else {
                    st.add(temp.val);
                }
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
                levelNum--;
            }
            while (!st.isEmpty()) {
                wrapList.add(st.pop());
            }
            flag = !flag;
        }
        return wrapList;
    }
}
