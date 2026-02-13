package striverAToZ.binaryTree.hardProbs;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBT {

    public static void main(String[] args) {

        // Construct the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);


        System.out.print("Original Tree: ");
        inorder(root);
        System.out.println();

        String serialized = serialize(root);
        System.out.println("Serialized: " + serialized);

        TreeNode deserialized = deserialize(serialized);

        System.out.print("Tree after deserialization: ");
        inorder(deserialized);
        System.out.println();
    }

//    tc and sc O(N)

    // Serialize using level-order traversal
    static String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (curr == null) {
                sb.append("#,");
            } else {
                sb.append(curr.val).append(",");
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }

        return sb.toString();
    }

    // Deserialize from string to binary tree
    static TreeNode deserialize(String data) {
        if (data == null || data.isEmpty())
            return null;

        String[] values = data.split(",");
        int index = 0;

        TreeNode root = new TreeNode(Integer.parseInt(values[index++]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // Left child
            if (!values[index].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[index]));
                node.left = left;
                queue.offer(left);
            }
            index++;

            // Right child
            if (!values[index].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[index]));
                node.right = right;
                queue.offer(right);
            }
            index++;
        }

        return root;
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);

    }
}

