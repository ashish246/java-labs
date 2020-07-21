package com.ds.list.doublylinked;

class Node {
	public int data;
	public Node next;
	public Node prev;
}

public class DoublyLinkedList {

	static Node start;

	public static void main(String[] args) {

		add(10);
		add(20);
		add(30);
		add(40);
		display();

		delete(30);

		display();

	}

	private static void add(int i) {
		// Create new node
		Node p = new Node();
		p.data = i;
		// Check if List is empty, If yes than point 'start' to the newly
		// created node
		if (start == null) {
			start = p;
		} else {
			// If the list is not empty, than traverse till the end of the list
			// and add the node
			Node s = start;
			while (s.next != null) {
				s = s.next;
			}
			s.next = p;
			p.prev = s;
		}

	}

	private static void display() {
		Node s = start;
		System.out.println("Data");
		while (s != null) {

			System.out.println(s.data);
			s = s.next;
		}

	}

	private static void delete(int i) {
		Node s = start;
		while (s.data != i) {
			s = s.next;
		}
		s.prev.next = s.next;
	}

}