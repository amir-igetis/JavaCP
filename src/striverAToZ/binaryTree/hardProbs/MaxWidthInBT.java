package striverAToZ.binaryTree.hardProbs;

import java.util.*;

public class MaxWidthInBT {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.left.left = new TreeNode(7);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

        int maxWidth = widthOfBinaryTree(root);
        System.out.println("The maximum width of the Binary Tree is " + maxWidth);
    }

    // striver code
    // soln for
    // https://leetcode.com/problems/maximum-width-of-binary-tree/description/
    static int widthOfBinaryTreeII(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            int mmin = q.peek().num; // to make the id starting from zero
            int first = 0, last = 0;
            for (int i = 0; i < size; i++) {
                int cur_id = q.peek().num - mmin;
                TreeNode TreeNode = q.peek().node;
                q.poll();
                if (i == 0)
                    first = cur_id;
                if (i == size - 1)
                    last = cur_id;
                if (TreeNode.left != null)
                    q.offer(new Pair(TreeNode.left, cur_id * 2 + 1));
                if (TreeNode.right != null)
                    q.offer(new Pair(TreeNode.right, cur_id * 2 + 2));
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    } // tx O(n) & sc O(n)

    // soln for
    // https://practice.geeksforgeeks.org/problems/maximum-width-of-tree/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=maximum-width-of-tree
    static int getMaxWidth(TreeNode root) {
        // Your code here
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 0;
        int res = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = q.remove();
                if (current.left != null) {
                    q.add(current.left);
                    count++;
                }
                if (current.right != null) {
                    q.add(current.right);
                    count++;
                }
            }
            res = Math.max(res, count);
            count = 0;
        }
        return res;
    }

    // another soln
    static int getMaxWidthII(TreeNode root) {
        // Your code here
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            int level = 0;
            for (int i = 0; i < n; i++) {
                TreeNode temp = q.poll();
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
                level++;
            }
            ans = Math.max(ans, level);
        }
        return ans;
    }

    // soln for
    // https://leetcode.com/problems/maximum-width-of-binary-tree/description/
    static int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Map<TreeNode, Integer> m = new HashMap<TreeNode, Integer>();
        q.offer(root);
        m.put(root, 1);
        int curW = 0;
        int maxW = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int start = 0;
            int end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode TreeNode = q.poll();
                if (i == 0)
                    start = m.get(TreeNode);
                if (i == size - 1)
                    end = m.get(TreeNode);
                if (TreeNode.left != null) {
                    m.put(TreeNode.left, m.get(TreeNode) * 2);
                    q.offer(TreeNode.left);
                }
                if (TreeNode.right != null) {
                    m.put(TreeNode.right, m.get(TreeNode) * 2 + 1);
                    q.offer(TreeNode.right);
                }
            }
            curW = end - start + 1;
            maxW = Math.max(curW, maxW);
        }
        return maxW;
    }

    // another soln for //
    // https://leetcode.com/problems/maximum-width-of-binary-tree/description/
    static int widthOfBinaryTreeI(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    private static int dfs(TreeNode root, int level, int order, List<Integer> start, List<Integer> end) {
        if (root == null)
            return 0;
        if (start.size() == level) {
            start.add(order);
            end.add(order);
        } else
            end.set(level, order);
        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2 * order, start, end);
        int right = dfs(root.right, level + 1, 2 * order + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }


    private static class Pair {
        TreeNode node;
        int num;

        Pair(TreeNode _node, int _num) {
            num = _num;
            node = _node;
        }
    }

}

