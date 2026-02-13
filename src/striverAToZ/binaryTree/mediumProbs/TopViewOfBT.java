package striverAToZ.binaryTree.mediumProbs;

import java.util.*;

public class TopViewOfBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(10);
        root.left.left.right = new TreeNode(5);
        root.left.left.right.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(10);

        ArrayList<Integer> ans = topView(root);
        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();

    }

    static ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair it = q.remove();
            int hd = it.hd;
            TreeNode temp = it.node;
//            if (mp.get(hd) == null)
//                mp.put(hd, temp.val);
            mp.computeIfAbsent(hd, k -> temp.val);
            if (temp.left != null)
                q.add(new Pair(temp.left, hd - 1));
            if (temp.right != null)
                q.add(new Pair(temp.right, hd + 1));
        }

        for (Map.Entry<Integer, Integer> entry : mp.entrySet())
            ans.add(entry.getValue());

        return ans;
    }

    private static class Pair {
        TreeNode node;
        int hd;

        public Pair(TreeNode n, int h) {
            this.node = n;
            this.hd = h;
        }
    }
}
