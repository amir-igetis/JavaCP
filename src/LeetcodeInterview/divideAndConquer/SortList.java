package LeetcodeInterview.divideAndConquer;

public class SortList {
    public static void main(String[] args) {
//
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode ans = sortList(head);
        printList(ans);

        System.out.println();
    }

    static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head; // Base case

        ListNode mid = getMiddle(head);
        ListNode rightHalf = mid.next;
        mid.next = null; // Split list

        ListNode left = sortList(head);
        ListNode right = sortList(rightHalf);

        return merge(left, right);
    }

    private static ListNode getMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("Null");
    }
}
