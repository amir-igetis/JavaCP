package LeetcodeInterview.linkedList;

public class AddTwoNums {
    public static void main(String[] args) {
//
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);

        ListNode ans = addTwoNumbers(head1, head2);
        while (ans.next != null) {
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
        System.out.print(ans.val);


        System.out.println();
    }

    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        if (carry > 0)
            curr.next = new ListNode(carry);

        return dummy.next;
    }
}
