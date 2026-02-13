package leetcodeDiscussProbPatterns.dailyQuestions;


import java.util.HashSet;
import java.util.Set;

public class DeleteNodeFromLinkedListPresentInArr {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode ans = modifiedList(nums, head);
        ListNode and = ans;
        while (and != null) {
            System.out.print(and.val + " ");
            and = and.next;
        }
        System.out.println();
    }

    static ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums)
            numSet.add(num);

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (numSet.contains(curr.next.val))
                curr.next = curr.next.next;
            else curr = curr.next;
        }

        return dummy.next;
    }
}
