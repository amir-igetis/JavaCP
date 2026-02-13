package LeetcodeInterview.divideAndConquer;

public class ConvertSortedArrToBST {
    public static void main(String[] args) {
//
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode ans = sortedArrayToBST(nums);
        inOrder(ans);
    }

    static TreeNode sortedArrayToBST(int[] nums) {
        return constructBST(nums, 0, nums.length - 1);
    }

    private static TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructBST(nums, left, mid - 1);
        root.right = constructBST(nums, mid + 1, right);

        return root;
    }

    private static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }
}
