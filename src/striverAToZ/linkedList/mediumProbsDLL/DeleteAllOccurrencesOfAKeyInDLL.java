package striverAToZ.linkedList.mediumProbsDLL;

public class DeleteAllOccurrencesOfAKeyInDLL {

    /// Question 1
    /// Problem Statement: Given the head of a doubly linked list and an integer target. Delete all nodes in the linked list with the value target and return the head of the modified linked list.
    public static void main(String[] args) {
        // Java doesn't have C++'s Node*&.
        // Using an array allows the method to modify the head reference.
        Node[] head = new Node[1];

        // Insert values
        insertAtEnd(head, 1);
        insertAtEnd(head, 2);
        insertAtEnd(head, 3);
        insertAtEnd(head, 2);
        insertAtEnd(head, 4);
        insertAtEnd(head, 2);
        insertAtEnd(head, 5);

        // Print original list
        System.out.println("Original List:");
        printList(head[0]);

        // Target value
        int target = 2;

        // Delete target nodes
        head[0] = deleteTargetNodes(head[0], target);

        // Print after deletion
        System.out.println("\nList after deleting value " + target + ":");
        printList(head[0]);
    }

    /// Time Complexity: O(n), Every node is visited once.
    /// Space Complexity: O(1), No extra space is used.

    // Inserts a new node with the given value at the end of the list
    private static void insertAtEnd(Node[] head, int val) {

        // Create the new node
        Node newNode = new Node(val);

        // If list is empty, set new node as head
        if (head[0] == null) {
            head[0] = newNode;
            return;
        }

        // Traverse to the last node
        Node temp = head[0];

        while (temp.next != null) {
            temp = temp.next;
        }

        // Link the new node at the end
        temp.next = newNode;
        newNode.back = temp;
    }

    // Prints the entire linked list from head to tail
    private static void printList(Node head) {

        Node temp = head;

        while (temp != null) {

            System.out.print(temp.data);

            if (temp.next != null) {
                System.out.print(" <-> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    // Deletes all nodes that have the specified target value
    static Node deleteTargetNodes(Node head, int target) {

        Node current = head;

        // Traverse the entire list
        while (current != null) {

            // Store next node before possibly deleting current
            Node nextNode = current.next;

            // If current node matches target
            if (current.data == target) {

                // If current is not the head
                if (current.back != null) {
                    current.back.next = current.next;
                } else {
                    // Current is the head
                    head = current.next;
                }

                // If current is not the tail
                if (current.next != null) {
                    current.next.back = current.back;
                }
            }

            // Move to the next node
            current = nextNode;
        }

        return head;
    }
}