package striverAToZ.binaryTree.hardProbs;

public class CheckForChildrenSumProperty {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        reOrder(root);
        inorderTraversal(root);
    }

    static void reOrder(TreeNode root) {
        if (root == null)
            return;
        int child = 0;
        if (root.left != null) {
            child += root.left.val;
        }
        if (root.right != null) {
            child += root.right.val;
        }
        if (child < root.val) {
            if (root.left != null) {
                root.left.val = root.val;
            } else if (root.right != null) {
                root.right.val = root.val;
            }
        }
        reOrder(root.left);
        reOrder(root.right);
        int tot = 0;
        if (root.left != null)
            tot += root.left.val;
        if (root.right != null)
            tot += root.right.val;
        if (root.left != null || root.right != null)
            root.val = tot;
    }

    static void changeTreeI(TreeNode root) {
        reOrder(root);
    } // tc & sc O(n)

    // soln for
    // https://practice.geeksforgeeks.org/problems/children-sum-parent/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=hildren-sum-parent
    static int isSumProperty(TreeNode root) {
        // add your code here
        if (root == null)
            return 1;
        if (root.left == null & root.right == null) {
            return 1;
        }
        int sum = 0;
        if (root.left != null) {
            sum += root.left.val;
        }
        if (root.right != null) {
            sum += root.right.val;
        }
        if (root.val == sum) {
            return (isSumProperty(root.left) & isSumProperty(root.right));

        }
        return 0;
    }

    ///  this is the striver code

    static void changeTree(TreeNode root) {
        // Base case: If the currentTreeNode
        // is null, return and do nothing.
        if (root == null) {
            return;
        }

        // Calculate the sum of the values of
        // the left and right children, if they exist.
        int child = 0;
        if (root.left != null) {
            child += root.left.val;
        }
        if (root.right != null) {
            child += root.right.val;
        }

        // Compare the sum of children with
        // the currentTreeNode's value and update
        if (child >= root.val) {
            root.val = child;
        } else {
            // If the sum is smaller, update the
            // child with the currentTreeNode's value.
            if (root.left != null) {
                root.left.val = root.val;
            } else if (root.right != null) {
                root.right.val = root.val;
            }
        }

        // Recursively call the function
        // on the left and right children.
        changeTree(root.left);
        changeTree(root.right);

        // Calculate the total sum of the
        // values of the left and right
        // children, if they exist.
        int tot = 0;
        if (root.left != null) {
            tot += root.left.val;
        }
        if (root.right != null) {
            tot += root.right.val;
        }

        // If either left or right child
        // exists, update the currentTreeNode's
        // value with the total sum.
        if (root.left != null || root.right != null) {
            root.val = tot;
        }
    }

// Function to print the inorder
// traversal of the tree

    private static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }
}
