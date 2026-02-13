package LeetcodeInterview.linkedList;

public class MergeTwoSortedList {
    public static void main(String[] args) {
//
        ListNode head1 = new ListNode(1);

        ListNode head2 = new ListNode(1);

        ListNode ans = mergeTwoLists(head1, head2);
        System.out.println(ans.val);
    }

    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null || list2 != null) {
            int v1 = (list1 == null ? Integer.MAX_VALUE : list1.val);
            int v2 = (list2 == null ? Integer.MAX_VALUE : list2.val);

            if (v1 < v2) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
            current.next = null;
        }
        return dummy.next;
    }
}
