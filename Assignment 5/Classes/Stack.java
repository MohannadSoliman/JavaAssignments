package eg.edu.alexu.csd.datastructure.stack;

public class Stack implements IStack{
	public class Node{
		Object element;
		Node next;
		public Node()
		{
			this.element = null;
			this.next = null;
		}
	}
	int size = 0;
	Node head = null;
	public Object pop(){
		if(size == 0) throw new RuntimeException("Stack is empty!");
		Object popped = head.element;
		head = head.next;
		size--;
		return popped;
	}
	public Object peek() {
		if(size == 0) throw new RuntimeException("Stack is empty!");
		return head.element;
	}
	public void push(Object element) {
		size++;
		Node node = new Node();
		node.element = element;
		node.next = head;
		head = node;
	}
	public boolean isEmpty() {
		if(size == 0) return true;
		return false;
	}
	public int size() {
		return size;
	}
}
