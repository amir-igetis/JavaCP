package striverAToZ.linkedList.hardProblems;

public class RotateALL {

    /// Question 2
    ///
    /// Problem Statement: Given the head of a singly linked list containing integers, shift the elements of the linked list to the right by k places and return the head of the modified list. Do not change the dataues of the nodes, only change the links between nodes.

    public static void main(String[] args) {
        // Create linked list: 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int k = 2;
        Node result = rotateRight(head, k);

        // Print result
        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }

    // brute

    /// Time Complexity: O(k * n),We are performing `k` rotations. In each rotation. We traverse the list to reach the second-last node (this takes O(n) time), then we adjust a few pointers (which is O(1)). So the overall time complexity is O(k * n). This approach becomes inefficient if k is large, especially with long lists, because each rotation traverses the full list.
    ///
    /// Space Complexity: O(1), We do not use any additional data structures.
    // Definition for singly-linked list node
    static Node rotateRight(Node head, int k) {
        // If list is empty or has one node or no rotation needed
        if (head == null || head.next == null || k == 0) return head;

        // Repeat rotation k times
        for (int i = 0; i < k; i++) {
            // Initialize pointers for traversal
            Node curr = head;
            Node prev = null;

            // Traverse to the last node
            while (curr.next != null) {
                prev = curr;
                curr = curr.next;
            }

            // Cut the last node
            prev.next = null;

            // Move it to the front
            curr.next = head;
            head = curr;
        }

        // Return rotated list
        return head;
    }

    // optimal

    /// Time Complexity: O(N), We perform a single traversal to calculate the length, another to find the new tail, and one for final breaking all linear operations.
    ///
    /// Space Complexity: O(1),No extra space is used; we just adjust pointers in place.
    // Definition for singly-linked list node
    static Node rotateRightI(Node head, int k) {
        // If list is empty or only one node or k = 0, return head
        if (head == null || head.next == null || k == 0)
            return head;

        // Initialize length and tail
        int length = 1;
        Node tail = head;

        // Traverse to find tail and calculate length
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Make the list circular
        tail.next = head;

        // Calculate effective rotation
        k = k % length;

        // Traverse to new tail position
        int stepsToNewTail = length - k;
        Node newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // New head is next of newTail
        Node newHead = newTail.next;

        // Break the circle
        newTail.next = null;

        return newHead;
    }
}

