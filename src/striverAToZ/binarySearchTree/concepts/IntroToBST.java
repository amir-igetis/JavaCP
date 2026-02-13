package striverAToZ.binarySearchTree.concepts;

public class IntroToBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(17);

        System.out.println(maxVal(root.left));

        int[] nums = {8, 14, 45, 64, 100};
        System.out.println(isBSTTraversalI(nums));
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/binary-search-trees/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=binary-search-trees
    static boolean isBSTTraversal(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max >= nums[i])
                return false;
            max = nums[i];
        }
        return true;
    }

    //    my sol 
    static boolean isBSTTraversalI(int[] nums) {
        // code here
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] >= nums[j])
                return false;
            i++;
        }
        return true;
    }

    // search in binary tree
    // soln for
    // https://leetcode.com/problems/search-in-a-binary-search-tree/description/
    // soln for
    // https://practice.geeksforgeeks.org/problems/search-a-node-in-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=search-a-node-in-bst
    static boolean search(TreeNode root, int x) {
        if (root == null)
            return false;
        else if (root.val == x)
            return true;
        else if (root.val > x)
            return search(root.left, x);
        else return search(root.right, x);
    }

    // find min or max in BST
    // soln for
    // https://practice.geeksforgeeks.org/problems/minimum-element-in-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=minimum-element-in-bst
    static int minVal(TreeNode root) {
        if (root == null)
            return -1;
        if (root.left != null)
            return minVal(root.left);
        return root.val;

    }

    static int maxVal(TreeNode root) {
        if (root == null)
            return -1;
        if (root.right != null)
            return maxVal(root.right);
        return root.val;
    }
}
