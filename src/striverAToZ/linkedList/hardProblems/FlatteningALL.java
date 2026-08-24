package striverAToZ.linkedList.hardProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlatteningALL {

    /// Question 3
    /// Problem Statement: Given a linked list containing ‘N’ head nodes where every node in the linked list contains two pointers:
    ///
    /// ‘Next’ points to the next node in the list
    /// ‘Child’ pointer to a linked list where the current node is the head
    ///
    /// Each of these child linked lists is in sorted order and connected by a 'child' pointer. Your task is to flatten this linked list such that all nodes appear in a single layer or level in a 'sorted order'.
    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.child = new ListNode(14);

        head.next = new ListNode(10);
        head.next.child = new ListNode(4);

        head.next.next = new ListNode(12);
        head.next.next.child = new ListNode(20);
        head.next.next.child.child = new ListNode(13);

        head.next.next.next = new ListNode(7);
        head.next.next.next.child = new ListNode(17);

        // Print original list
        System.out.println("Original linked list:");
        printOriginalLinkedList(head, 0);

        ListNode flattened = flattenLinkedListI(head);
        System.out.print("\nFlattened linked list: ");
        printLinkedList(flattened);
    }
    // brute

    /// Time Complexity: O(N x M) + O(N x M log(N x M)) + O(N x M), where N is the number of nodes along the next pointers and M is the number of nodes along the child pointers.
    /// O(N x M) because we traverse through all the nodes, iterating through N nodes along the next pointers and M nodes along the child pointers.
    /// O(N x M log(N x M)) because we sort the array containing N x M total elements.
    /// O(N x M) because we reconstruct the linked list from the sorted array by iterating over the N x M elements.
    ///
    /// Space Complexity: O(N x M) + O(N x M), where N is the number of nodes along the next pointers and M is the number of nodes along the child pointers.
    /// O(N x M) for storing all the elements in an additional array for sorting.
    /// O(N x M) to reconstruct the linked list from the array after sorting.

    // Function to convert an array list to a linked list
    private static ListNode convertArrToLinkedList(List<Integer> arr) {

        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        for (int val : arr) {
            temp.child = new ListNode(val);
            temp = temp.child;
        }
        return dummyNode.child;
    }

    // Function to flatten a linked list with child pointers
    static ListNode flattenLinkedList(ListNode head) {
        List<Integer> arr = new ArrayList<>();

        while (head != null) {
            ListNode t2 = head;
            while (t2 != null) {
                arr.add(t2.val);
                t2 = t2.child;
            }
            head = head.next;
        }

        Collections.sort(arr);
        return convertArrToLinkedList(arr);
    }

    // Print the linked list in a single line
    private static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.child;
        }
        System.out.println();
    }

    // Print the original linked list in a grid-like structure
    private static void printOriginalLinkedList(ListNode head, int depth) {
        while (head != null) {
            System.out.print(head.val);
            if (head.child != null) {
                System.out.print(" -> ");
                printOriginalLinkedList(head.child, depth + 1);
            }
            if (head.next != null) {
                System.out.println();
                for (int i = 0; i < depth; ++i) {
                    System.out.print("| ");
                }
            }
            head = head.next;
        }

    }


    // optimal

    /// Time Complexity: O(N x (2M)) ~ O(2N x M), where N is the length of the linked list along the next pointer and M is the length of the linked list along the child pointers.
    /// The merge operation in each recursive call takes time complexity proportional to the length of the linked lists being merged, as they have to iterate over the entire lists. Since the vertical depth of the linked lists is assumed to be M, the time complexity for a single merge operation is proportional to O(2M).
    /// This operation is performed N number of times (to each and every node along the next pointer list), hence the resultant time complexity becomes O(N x 2M).
    ///
    /// Space Complexity: O(1), as this code uses no external space or additional data structures to store values. But a recursive stack uses O(N) space to build the recursive calls for each node along the next pointer list.
    private static ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode res = dummyNode;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                res.child = list1;
                res = list1;
                list1 = list1.child;
            } else {
                res.child = list2;
                res = list2;
                list2 = list2.child;
            }
            res.next = null;
        }

        if (list1 != null) res.child = list1;
        else res.child = list2;

        if (dummyNode.child != null) {
            dummyNode.child.next = null;
        }

        return dummyNode.child;
    }

    // Flatten a multi-level linked list
    static ListNode flattenLinkedListI(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mergedHead = flattenLinkedList(head.next);
        return merge(head, mergedHead);
    }


    private static // Node definition for linked list with child pointers
    class ListNode {
        int val;
        ListNode next;
        ListNode child;

        ListNode() {
            val = 0;
            next = null;
            child = null;
        }

        ListNode(int data1) {
            val = data1;
            next = null;
            child = null;
        }

        ListNode(int data1, ListNode next1, ListNode child1) {
            val = data1;
            next = next1;
            child = child1;
        }
    }
}
