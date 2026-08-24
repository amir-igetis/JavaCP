package striverAToZ.linkedList.mediumProbs;

import java.util.HashMap;
import java.util.Map;

public class DetectLoopInALinkedList {

    /// Question 4
    ///
    /// Problem Statement: Given a Linked List, determine whether the linked list contains a cycle or not.

    public static void main(String[] args) {
        Node head = new Node(1);
        Node sec = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        head.next = sec;
        sec.next = third;
        third.next = fourth;
        fourth.next = fifth;

        fifth.next = third;

        if (detectLoop(head)) {
            System.out.println("Loop detected in the Linked list ");
        } else {
            System.out.println("No loop detected in the Linked list ");
        }

        if (hasCycleI(head)) {
            System.out.println("Loop detected in the Linked list Optimal ");
        } else {
            System.out.println("No loop detected in the Linked list Optimal ");
        }
    }

    // brute

    /// Time Complexity: O(N*LogN), we traverse the entire linked list once and store and retrieve nodes from the hash map. Map operations have a worst time space complexiy of O(LogN).
    /// Space Complexity: O(N) , additional amount of extra space is used to store nodes in a hash map.
    static boolean detectLoop(Node head) {
        // Initialize a pointer at head
        Node temp = head;

        // Map to keep track of visited nodes
        Map<Node, Integer> nodeMap = new HashMap<>();

        // Traverse the linked list
        while (temp != null) {

            // If node already exists in map, loop detected
            if (nodeMap.containsKey(temp)) {
                return true;
            }

            // Store the current node in map
            nodeMap.put(temp, 1);

            // Move to the next node
            temp = temp.next;
        }

        // If traversal completes, no loop detected
        return false;
    }

    // optimal

    /// Time Complexity: O(N), we traverse the entire linked list once. The fast pointer either reaches the end of the list or meets the slow pointer in linear time.
    /// Space Complexity: O(1) , constant amount of extra space is used detect a cycle using Floyd's Cycle Detection Algorithm.
    static boolean hasCycleI(Node head) {
        // Initialize two pointers, slow and fast,
        // to the head of the linked list
        Node slow = head;
        Node fast = head;

        // Step 2: Traverse the linked list with
        // the slow and fast pointers
        while (fast != null && fast.next != null) {
            // Move slow one step
            slow = slow.next;
            // Move fast two steps
            fast = fast.next.next;

            // Check if slow and fast pointers meet
            if (slow == fast) {
                return true;  // Loop detected
            }
        }

        // If fast reaches the end of the list,
        // there is no loop
        return false;
    }
}
