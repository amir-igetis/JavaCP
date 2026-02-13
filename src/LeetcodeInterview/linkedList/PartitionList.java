package LeetcodeInterview.linkedList;

public class PartitionList {
    public static void main(String[] args) {
//
        ListNode head = new ListNode(1);
        int x = 3;

        ListNode ans = partition(head, x);
        System.out.println(ans.val);
    }

    static ListNode partition(ListNode head, int x) {
        ListNode lessHead = new ListNode(0);
        ListNode less = lessHead;
        ListNode greaterHead = new ListNode(0);
        ListNode greater = greaterHead;

        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }
        greater.next = null;
        less.next = greaterHead.next;

        return lessHead.next;
    }
}
