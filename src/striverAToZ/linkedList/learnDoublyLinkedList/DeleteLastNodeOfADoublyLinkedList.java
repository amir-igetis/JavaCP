package striverAToZ.linkedList.learnDoublyLinkedList;

public class DeleteLastNodeOfADoublyLinkedList {

	/// Problem Statement: Given a Doubly Linked List, delete the last node of the
	/// Doubly Linked List.

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.back = head;
		head.next.next = new Node(3);
		head.next.next.back = head.next;
		head = deleteTail(head);

		// Print list after deletion
		Node curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
	}

	/// Time Complexity: O(N), we traverse the entire linked list once to delete the
	/// tail of the list.
	/// Space Complexity: O(1) , constant amount of extra space is used.

	static Node deleteTail(Node head) {
		// If list is empty
		if (head == null)
			return null;

		// If only one node present
		if (head.next == null)
			return null;

		// Traverse to the last node
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}

		// Update second last node's next to null
		temp.back.next = null;

		// Return head
		return head;
	}
}
