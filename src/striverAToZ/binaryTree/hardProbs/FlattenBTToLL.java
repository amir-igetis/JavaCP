package striverAToZ.binaryTree.hardProbs;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenBTToLL {
    // program entry point
    public static void main(String[] args) {
        // construct sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(8);


        // print original tree preorder
        System.out.print("Binary Tree Preorder: ");
        printPreorder(root);
        System.out.println();

        // flatten the tree
        flattenII(root);

        // print flattened tree via right pointers
        System.out.print("Binary Tree After Flatten: ");
        printFlattenTree(root);
        System.out.println();
    }

    /// Brute force

//    tc and sc O(n) O(log2 n)
    // previous node reference for reverse preorder linking
    static TreeNode prev = null;

    // flatten tree to right-skewed linked list (preorder sequence)
    static void flatten(TreeNode root) {
        // return if node is null
        if (root == null) return;
        // flatten right subtree first
        flatten(root.right);
        // flatten left subtree next
        flatten(root.left);
        // connect current node's right to previously processed node
        root.right = prev;
        // nullify left pointer
        root.left = null;
        // update previous to current
        prev = root;
    }

    // print preorder traversal
    private static void printPreorder(TreeNode root) {
        // return on null
        if (root == null) return;
        // print current node
        System.out.print(root.val + " ");
        // traverse left subtree
        printPreorder(root.left);
        // traverse right subtree
        printPreorder(root.right);
    }

    // print nodes following right pointers
    static void printFlattenTree(TreeNode root) {
        // return on null
        if (root == null) return;
        // print current node
        System.out.print(root.val + " ");
        // move to right child
        printFlattenTree(root.right);
    }


    ///  Better approach

//    tc and sc O(n) O(log2 n)
    static void flattenI(TreeNode root) {
        // handle empty tree
        if (root == null) return;
        // create stack for DFS
        Deque<TreeNode> st = new ArrayDeque<>();
        // push root to start traversal
        st.push(root);
        // process while stack has nodes
        while (!st.isEmpty()) {
            // take top node
            TreeNode cur = st.pop();
            // push right child first (so left is processed first)
            if (cur.right != null) st.push(cur.right);
            // push left child next
            if (cur.left != null) st.push(cur.left);
            // if stack not empty, link current's right to next node
            if (!st.isEmpty()) cur.right = st.peek();
            // nullify left to form right-only chain
            cur.left = null;
        }
    }

    ///  Optimal approach (Morris Traversal)

//    tc and sc O(2n) O(1)
    static void flattenII(TreeNode root) {
        // Initialize a pointer
        // 'curr' to the root of the tree
        TreeNode curr = root;

        // Iterate until 'curr'
        // becomes NULL
        while (curr != null) {
            // Check if the current
            // node has a left child
            if (curr.left != null) {
                // If yes, find the rightmost
                // node in the left subtree
                TreeNode pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // Connect the rightmost node in
                // the left subtree to the current
                // node's right child
                pre.right = curr.right;

                // Move the entire left subtree to the
                // right child of the current node
                curr.right = curr.left;

                // Set the left child of
                // the current node to NULL
                curr.left = null;
            }

            // Move to the next node
            // on the right side
            curr = curr.right;
        }
    }


}

