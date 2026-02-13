package linkedList;

public class InsertNode {
    public static void main(String[] args) {
        ListNode head = null;
        head = insertFirst(head, 1);
        head = insertFirst(head, 50);
        head = insertFirst(head, 30);
        head = insertFirst(head, 2);
        head = insertFirst(head, 12);

        head = insertLast(head, 89);
        head = insertLast(head, 47);

        deleteNode(head, 12);

        printList(head);

    }

    static ListNode insertFirst(ListNode head, int target) {
        ListNode curr = new ListNode(target);
        if (head == null)
            return curr;
        curr.next = head;
        head = curr;
        return head;
    }

    static ListNode insertLast(ListNode head, int target) {
        ListNode curr = new ListNode(target);
        if (head == null) return curr;
        ListNode temp = head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = curr;
        return head;
    }

    static ListNode deleteNode(ListNode head, int target) {
        if (target == head.val && head.next == null || head == null)
            return null;
        if (target == head.val) {
            ListNode ans = head.next;
            return ans;
        }
        ListNode curr = head;
        while (curr.next.val != target) {
            curr = curr.next;
        }
        ListNode temp = curr.next;
        if (temp.next != null) {
            temp = temp.next;
            curr.next = temp;
        } else {
            curr.next = null;
        }

        curr = head;
        return curr;
    }

    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + "-->");
            curr = curr.next;
        }
        System.out.println("Null");
    }
}
