package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BalanceABinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
//root.left.left.left.left = new TreeNode(5);
        TreeNode ans = balanceBST(root);
        printList(ans);
    }

    static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }

    //    inorder + recursive  TC & SC O(N)
    static TreeNode balanceBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTrav(root, inorder);
        return createBalancedBST(inorder, 0, inorder.size() - 1);
    }

    private static void inorderTrav(TreeNode root, List<Integer> inorder) {
        if (root == null)
            return;
        inorderTrav(root.left, inorder);
        inorder.add(root.val);
        inorderTrav(root.right, inorder);
    }

    private static TreeNode createBalancedBST(List<Integer> inorder, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode leftSubTree = createBalancedBST(inorder, start, mid - 1);
        TreeNode rightSubTree = createBalancedBST(inorder, mid + 1, end);
        TreeNode node = new TreeNode(
                inorder.get(mid), leftSubTree, rightSubTree
        );

        return node;
    }

    //    Day stout warren algo / In place balancing TC & SC O(n) & O(n)
    static TreeNode balanceBSTI(TreeNode root) {
        if (root == null) return null;

        // Step 1: Create the backbone (vine)
        // Temporary dummy node
        TreeNode vineHead = new TreeNode(0);
        vineHead.right = root;
        TreeNode current = vineHead;
        while (current.right != null) {
            if (current.right.left != null) {
                rightRotate(current, current.right);
            } else {
                current = current.right;
            }
        }

        // Step 2: Count the nodes
        int nodeCount = 0;
        current = vineHead.right;
        while (current != null) {
            ++nodeCount;
            current = current.right;
        }

        // Step 3: Create a balanced BST
        int m =
                (int) Math.pow(
                        2,
                        Math.floor(Math.log(nodeCount + 1) / Math.log(2))
                ) -
                        1;
        makeRotations(vineHead, nodeCount - m);
        while (m > 1) {
            m /= 2;
            makeRotations(vineHead, m);
        }

        TreeNode balancedRoot = vineHead.right;
        return balancedRoot;
    }

    // Function to perform a right rotation
    private static void rightRotate(TreeNode parent, TreeNode node) {
        TreeNode tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        parent.right = tmp;
    }

    // Function to perform a left rotation
    private static void leftRotate(TreeNode parent, TreeNode node) {
        TreeNode tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        parent.right = tmp;
    }

    // Function to perform a series of left rotations to balance the vine
    private static void makeRotations(TreeNode vineHead, int count) {
        TreeNode current = vineHead;
        for (int i = 0; i < count; ++i) {
            TreeNode tmp = current.right;
            leftRotate(current, tmp);
            current = current.right;
        }

    }
}
