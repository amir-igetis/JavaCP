package striverAToZ.binarySearchTree.practiceProblems;

public class DeleteNodeInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

//        System.out.println(insert(root, 12));
//        TreeNode node = delete(root, 15);
        TreeNode node = deleteNode(root, 15);

        printList(node);


    }

    private static void printList(TreeNode root) {
        if (root == null)
            return;
        printList(root.left);
        System.out.print(root.val + " ");
        printList(root.right);
    }


    static TreeNode delete(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key)
            return helper(root);
        TreeNode dummy = root;
        while (root != null) {
            if (root.val > key) {
                if (root.left != null && root.left.val == key) {
                    root.left = helper(root.left);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right != null && root.right.val == key) {
                    root.right = helper(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return dummy;
    }

    private static TreeNode helper(TreeNode root) {
        if (root.left == null)
            return root.right;
        else if (root.right == null)
            return root.left;
        TreeNode rightChild = root.right;
        TreeNode lastRight = findLastRight(root.left);
        lastRight.right = rightChild;
        return root.left;
    }

    private static TreeNode findLastRight(TreeNode root) {
        if (root.right == null)
            return root;
        return findLastRight(root.right);
    }

//    another soln

    // soln for https://leetcode.com/problems/delete-node-in-a-bst/
    // soln for
    // https://practice.geeksforgeeks.org/problems/delete-a-node-from-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=delete-a-node-from-bst
    static TreeNode deleteNode(TreeNode root, int x) {
        // if root is null then return
        if (root == null)
            return root;
        // if x is smaller then delete on left
        if (root.val > x) {
            root.left = deleteNode(root.left, x);
        } else if (root.val < x) {
            root.right = deleteNode(root.right, x);
        }
        // if we found thenode to be deleted then
        else {
            // case 1 : if it's left child is null then return right child
            if (root.left == null)
                return root.right;
                // case 2 : if it's right child is null then return left child
            else if (root.right == null)
                return root.left;
            // case 3 : if both childs are not null then

            // set the node to be deleted's value to left most predecessor
            // root's right child
            root.val = leftPred(root.right);
            // and now delete the left most predecessor from root.right sub tree
            // here we are deleting coz we know that to delete a
            // left most predecessor is easy coz it's left child is always a null
            root.right = deleteNode(root.right, root.val);
        }
        // return the root
        return root;
    }

    public static int leftPred(TreeNode root) {
        // take root's val
        int predVal = root.val;
        // go to left until its not null
        while (root.left != null) {
            predVal = root.left.val;
            root = root.left;
        }
        // return the value of the predecessor
        return predVal;
    }
}
