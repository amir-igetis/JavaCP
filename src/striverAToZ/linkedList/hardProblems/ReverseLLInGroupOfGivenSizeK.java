package striverAToZ.linkedList.hardProblems;

public class ReverseLLInGroupOfGivenSizeK {

    /// Question 1
    ///
    /// Problem Statement: Given the head of a singly linked list containing integers, reverse the nodes of the list in groups of k and return the head of the modified list. If the number of nodes is not a multiple of k, then the remaining nodes at the end should be kept as is and not reversed.
    /// Do not change the dataues of the nodes, only change the links between nodes.
    public static void main(String[] args) {
        // Creating the linked list: 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int k = 2;

        // Reversing in groups of k
        Node result = reverseKGroup(head, k);

        // Printing the final list
        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }

    /// Time Complexity: O(N),We visit each node exactly once during reversal and during group detection (getKthNode). So the total operations are linear with respect to the number of nodes in the list.
    ///
    /// Space Complexity: O(1),The algorithm uses a constant amount of extra memory for pointers and dummy node. No additional data structures like arrays or stacks are used.
    static Node reverseKGroup(Node head, int k) {
        // Create a dummy node to handle edge cases
        Node dummy = new Node(0);
        dummy.next = head;

        // Pointer to the tail of the last reversed group
        Node groupPrev = dummy;

        while (true) {
            // Get the k-th node in the current group
            Node kth = getKthNode(groupPrev, k);
            if (kth == null) break;

            // Store the next group’s head
            Node groupNext = kth.next;

            // Reverse the current k-group
            Node prev = groupNext;
            Node curr = groupPrev.next;

            for (int i = 0; i < k; i++) {
                Node temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect the previous group to the reversed group
            Node temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }

        // Return the new head
        return dummy.next;
    }

    // Helper function to get the k-th node from the current node
    private static Node getKthNode(Node curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;

    }
}