package striverAToZ.linkedList.mediumProbs;

import java.util.ArrayList;
import java.util.Collections;

public class SortLL {

    /// Question 11
    ///
    /// Problem Statement: Given a linked list, sort its nodes based on the data value in them. Return the head of the sorted linked list.
    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(5);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(1);

        // Print original list
        System.out.print("Original Linked List: ");
        printLinkedList(head);

        head = sortLLI(head);

        // Print sorted list
        System.out.print("Sorted Linked List: ");
        printLinkedList(head);
    }

    // brute

    /// Time Complexity: O(2*N + N*LogN), we traverse the linked list, store its elements in an array, sort it, and then copy the sorted values back into the original list.
    /// Space Complexity: O(N) , additional space required to store all the elements of linked list in an array.
    static Node sortLL(Node head) {
        // List to store node values
        ArrayList<Integer> arr = new ArrayList<>();

        // Pointer to traverse the list
        Node temp = head;

        // Traverse and push values into list
        while (temp != null) {
            arr.add(temp.data);
            temp = temp.next;
        }

        // Sort the list
        Collections.sort(arr);

        // Reassign sorted values to list nodes
        temp = head;
        for (int i = 0; i < arr.size(); i++) {
            temp.data = arr.get(i);
            temp = temp.next;
        }

        // Return head of sorted list
        return head;
    }

    private static void printLinkedList(Node head) {
        // Pointer to traverse list
        Node temp = head;

        // Traverse and print values
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // optimal

    /// Time Complexity: O(N*LogN), we recursively divide the linked list into two halves and then merge two sorted halves.
    /// Space Complexity: O(1) , constant additional space is required to sort the entire linked list.
    private static Node mergeTwoSortedLinkedLists(Node list1, Node list2) {
        // Create a dummy node
        Node dummyNode = new Node(-1, null);

        // Temp pointer to build merged list
        Node temp = dummyNode;

        // Traverse both lists
        while (list1 != null && list2 != null) {
            // Choose smaller node
            if (list1.data <= list2.data) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            // Move temp pointer
            temp = temp.next;
        }

        // Attach remaining nodes
        if (list1 != null) {
            temp.next = list1;
        } else {
            temp.next = list2;
        }

        // Return head of merged list
        return dummyNode.next;
    }

    // Function to find middle of linked list
    private static Node findMiddle(Node head) {
        // If list empty or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Slow and fast pointers
        Node slow = head;
        Node fast = head.next;

        // Move fast twice as fast as slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Return middle node
        return slow;
    }

    // Function to perform merge sort
    static Node sortLLI(Node head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle node
        Node middle = findMiddle(head);

        // Split into two halves
        Node right = middle.next;
        middle.next = null;
        Node left = head;

        // Recursively sort both halves
        left = sortLL(left);
        right = sortLL(right);

        // Merge sorted halves
        return mergeTwoSortedLinkedLists(left, right);
    }
}
