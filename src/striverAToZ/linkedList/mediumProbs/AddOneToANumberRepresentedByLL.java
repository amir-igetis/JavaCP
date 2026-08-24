package striverAToZ.linkedList.mediumProbs;

public class AddOneToANumberRepresentedByLL {

    /// Question 13
    ///
    /// Problem Statement: Given the head of a singly linked list representing a positive integer number. Each node of the linked list represents a digit of the number, with the 1st node containing the leftmost digit of the number and so on. The task is to add one to the value represented by the linked list and return the head of a linked list containing the final value.
    ///
    /// The number will contain no leading zeroes except when the value represented is zero itself.

    public static void main(String[] args) {
        Node head = null;
        LinkedList ll = new LinkedList();

        // Example: Number 129 (1 -> 2 -> 9)
        head = ll.append(head, 1);
        head = ll.append(head, 2);
        head = ll.append(head, 9);

        System.out.print("Original Number: ");
        ll.printList(head);

        head = addOneI(head);

        System.out.print("After Adding One: ");
        ll.printList(head);
    }

    // iterative

    /// Time Complexity: O(n), Two reversals + one pass for addition.
    /// Space Complexity: O(1), Iterative, no extra stack used.

    // LinkedList class to manage node-level operations
    private static class LinkedList {
        // function to insert digit at the end
        Node append(Node head, int value) {
            Node newNode = new Node(value);
            if (head == null) {
                return newNode;
            }
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
            return head;
        }

        // Function to print the list
        void printList(Node head) {
            Node current = head;
            while (current != null) {
                System.out.print(current.data);
                current = current.next;
            }
            System.out.println();
        }
    }

    private static Node reverseList(Node node) {
        Node prev = null;
        Node current = node;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    // Function to add one to the number represented by the linked list
    static Node addOne(Node head) {
        // Reverse the list to make least significant digit accessible
        head = reverseList(head);

        Node current = head;
        int carry = 1;

        // Traverse the list and add carry
        while (current != null && carry > 0) {
            int sum = current.data + carry;
            current.data = sum % 10;
            carry = sum / 10;

            // If there's no next node and we still have a carry, append a new node
            if (current.next == null && carry > 0) {
                current.next = new Node(carry);
                carry = 0;
            }

            current = current.next;
        }

        // Reverse the list back to restore original order
        head = reverseList(head);
        return head;
    }

    // recursive

    /// Time Complexity: O(n), One pass for addition.
    /// Space Complexity: O(n), Auxiliary stack space.
    private static int addOneUtil(Node node) {
        // Base case: when reaching beyond last node, return carry = 1
        if (node == null) return 1;

        // Recurse to the end
        int carry = addOneUtil(node.next);
        int sum = node.data + carry;
        node.data = sum % 10;
        // Return new carry
        return sum / 10;
    }

    // Function to add one to the number represented by the linked list
    static Node addOneI(Node head) {
        // Perform recursive addition
        int carry = addOneUtil(head);

        // If carry remains after processing the head, create a new head node
        if (carry != 0) {
            Node newHead = new Node(carry);
            newHead.next = head;
            head = newHead;
        }

        return head;
    }

}
