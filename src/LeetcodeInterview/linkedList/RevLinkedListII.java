package LeetcodeInterview.linkedList;

public class RevLinkedListII {
    public static void main(String[] args) {
//
        ListNode head = new ListNode(1);

        int left = 2, right = 4;

        ListNode ans = reverseBetween(head, left, right);
        System.out.println(ans.val);
    }

    static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode curr = pre.next;
        ListNode prev = null;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        pre.next.next = curr;
        pre.next = prev;

        return dummy.next;

    }
}
