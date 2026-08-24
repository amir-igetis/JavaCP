package striverAToZ.linkedList.mediumProbs;

import java.util.Arrays;
import java.util.List;

public class RemoveNthNodeFromTheEndOfLL {

    /// Question 9
    ///
    /// Problem Statement: Given a linked list and an integer N, the task is to delete the Nth node from the end of the linked list and print the updated linked list.
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
        int N = 3;

        // Create linked list manually
        Node head = new Node(arr.get(0));
        head.next = new Node(arr.get(1));
        head.next.next = new Node(arr.get(2));
        head.next.next.next = new Node(arr.get(3));
        head.next.next.next.next = new Node(arr.get(4));

//        head = deleteNthNodeFromEnd(head, N);
        head = deleteNthNodeFromEndI(head, N);

        // Print the modified linked list
        printLL(head);

    }

    // brute

    /// Time Complexity: O(L)+O(L-N), We are calculating the length of the linked list and then iterating up to the (L-N)th node of the linked list, where L is the total length of the list.
    /// Space Complexity: O(1), constant additional space is used.

    private static void printLL(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    // Function to delete the Nth node from the end
    static Node deleteNthNodeFromEnd(Node head, int N) {
        // If list is empty
        if (head == null) {
            return null;
        }

        int cnt = 0;
        Node temp = head;

        // Count total number of nodes
        while (temp != null) {
            cnt++;
            temp = temp.next;
        }

        // If N equals total nodes → delete head
        if (cnt == N) {
            return head.next;
        }

        // Calculate position from start
        int res = cnt - N;
        temp = head;

        // Traverse to the node before target
        while (temp != null) {
            res--;
            if (res == 0) {
                break;
            }
            temp = temp.next;
        }

        // Delete the node
        temp.next = temp.next.next;

        return head;
    }

    // optimal

    /// Time Complexity: O(N), since the fast pointer will traverse the entire linked list, where N is the length of the linked list.
    /// Space Complexity: O(1), constant additional space is used to check unique elements.
    static Node deleteNthNodeFromEndI(Node head, int N) {
        // Create a dummy node before head to handle edge cases
        Node dummy = new Node(0, head);

        // Initialize slow and fast pointers at dummy
        Node slow = dummy;
        Node fast = dummy;

        // Move fast pointer N+1 steps ahead to create a gap
        for (int i = 0; i <= N; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Slow is now at node before target → delete target node
        slow.next = slow.next.next;

        // Return updated head
        return dummy.next;
    }

}

