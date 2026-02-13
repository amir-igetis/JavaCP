package striverAToZ.binaryTree.mediumProbs;

import java.util.ArrayList;
import java.util.List;

public class LOrRViewOfBT {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(10);
        root.left.left.right = new TreeNode(5);
        root.left.left.right.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(10);

        List<Integer> ans = rightSideView(root);
        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();

        List<Integer> ansI = leftSideView(root);
        for (Integer i : ansI)
            System.out.print(i + " ");
        System.out.println();
    }

    static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightView(root, res, 0);
        return res;
    }

    private static void rightView(
            TreeNode curr, List<Integer> res, int currDepth
    ) {
        if (curr == null)
            return;
        if (currDepth == res.size())
            res.add(curr.val);
        rightView(curr.right, res, currDepth + 1);
        rightView(curr.left, res, currDepth + 1);
    }


//    left view

    static List<Integer> leftSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        leftView(root, res, 0);
        return res;

    }

    private static void leftView(
            TreeNode curr, List<Integer> res, int currDepth
    ) {
        if (curr == null)
            return;
        if (currDepth == res.size())
            res.add(curr.val);
        leftView(curr.left, res, currDepth + 1);
        leftView(curr.right, res, currDepth + 1);
    }
}
