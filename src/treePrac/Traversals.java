package treePrac;

import java.util.ArrayList;
import java.util.Arrays;

public class Traversals {

//    Traversal without recursion

//    postOrder traversal

    private static void postHelper(TreeNode root, ArrayList<Integer> ans) {
        if (root != null) {
            postHelper(root.left, ans);
            postHelper(root.right, ans);
            ans.add(root.val);
        }
    }

    static int[] postorder(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        postHelper(root, ans);
        return ans.stream().mapToInt(an -> an).toArray();
    }

    //    inOrder traversal
    private static void inHelper(TreeNode root, ArrayList<Integer> ans) {
        if (root != null) {
            inHelper(root.left, ans);
            ans.add(root.val);
            inHelper(root.right, ans);
        }
    }

    static int[] inorder(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        inHelper(root, ans);
        return ans.stream().mapToInt(an -> an).toArray();
    }

    // preOrder traversal
    private static void preHelper(TreeNode root, ArrayList<Integer> ans) {
        if (root != null) {
            ans.add(root.val);
            preHelper(root.left, ans);
            preHelper(root.right, ans);
        }
    }

    static int[] preorder(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        preHelper(root, ans);
//        int[] res = new int[ans.size()];
//        for (int i = 0; i < ans.size(); i++) {
//            res[i] = ans.get(i);
//        }
//        we can put all the elements of the ArrayList to a new array using the following method
        return ans.stream().mapToInt(an -> an).toArray();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(11);
        root.left.right.right = new TreeNode(12);

        System.out.println(Arrays.toString(preorder(root)));
    }
}
