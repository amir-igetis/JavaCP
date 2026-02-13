package striverAToZ.binarySearchTree.practiceProblems;

public class InorderSuccessorOrPredecessor {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

        TreeNode node = inorderSuccessor(root, root.right);
        printList(node);
    }

    static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                succ = root;
                root = root.left;
            }
        }
        return succ;
    }


    // soln for
    // https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1
    static void findPreSuc(TreeNode root, Res p, Res s, int key) {
        p.pre = prede(root, key);
        s.succ = succe(root, key);
    }

    static TreeNode prede(TreeNode root, int key) {
        TreeNode pre = null;
        while (root != null) {
            if (root.val >= key) {
                root = root.left;
            } else {
                pre = root;
                root = root.right;
            }
        }
        return pre;
    }

    static TreeNode succe(TreeNode root, int key) {
        TreeNode succ = null;
        while (root != null) {
            if (root.val > key) {
                succ = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return succ;
    }

    private static class Res {
        TreeNode pre = null;
        TreeNode succ = null;
    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }
}
