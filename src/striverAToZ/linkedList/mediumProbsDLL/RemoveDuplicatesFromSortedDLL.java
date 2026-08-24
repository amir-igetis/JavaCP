package striverAToZ.linkedList.mediumProbsDLL;

public class RemoveDuplicatesFromSortedDLL {

    /// Question 3
    ///
    /// Problem Statement: Given the head of a doubly linked list with its values sorted in non-decreasing order. Remove all duplicate occurrences of any value in the list so that only distinct values are present in the list.
    ///
    /// Return the head of the modified linked list.
    public static void main(String[] args) {
        int[] values = {1, 2, 2, 2, 3, 4, 4, 5, 5, 6};

        for (int value : values) {
            insertAtEnd(value);
        }

        // Print the original list
        System.out.print("Original List: ");
        printList();

        // Remove duplicate nodes
        head = removeDuplicates();

        // Print the updated list
        System.out.print("After Removing Duplicates (keeping 1 occurrence): ");
        printList();
    }

    /// Time Complexity: O(n), Every node is visited exactly once.
    /// Space Complexity: O(1), No extra space is used.

    static Node head = null;

    // Function to insert a node at the end of the list
    private static void insertAtEnd(int value) {
        Node newNode = new Node(value);

        // If list is empty, set new node as head
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Link the new node at the end
        current.next = newNode;
        newNode.back = current;
    }

    // Function to remove duplicate values from a sorted doubly linked list
    static Node removeDuplicates() {
        // If the list is empty, return null
        if (head == null) return null;

        Node current = head;

        // Traverse the list until the second last node
        while (current != null && current.next != null) {
            Node nextDistinct = current.next;

            // Skip and unlink all nodes with the same value as current
            while (nextDistinct != null && nextDistinct.data == current.data) {
                nextDistinct = nextDistinct.next;
            }

            // Connect current node to the next distinct node
            current.next = nextDistinct;
            if (nextDistinct != null) {
                nextDistinct.back = current;
            }

            // Move to the next node
            current = current.next;
        }

        return head;
    }

    // Function to print the list
    private static void printList() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
