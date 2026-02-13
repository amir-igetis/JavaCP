package striverAToZ.binaryTree.traversals;

import java.util.*;

public class IntroToTrees {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(17);

        System.out.println(countNodes(3)); // i is the lever number

//        preorder(root);
//        printList(node);
    }

    static void printList(TreeNode node) {
        if (node == null) return;
        printList(node.left);
        System.out.print(node.val);
        printList(node.right);
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/introduction-to-trees/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-trees
    private static int countNodes(int i) {
        // code here
        return (int) (Math.pow(2, i - 1));
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/binary-tree-representation/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=binary-tree-representation
    private static void createTree(TreeNode root0, List<Integer> v) {
        // Code here
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root0);
        int iterator = 1;
        int length = v.size();
        while (iterator < length) {
            TreeNode temp = q.poll();
            TreeNode leftNode = new TreeNode(v.get(iterator));
            temp.left = leftNode;
            q.add(leftNode);
            iterator++;
            TreeNode rightNode = new TreeNode(v.get(iterator));
            temp.right = rightNode;
            q.add(rightNode);
            iterator++;

        }
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/preorder-traversal/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=preorder-traversal
    private static ArrayList<Integer> preorder(Node root) {
        // Code here
        Stack<Node> stack = new Stack<>();
        ArrayList<Integer> arr = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            arr.add(temp.data);

            if (temp.right != null) {
                stack.push(temp.right);
            }

            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return arr;
    }

    // another soln
    private static ArrayList<Integer> preorderI(Node root) {
        // Code here
        ArrayList<Integer> arr = new ArrayList<Integer>();
        preorder(root, arr);
        return arr;
    }

    private static void preorder(Node root, ArrayList<Integer> list) {
        if (root != null) {
            list.add(root.data);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    // soln for
    // https://leetcode.com/problems/binary-tree-preorder-traversal/description/
    private List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> arr = new ArrayList<Integer>();
        preorder(root, arr);
        return arr;
    }

    private static void preorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/inorder-traversal/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=inorder-traversal
    private static ArrayList<Integer> inOrder(Node root) {
        // Code
        ArrayList<Integer> arr = new ArrayList<Integer>();
        inorder(root, arr);
        return arr;
    }

    private static void inorder(Node root, ArrayList<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.data);
            inorder(root.right, list);
        }
    }

    // soln for
    // https://leetcode.com/problems/binary-tree-inorder-traversal/description/
    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> arr = new ArrayList<Integer>();
        inorder(root, arr);
        return arr;
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/postorder-traversal/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=postorder-traversal
    private static ArrayList<Integer> postOrder(Node root) {
        // Your code goes here
        ArrayList<Integer> arr = new ArrayList<>();
        postorder(root, arr);
        return arr;
    }

    private static void postorder(Node root, ArrayList<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            list.add(root.data);
        }
    }

    // soln for https://leetcode.com/problems/binary-tree-postorder-traversal/
    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> arr = new ArrayList<Integer>();
        postorder(root, arr);
        return arr;
    }

    private static void postorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            list.add(root.val);
        }
    }

    // level order traversal
    // soln for
    // https://practice.geeksforgeeks.org/problems/level-order-traversal-in-spiral-form/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=level-order-traversal-in-spiral-form
    private static ArrayList<Integer> levelOrder(TreeNode root) {
        if (root == null) {
            ArrayList<Integer> hh = new ArrayList<>();
            return hh;
        }
        ArrayList<Deque<Integer>> l = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int eo = 0;
        while (!q.isEmpty()) {
            Deque<Integer> d = new ArrayDeque<>();
            int c = q.size();
            for (int i = 0; i < c; i++) {
                TreeNode cur = q.poll();
                if (eo == 0) {
                    d.addFirst(cur.val);
                } else {
                    d.addLast(cur.val);
                }
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
            if (eo == 0) eo = 1;
            else eo = 0;
            l.add(d);
        }
        ArrayList<Integer> hh = new ArrayList<>();
        for (Deque<Integer> aa : l) {
            for (Integer t : aa) {
                hh.add(t);
            }
        }
        return hh;
    }

    // soln for https://leetcode.com/problems/binary-tree-level-order-traversal/
    private static List<List<Integer>> levelOrderI(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if (root == null)
            return wrapList;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for (int i = 0; i < levelNum; i++) {
                if (queue.peek().left != null)
                    queue.offer(queue.peek().left);
                if (queue.peek().right != null)
                    queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }

    // iterative preorder
    // soln for
    // https://practice.geeksforgeeks.org/problems/preorder-traversal/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=preorder-traversal
    // soln for
    // https://leetcode.com/problems/binary-tree-preorder-traversal/description/
    private static ArrayList<Integer> preOrderIte(Node curr) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (curr == null) {
            return arr;
        }
        Stack<Node> s = new Stack<>();
        s.push(curr);
        while (!s.isEmpty()) {
            Node topNode = s.peek();
            arr.add(topNode.data);
            s.pop();
            if (topNode.right != null) {
                s.push(topNode.right);
            }
            if (topNode.left != null) {
                s.push(topNode.left);
            }
        }
        return arr;
    }

    // iterative inorder
    // soln for
    // https://practice.geeksforgeeks.org/problems/inorder-traversal/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=inorder-traversal
    // soln for https://leetcode.com/problems/binary-tree-inorder-traversal/
    private static ArrayList<Integer> inOrderIte(Node root) {
        ArrayList<Integer> arr = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        while (true) {
            if (root != null) {
                s.push(root);
                root = root.left;
            } else {
                if (s.isEmpty()) {
                    break;
                }
                root = s.peek();
                arr.add(root.data);
                s.pop();
                root = root.right;
            }
        }
        return arr;
    }

    // post order using two stacks
    // striver soln
    // soln for
    // https://practice.geeksforgeeks.org/problems/postorder-traversal/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=ostorder-traversal
    // soln for https://leetcode.com/problems/binary-tree-postorder-traversal/
    private static ArrayList<Integer> postOrderTwo(Node root) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) {
            return arr;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.peek();
            s1.pop();
            s2.push(root);
            if (root.left != null) {
                s1.push(root.left);
            }
            if (root.right != null) {
                s1.push(root.right);
            }
        }
        while (!s2.isEmpty()) {
            arr.add(s2.peek().data);
            s2.pop();
        }
        return arr;
    }

    // post order using single stack
    // striver soln
    private static ArrayList<Integer> postOrderOne(Node root) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) {
            return arr;
        }
        Stack<Node> st = new Stack<>();
        while (root != null || !st.isEmpty()) {
            if (root != null) {
                st.push(root);
                root = root.left;
            } else {
                Node temp = st.peek().right;
                if (temp == null) {
                    temp = st.peek();
                    st.pop();
                    arr.add(temp.data);
                    while (!st.isEmpty() && temp == st.peek().right) {
                        temp = st.peek();
                        st.pop();
                        arr.add(temp.data);
                    }
                } else {
                    root = temp;
                }
            }
        }
        return arr;
    }

    // in pre and post in one traversal
    private static void allTraversal(TreeNode root, List<Integer> pre, List<Integer> in, List<Integer> post) {
        Stack<Pair> st = new Stack<Pair>();
        st.push(new Pair(root, 1));

        if (root == null)
            return;

        while (!st.isEmpty()) {
            Pair it = st.pop();

            // this is part of pre
            // increment 1 to 2
            // push the left side of the tree
            if (it.num == 1) {
                pre.add(it.node.val);
                it.num++;
                st.push(it);

                if (it.node.left != null) {
                    st.push(new Pair(it.node.left, 1));
                }
            }

            // this is a part of in
            // increment 2 to 3
            // push right
            else if (it.num == 2) {
                in.add(it.node.val);
                it.num++;
                st.push(it);

                if (it.node.right != null) {
                    st.push(new Pair(it.node.right, 1));
                }
            }
            // don't push it back again
            else {
                post.add(it.node.val);
            }
        }
    }

    private static class Pair {
        TreeNode node;
        int num;

        Pair(TreeNode _node, int _num) {
            num = _num;
            node = _node;
        }
    }

    private static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
