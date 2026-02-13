package striverAToZ.binaryTree.hardProbs;

import java.util.ArrayList;
import java.util.List;

public class RootToNodePath {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.right = new TreeNode(3);

        ArrayList<ArrayList<Integer>> arr = Paths(root);
        for (ArrayList<Integer> i : arr) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
        System.out.println();

        //
        int target = 7;

        // Get path from root to target
        List<Integer> path = solve(root, target);

        // Print the path
        System.out.print("Path from root to node " + target + ": ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1)
                System.out.print(" -> ");

        }
    }

    // Function to return the final path vector
    static List<Integer> solve(TreeNode root, int x) {
        // Initialize result list
        List<Integer> arr = new ArrayList<>();

        // If tree is empty
        if (root == null)
            return arr;

        // Call helper function
        getPath(root, arr, x);
        return arr;
    }

    private static boolean getPath(TreeNode root, List<Integer> arr, int x) {
        // Base case: If node is null
        if (root == null)
            return false;

        // Add current node to path
        arr.add(root.val);

        // If current node is the target
        if (root.val == x)
            return true;

        // Recurse into left and right children
        if (getPath(root.left, arr, x) || getPath(root.right, arr, x))
            return true;

        // Backtrack if not found
        arr.remove(arr.size() - 1);
        return false;
    }


    /// this is different

    // soln for
    // https://practice.geeksforgeeks.org/problems/root-to-leaf-paths/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=root-to-leaf-paths
    static ArrayList<ArrayList<Integer>> Paths(TreeNode root) {
        // Code here

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        LeafPaths(root, arr, ans);
        return ans;
    }

    private static void LeafPaths(TreeNode root, ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> ans) {
        if (root == null) {
            return;
        }
        arr.add(root.val);
        if (root.left == null && root.right == null) {
            ans.add(arr);
            return;
        }

        LeafPaths(root.left, new ArrayList<Integer>(arr), ans);
        LeafPaths(root.right, arr, ans);
    }
}
