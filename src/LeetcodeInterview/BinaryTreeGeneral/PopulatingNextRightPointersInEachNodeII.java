package LeetcodeInterview.BinaryTreeGeneral;


public class PopulatingNextRightPointersInEachNodeII {
    public static void main(String[] args) {
//
        Node root = new Node(1);

        Node ans = connect(root);
        System.out.println(ans.val);

    }

    static Node connect(Node root) {
        if (root == null)
            return null;

        Node head = root;
        while (head != null) {
            Node dummy = new Node(0);
            Node curr = dummy;
            for (Node node = head; node != null; node = node.next) {
                if (node.left != null) {
                    curr.next = node.left;
                    curr = curr.next;
                }
                if (node.right != null) {
                    curr.next = node.right;
                    curr = curr.next;
                }
            }
            head = dummy.next;
        }
        return root;
    }
}
