package striverAToZ.binaryTree.mediumProbs;

import java.util.*;

public class VerticalOrderTraversalOfBinaryTree {
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

        List<List<Integer>> list = new ArrayList<>();
        list = findVertical(root);

        System.out.println("The Vertical Traversal is : ");
        for (List<Integer> it : list) {
            for (int nodeVal : it) {
                System.out.print(nodeVal + " ");
            }
            System.out.println();
        }

    }

    static List<List<Integer>> findVertical(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> mp = new TreeMap<>();
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0, 0));
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            TreeNode node = tuple.node;
            int x = tuple.row;
            int y = tuple.col;

            if (!mp.containsKey(x))
                mp.put(x, new TreeMap<>());
            if (!mp.get(x).containsKey(y))
                mp.get(x).put(y, new PriorityQueue<>());
            mp.get(x).get(y).offer(node.val);

            if (node.left != null) {
                q.offer(new Tuple(node.left, x - 1, y + 1));
            }
            if (node.right != null) {
                q.offer(new Tuple(node.right, x + 1, y + 1));
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> pq : mp.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : pq.values()) {
                while (!nodes.isEmpty())
                    list.get(list.size() - 1).add(nodes.poll());
            }
        }
        return list;
    }
    // tc o(n*logn*logn*logn) & sc o(n)

    // soln for
    // https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=print-a-binary-tree-in-vertical-order
    static ArrayList<Integer> verticalOrder(TreeNode root) {
        // add your code here
        Queue<Pair> q = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        TreeMap<Integer, ArrayList<Integer>> t = new TreeMap<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode curr = p.node;
            int hd = p.hd;
            if (t.containsKey(hd)) {
                t.get(hd).add(curr.val);
            } else {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(curr.val);
                t.put(hd, al);
            }
            if (curr.left != null) {
                q.offer(new Pair(curr.left, hd - 1));
            }
            if (curr.right != null) {
                q.offer(new Pair(curr.right, hd + 1));
            }
        }
        for (Map.Entry<Integer, ArrayList<Integer>> p : t.entrySet()) {
            ArrayList<Integer> e = p.getValue();
            for (int x : e) {
                arr.add(x);
            }
        }
        return arr;
    }

    private static class Pair {
        TreeNode node;
        int hd;

        public Pair(TreeNode n, int h) {
            this.node = n;
            this.hd = h;
        }
    }

    private static class Tuple {
        TreeNode node;
        int row;
        int col;

        public Tuple(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}
