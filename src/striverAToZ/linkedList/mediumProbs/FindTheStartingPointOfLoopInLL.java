package striverAToZ.linkedList.mediumProbs;

import java.util.HashSet;
import java.util.Set;

public class FindTheStartingPointOfLoopInLL {

    /// Question 5
    ///
    /// Problem Statement: Given the head of a linked list that may contain a cycle, return the starting point of that cycle. If there is no cycle in the linked list return null.
    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(0);
        head.next.next.next = new Node(-4);

        // Creating a cycle (tail connects to node index 1)
        head.next.next.next.next = head.next;

        Node startNode = detectCycleI(head);
//        Node startNode = detectCycle(head);

        if (startNode != null)
            System.out.println("Cycle starts at node with value: "
                    + startNode.data);
        else
            System.out.println("No cycle found.");
    }

    // brute

    /// Time Complexity: O(N) where N is the number of nodes in the linked list. Each node is visited only once during traversal. Hashing allows O(1) lookup to check for previously visited nodes.
    ///
    /// Space Complexity: O(N) due to the additional hash set used to store visited nodes. In the worst case (no cycle), all N nodes will be stored in the hash set.
    // Function to detect start of loop using Hash Map
    static Node detectCycle(Node head) {
        // Create a set to store visited nodes
        Set<Node> visited = new HashSet<>();

        // Traverse through the list
        while (head != null) {
            // If already visited, it's the start of the loop
            if (visited.contains(head)) {
                return head;
            }

            // Mark current node as visited
            visited.add(head);

            // Move to the next node
            head = head.next;
        }

        // No cycle found
        return null;
    }

    // optimal

    /// Time Complexity: O(N) where N is the number of nodes in the linked list. In the worst case, we traverse the entire list once with the slow and fast pointers, and then again to find the entry point of the loop.
    ///
    /// Space Complexity: O(1) constant extra space. No additional data structures are used, only two pointers.
    static Node detectCycleI(Node head) {
        // Initialize slow and fast pointers
        Node slow = head;
        Node fast = head;

        // Traverse while fast and fast.next are not null
        while (fast != null && fast.next != null) {
            // Move slow one step
            slow = slow.next;

            // Move fast two steps
            fast = fast.next.next;

            // If they meet, cycle is present
            if (slow == fast) {
                // Reset slow to head
                slow = head;

                // Move both one step to find start of loop
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                // Return the starting node of loop
                return slow;
            }
        }

        // If no cycle found
        return null;
    }
}
