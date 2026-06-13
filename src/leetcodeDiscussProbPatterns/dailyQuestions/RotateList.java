package leetcodeDiscussProbPatterns.dailyQuestions;

public class RotateList {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        int k = 2;
        ListNode ans = rotateRight(node, k);
        System.out.println(ans.val);
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
