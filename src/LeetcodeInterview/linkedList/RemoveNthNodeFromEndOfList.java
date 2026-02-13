package LeetcodeInterview.linkedList;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
//
        ListNode head = new ListNode(1);

        int n = 2;

        ListNode ans = removeNthFromEnd(head, n);
        System.out.println(ans.val);
    }


    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        for (int i = 0; i <= n; i++)
            first = first.next;

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummy.next;
    }
}
