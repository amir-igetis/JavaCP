package striverAToZ.linkedList.learnOneDLinkedList;

public class InsertAtTheHeadOfALinkedList {

	/// Problem Statement: Given a linked list and an integer value val, insert a
	/// new node with that value at the beginning (before the head) of the list and
	/// return the updated linked list.
	///

	public static void main(String[] args) {
		Node head = new Node(2);
		head.next = new Node(3);

		System.out.print("Original List: ");
		printList(head);

		// Inserting new node at head
		head = insertAtHead(head, 1);

		System.out.print("After Insertion at Head: ");
		printList(head);
	}

	/// Time Complexity: O(1), creating a new node and updating the head takes
	/// constant time.
	/// Space Complexity: O(1) , only one extra node is created to insert at the
	/// head of the linked list.

	private // Node class to represent each node in the linked list
	static class Node {
		int data;
		Node next;

		// Constructor with data and next pointer
		Node(int data1, Node next1) {
			data = data1;
			next = next1;
		}

		// Constructor with only data
		Node(int data1) {
			data = data1;
			next = null;
		}
	}

	// Function to insert a new node at the head
	static Node insertAtHead(Node head, int newData) {
		// Create a new node whose next points to current head
		Node newNode = new Node(newData, head);
		// Return the new node as the head
		return newNode;
	}

	// Function to print the linked list
	private static void printList(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}
