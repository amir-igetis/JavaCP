package striverAToZ.linkedList.mediumProbs;

public class SegregateEvenAndOddNodesInLL {

    /// Question 8
    ///
    /// Problem Statement: Given the head of a singly linked list. Group all the nodes with odd indices followed by all the nodes with even indices and return the reordered list. Consider the 1st node to have index 1 and so on. The relative order of the elements inside the odd and even group must remain the same as the given input.
    public static void main(String[] args) {
// Creating linked list: 17 -> 15 -> 8 -> 12 -> 10 -> 5 -> 4
        Node head = new Node(17);
        head.next = new Node(15);
        head.next.next = new Node(8);
        head.next.next.next = new Node(12);
        head.next.next.next.next = new Node(10);
        head.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next = new Node(4);

        // Call segregation function
        Node newHead = segregateEvenOdd(head);

        // Print result
        while (newHead != null) {
            System.out.print(newHead.data + " ");
            newHead = newHead.next;
        }

    }

    /// Time Complexity: O(n), We traverse the entire linked list only once to rearrange the nodes. Each node is visited exactly once. No nested traversal or re-traversal occurs. Hence, linear time in terms of the number of nodes n.
    ///
    /// Space Complexity: O(1), We do not use any extra data structures
    static Node segregateEvenOdd(Node head) {

        // Edge case: If list is empty or has only one node
        if (head == null || head.next == null) return head;

        // Create pointers for the heads and tails of even and odd lists
        Node evenHead = null, evenTail = null;
        Node oddHead = null, oddTail = null;

        // Pointer to traverse the list
        Node current = head;

        // Traverse the linked list
        while (current != null) {

            // If the current node has even value
            if (current.data % 2 == 0) {

                // First even node
                if (evenHead == null) {
                    evenHead = current;
                    evenTail = current;
                } else {
                    evenTail.next = current;
                    evenTail = current;
                }

            } else {
                // If the current node has odd value

                // First odd node
                if (oddHead == null) {
                    oddHead = current;
                    oddTail = current;
                } else {
                    oddTail.next = current;
                    oddTail = current;
                }
            }

            // Move to next node
            current = current.next;
        }

        // If no even nodes found, return odd list
        if (evenHead == null) return oddHead;

        // If no odd nodes found, return even list
        if (oddHead == null) return evenHead;

        // Combine even and odd lists
        evenTail.next = oddHead;

        // Set end of list to null
        oddTail.next = null;

        return evenHead;
    }
}
