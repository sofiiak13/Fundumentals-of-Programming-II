public class A3LinkedList implements A3List {
	private A3Node head;
	private A3Node tail;
	private int length;
	

	public A3LinkedList() {
		head = null;
		tail = null;
		length = 0;
	}

	/* 
	 * Purpose: add s to the front of the list
	 * Parameters: String s - the string to add
	 * Returns: nothing
	 * Precondition: s is not null
	 */
	
	public void addFront(String s) {

		A3Node first = new A3Node(s);

		if (length == 0){
			tail = first;
		}else{
			head.prev = first;
			first.next = head;
		}
		
		head = first;
		length++;
	}

	/* 
	 * Purpose: add s to the back of the list
	 * Parameters: String s - the string to add
	 * Returns: nothing
	 * Precondition: s is not null
	 */
	public void addBack(String s) {
		A3Node last = new A3Node(s);

		if (length == 0) {
			head = last;
		} else {
			last.prev = tail;
			tail.next = last;
		}

		tail = last;
		length++;

	}
		
		
	
	public int size() {
		return length;
	}
	
	public boolean isEmpty() {
		return length==0;
	}
	
	/* 
	 * Purpose: removes the element from the front of the list
	 * Parameters: none
	 * Returns: nothing
	 */

	public void removeFront() {
		
		if (length == 1) {
			head = null;
			tail = null;
			length--;
		} else if (length != 0) {
			head = head.next;
			head.prev = null;
			length--;
		}
		
	}
	
	/* 
	 * Purpose: removes the element from the back of the list
	 * Parameters: none
	 * Returns: nothing
	 */
	public void removeBack() {
		if (length == 1){
			head = null;
			tail = null;
			length--;
		}else if (length != 0) {
			tail = tail.prev;
			tail.next = null;
			length--;
		}

	}

	
	/* 
	 * Purpose: remove the node at the given position from this list
	 * Parameters: int pos - the position of the node to remove
	                         (assuming 0-based positioning)
	 * Returns: void - nothing
	 *
	 * Note: If an invalid positions is given, no node is removed
	 *
	 * Note: there are no pre-conditions about the size of the list
	 *       or the value of the pos parameter
	 */
	
	public void removeAt(int pos) {
		if (pos == 0) {
			removeFront();
		} else if (pos == length-1){
			removeBack();
		} else if (0 < pos && pos< length) {
			A3Node cur = head;
			for (int i = 0; i < pos; i++) {
				cur = cur.next;
			}
			cur.next.prev = cur.prev;
			cur.prev.next = cur.next;
			length--;
		}
	}


	/* 
	 * Purpose: create and return a new A3LinkedList containing all the 
	 *          values from this list and other list, sorted alphabetically
	 * Parameters: A3LinkedList other - the other linked list
	 * Returns: A3LinkedList - a new linked list containing all of the values
	 *                         merged from this list and other list
	 *
	 * Pre-condition: the elements in both this list and other list are 
	 *                sorted alphabetically
	 * Post-condition: none of the elements in this list or other list
	 *                 have been modified or removed
	 *
	 * HINT: Look through the Node class for helpful methods
	 * Note: there are no pre-conditions about the size of either list
	 */
	public A3LinkedList mergeSorted(A3LinkedList other) {
		A3LinkedList merged = new A3LinkedList();

		A3Node cur = this.head;
		A3Node curOther = other.head;

		while (cur != null && curOther != null) {

			if (cur.comesBefore(curOther)) {
				merged.addBack(cur.getData());
				cur = cur.next;
			} else {
				merged.addBack(curOther.getData());
				curOther = curOther.next;
			}

		}

		while (cur != null) {
			merged.addBack(cur.getData());
			cur = cur.next;
		}

		while (curOther != null) {
			merged.addBack(curOther.getData());
			curOther = curOther.next;
		}

		return merged;
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from front to back
	 * Parameters: none
	 * Returns: nothing
	 *
	 * USED TO HELP YOU WITH DEBUGGING
	 * DO NOT CHANGE THIS METHOD
	 */
	public String frontToBack() {
		String result = "{";
		A3Node cur = head;
		while (cur != null) {
			result += cur.getData();
			cur = cur.next;
		}
		result += "}";
		return result;
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from back to front
	 * Parameters: none
	 * Returns: nothing
	 *
	 * USED TO HELP YOU WITH DEBUGGING
	 * DO NOT CHANGE THIS METHOD
	 */
	public String backToFront() {
		String result = "{";
		A3Node cur = tail;
		while (cur != null) {
			result += cur.getData();
			cur = cur.prev;
		}
		result += "}";
		return result;
	}

	
}
	