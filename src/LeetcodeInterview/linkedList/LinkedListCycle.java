package LeetcodeInterview.linkedList;

public class LinkedListCycle {
    public static void main(String[] args) {
//
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-1);
        head.next.next.next.next = head.next;
        System.out.println(hasCycle(head));
    }

    static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}
