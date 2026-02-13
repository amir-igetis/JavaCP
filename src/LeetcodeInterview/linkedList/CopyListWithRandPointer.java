package LeetcodeInterview.linkedList;

import java.util.HashMap;

public class CopyListWithRandPointer {
    public static void main(String[] args) {
//
        Node head = new Node(7);

        Node ans = copyRandomList(head);
        System.out.println(ans.val);
    }

    static Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> mp = new HashMap<>();
        Node curr = head;

        while (curr != null) {
            mp.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            mp.get(curr).next = mp.get(curr.next);
            mp.get(curr).random = mp.get(curr.random);
            curr = curr.next;
        }

        return mp.get(head);
    }

    static Node copyRandomListI(Node head) {
        if (head == null) return null;
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        curr = head;
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        while (curr != null) {
            copyCurr.next = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        return dummy.next;

    }
}


