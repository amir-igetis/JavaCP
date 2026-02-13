package LeetcodeInterview.linkedList;

public class RotateList {
    public static void main(String[] args) {
//
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int k = 2;
        ListNode ans = rotateRight(head, k);
//        System.out.println(ans.val);

        while (ans != null) {
            System.out.print(ans.val + "->");
            ans = ans.next;
        }

        System.out.print("Null");
    }

    static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        ListNode temp = head;
        int length = 1;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        temp.next = head;

        k = k % length;
        int end = length - k;
        ListNode newTemp = head;
        for (int i = 1; i < end; i++) {
            newTemp = newTemp.next;
        }

        head = newTemp.next;
        newTemp.next = null;
        return head;
    }
}
