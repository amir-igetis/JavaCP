package striverAToZ.linkedList.mediumProbs;

import java.util.Stack;

public class CheckIfGivenLinkedListIsPalindrome {

    /// Question 7
    ///
    /// Problem Statement: Given the head of a singly linked list representing a positive integer number. Each node of the linked list represents a digit of the number, with the 1st node containing the leftmost digit of the number and so on. Check whether the linked list values form a palindrome or not. Return true if it forms a palindrome, otherwise, return false. .
    ///
    /// A palindrome is a sequence that reads the same forward and backwards.
    public static void main(String[] args) {
// Create a linked list with values 1, 5, 2, 5, and 1 (15251, a palindrome)
        Node head = new Node(1);
        head.next = new Node(5);
        head.next.next = new Node(2);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(1);

        // Print the original linked list
        System.out.print("Original Linked List: ");
        printLinkedList(head);

        // Check if the linked list is a palindrome
        if (isPalindrome(head)) {
            System.out.println("The linked list is a palindrome.");
        } else {
            System.out.println("The linked list is not a palindrome.");
        }
    }

    // brute

    /// Time Complexity: O(N), we traverse the entire linked list twice, once to push all elements into the stack, and once to compare them with the original list.
    ///
    /// Space Complexity: O(N), we use a stack that stores all the elements of the linked list, which takes linear space in the worst case.
    static boolean isPalindrome(Node head) {

        // Create an empty stack to store values
        Stack<Integer> st = new Stack<>();

        // Initialize a temporary pointer to the head of the linked list
        Node temp = head;

        // Traverse the linked list and push values onto the stack
        while (temp != null) {

            // Push the data from the current node onto the stack
            st.push(temp.data);

            // Move to the next node
            temp = temp.next;
        }

        // Reset the temporary pointer back to the head of the linked list
        temp = head;

        // Compare values by popping from the stack and checking against linked list nodes
        while (temp != null) {

            // If values don't match, it's not a palindrome
            if (temp.data != st.peek()) {
                return false;
            }

            // Pop the value from the stack
            st.pop();

            // Move to the next node in the linked list
            temp = temp.next;
        }

        // If all values match, it's a palindrome
        return true;
    }

    // Function to print the linked list
    private static void printLinkedList(Node head) {

        Node temp = head;

        while (temp != null) {

            // Print the current node's data
            System.out.print(temp.data + " ");

            // Move to the next node
            temp = temp.next;
        }

        System.out.println();
    }

    // optimal

    /// Time Complexity: O(N), we traverse the list twice, once to reverse half of it and once to compare, each taking O(N/2), which simplifies to O(N).
    ///
    /// Space Complexity: O(1), no extra space is used apart from a few pointers; operations are done in-place.
    // Node class represents a node in a linked list
    private static Node reverseLinkedList(Node head) {
        // Check if the list is empty or has only one node
        if (head == null || head.next == null) {
            return head;  // No change is needed; return the current head
        }

        // Recursive step: Reverse the remaining part of the list and get the new head
        Node newHead = reverseLinkedList(head.next);

        // Store the next node in 'front' to reverse the link
        Node front = head.next;

        // Update the 'next' pointer of 'front' to point to the current head
        front.next = head;

        // Set the 'next' pointer of the current head to null to break the original link
        head.next = null;

        // Return the new head obtained from the recursion
        return newHead;
    }

    // Function to check if the linked list is a palindrome
    static boolean isPalindromeI(Node head) {
        // Check if the linked list is empty or has only one node
        if (head == null || head.next == null) {
            return true;  // It's a palindrome by definition
        }

        // Initialize two pointers, slow and fast, to find the middle of the linked list
        Node slow = head;
        Node fast = head;

        // Traverse the linked list to find the middle using slow and fast pointers
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;       // Move slow pointer one step at a time
            fast = fast.next.next;  // Move fast pointer two steps at a time
        }

        // Reverse the second half of the linked list starting from the middle
        Node newHead = reverseLinkedList(slow.next);

        // Pointer to the first half
        Node first = head;

        // Pointer to the reversed second half
        Node second = newHead;

        // Compare data values of nodes from both halves
        while (second != null) {
            if (first.data != second.data) {
                // If values do not match, the list is not a palindrome
                reverseLinkedList(newHead);  // Reverse the second half back to its original state
                return false;
            }

            first = first.next;  // Move the first pointer
            second = second.next; // Move the second pointer
        }

        // Reverse the second half back to its original state
        reverseLinkedList(newHead);

        // The linked list is a palindrome
        return true;
    }
}
