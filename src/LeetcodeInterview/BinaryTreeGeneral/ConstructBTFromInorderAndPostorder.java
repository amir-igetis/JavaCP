package LeetcodeInterview.BinaryTreeGeneral;

import java.util.*;

public class ConstructBTFromInorderAndPostorder {
    public static void main(String[] args) {
//

        int[] inorder = {9, 3, 15, 20, 7}, postorder = {9, 15, 7, 20, 3};
        TreeNode ans = buildTree(inorder, postorder);
        List<List<Integer>> res = printList(ans);

        for (List<Integer> i : res)
            for (Integer j : i)
                System.out.print(j + " ");
        System.out.println();
    }

    private static HashMap<Integer, Integer> inorderMp;
    private static int postorderIndex;

    static TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderMp = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMp.put(inorder[i], i);
        }
        postorderIndex = postorder.length - 1;
        return constructTree(postorder, 0, inorder.length - 1);
    }

    private static TreeNode constructTree(int[] postorder, int left, int right) {
        if (left > right)
            return null;

        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);
        int inorderIndex = inorderMp.get(rootVal);

        root.right = constructTree(postorder, inorderIndex + 1, right);
        root.left = constructTree(postorder, left, inorderIndex - 1);

        return root;
    }

    private static List<List<Integer>> printList(TreeNode node) {
        List<List<Integer>> res = new ArrayList<>();
        if (node == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()) {
            int lvlSize = q.size();
            List<Integer> lvl = new ArrayList<>();
            for (int i = 0; i < lvlSize; i++) {
                TreeNode n = q.poll();
                lvl.add(n.val);
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            res.add(lvl);
        }
        return res;
    }
}
