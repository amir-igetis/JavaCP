package striverAToZ.linkedList.mediumProbs;

import java.util.Stack;

public class ReverseALinkedList {

	/// Question 2
	///
	/// Problem Statement: Given the head of a singly linked list, write a program
	/// to reverse the linked list, and return the head pointer to the reversed
	/// list.
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);

//        head = reverseList(head);
//
//        head = reverseListI(head);
		head = reverseListII(head);

		// Printing reversed list
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}

	}

	// brute

	/// Time Complexity: O(N) ,We traverse the linked list twice once to push all
	/// node values into the stack, and once to reassign values. Each traversal
	/// takes O(N) time, where N is the number of nodes.
	///
	/// Space Complexity: O(N) , We use an extra stack to store all the node values,
	/// which requires O(N) additional space.
	static Node reverseList(Node head) {
		// Stack to store values of nodes
		Stack<Integer> stack = new Stack<>();

		// Temporary pointer to traverse the list
		Node temp = head;

		// Traverse and push all node values to stack
		while (temp != null) {
			stack.push(temp.data);
			temp = temp.next;
		}

		// Reset temp back to head
		temp = head;

		// Reassign values from stack in reverse order
		while (temp != null) {
			temp.data = stack.pop();
			temp = temp.next;
		}

		// Return the modified head
		return head;
	}

	// optimal

	/// Time Complexity: O(N) Because we are traversing each node of the linked list
	/// exactly once. Each pointer reversal is done in constant time.
	///
	/// Space Complexity: O(1) We are not using any additional data structure or
	/// recursion. All modifications are done in-place using pointers.
	static Node reverseListI(Node head) {
		// Initialize previous pointer to null
		Node prev = null;

		// Start from the head of the list
		Node temp = head;

		// Traverse the list
		while (temp != null) {
			// Save the next node
			Node front = temp.next;

			// Reverse the current node's pointer
			temp.next = prev;

			// Move prev to current node
			prev = temp;

			// Move to the next node
			temp = front;
		}

		// Return new head (last node becomes first)
		return prev;
	}

	// recursive

	/// Time Complexity: O(n),Each node is visited exactly once during the recursive
	/// call, and we do constant-time work for each node (like flipping pointers).
	///
	/// Space Complexity: O(n),The recursion stack goes up to n levels deep (one for
	/// each node), which uses extra space on the call stack.
	static Node reverseListII(Node head) {
		// Base case: if list is empty or has only one node
		if (head == null || head.next == null)
			return head;

		// Recursively reverse the rest of the list
		Node newHead = reverseListII(head.next);

		// Store reference to next node
		Node front = head.next;

		// Make the next node point to current node
		front.next = head;

		// Break original forward link
		head.next = null;

		// Return new head of reversed list
		return newHead;
	}
}
