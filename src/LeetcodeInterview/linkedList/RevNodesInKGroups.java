package LeetcodeInterview.linkedList;

public class RevNodesInKGroups {
    public static void main(String[] args) {
//

        ListNode head = new ListNode(1);

        int k = 2;
        ListNode ans = reverseKGroup(head, k);
        System.out.println(ans.val);
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        if (count == k) {
            ListNode prev = null, next = null;
            curr = head;
            for (int i = 0; i < k; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            head.next = reverseKGroup(curr, k);
            return prev;
        }
        return head;
    }

    static ListNode reverseKGroupI(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode groupStart = prevGroupEnd.next;
            ListNode groupEnd = prevGroupEnd;
            for (int i = 0; i < k; i++) {
                groupEnd = groupEnd.next;
                if (groupEnd == null)
                    return dummy.next;
            }
            ListNode nextGroupStart = groupEnd.next;

            ListNode prev = null, curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            prevGroupEnd.next = groupEnd;
            groupStart.next = nextGroupStart;
            prevGroupEnd = groupStart;
        }
    }
}
