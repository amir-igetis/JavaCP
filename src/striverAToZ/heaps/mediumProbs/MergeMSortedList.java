package striverAToZ.heaps.mediumProbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MergeMSortedList {
    public static void main(String[] args) {
        // Example: [[1,4,5],[1,3,4],[2,6]]
        ListNode a = new ListNode(1);
        a.next = new ListNode(4);
        a.next.next = new ListNode(5);

        ListNode b = new ListNode(1);
        b.next = new ListNode(3);
        b.next.next = new ListNode(4);

        ListNode c = new ListNode(2);
        c.next = new ListNode(6);

        ListNode[] lists = new ListNode[]{a, b, c};


//        ListNode result = mergeKSortedLists(lists);
        ListNode result = mergeKListsI(lists);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }


    // Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        // Constructor to initialize node with value
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

//    only sorting

    // Function to merge k sorted linked lists using brute force
    static ListNode mergeKSortedLists(ListNode[] lists) {
        // List to collect all node values
        List<Integer> allValues = new ArrayList<>();

        // Traverse each linked list
        for (ListNode head : lists) {
            while (head != null) {
                allValues.add(head.val);
                head = head.next;
            }
        }

        // Sort the collected values
        Collections.sort(allValues);

        // Create dummy node for result list
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // Create new nodes from sorted values
        for (int val : allValues) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        return dummy.next;
    }

    // using pq
    static ListNode mergeKListsI(ListNode[] lists) {
        // Create a priority queue (min-heap) to store nodes
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a, b) -> a.val - b.val
        );

        // Push the head of each non-null list into the heap
        for (ListNode node : lists) {
            if (node != null) pq.add(node);
        }

        // Create a dummy node to build the result list
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // While the heap is not empty
        while (!pq.isEmpty()) {
            // Extract the node with the smallest value
            ListNode smallest = pq.poll();

            // Add it to the result list
            tail.next = smallest;
            tail = tail.next;

            // If there's a next node, push it into the heap
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
        }

        // Return the head of the merged list
        return dummy.next;
    }
}


