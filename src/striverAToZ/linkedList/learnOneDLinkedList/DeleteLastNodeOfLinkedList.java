package striverAToZ.linkedList.learnOneDLinkedList;

public class DeleteLastNodeOfLinkedList {

	/// Problem Statement: Given a Linked List, delete the tail of the list and
	/// print the updated list.
	///
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);

		head = deleteTail(head);

		// Print list after deletion
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}

	}

	/// Time Complexity: O(N), we traverse the entire linked list once to delete the
	/// tail of the list.
	///
	/// Space Complexity: O(1) , constant amount of extra space is used.

	static Node deleteTail(Node head) {
		// If list is empty or has one node
		if (head == null || head.next == null) {
			return null;
		}

		// Traverse to the second last node
		Node curr = head;
		while (curr.next.next != null) {
			curr = curr.next;
		}

		// Delete tail node
		curr.next = null;

		// Return updated head
		return head;
	}
}
