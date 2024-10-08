import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
	Node head;
	Node tail;
	
	class Node{
		T data;
		Node prev;
		Node next;	
		
		Node(T val){
			data = val;
			next = null;
			prev = null;
		}
	}
	
	DoublyLinkedList(){
		head = null;
		tail = null;
	}
	
	public void insertAtBeginning(T val){
		Node newNode = new Node(val);
		newNode.next = head;
		//when is list empty
		if(head==null) {
			tail = newNode;
		}
		else { //when list is not empty
			head.prev = newNode;	
		}	
		head = newNode;
		
	}
	
	public void display() {
		if(head==null)
			System.out.println("List is empty");
		Node temp = head; //start from head
		while(temp != null) { //null implies end of list
			System.out.print(temp.data + " ");
			temp = temp.next; //jump to next
		}
		
	}
	
	public void displayRev() {
		if(head==null)
			System.out.println("List is empty");
		Node temp = tail; //start from head
		while(temp != null) { //null implies end of list
			System.out.print(temp.data + " ");
			temp = temp.prev; //jump to previous
		}
	}

	public void insertAtPos(int pos,T val) {
		if(pos==0)
		{
			insertAtBeginning(val);
			return;
		}
		
		Node newNode = new Node(val);
		Node temp = head;
		for(int i=1;i<pos;i++) { //jump to previous node
			temp = temp.next;
			if(temp==null) 
				throw new IndexOutOfBoundsException("Invalid Pos " + pos);
		}
		
		//reassigns the pointers
		newNode.next = temp.next;
		newNode.prev = temp;
		
		if(temp==tail)
			tail=newNode;
		else
			temp.next.prev = newNode;
		temp.next = newNode;
				
	}
	
	public void deleteAtPos(int pos) {
		
		if(head==null) 
			throw new IndexOutOfBoundsException("Deletion attempted on empty list ");
	
		if(pos==0) {
			head = head.next;
			if(head==null)
				tail=null;
			else
				head.prev = null;
			return;
		}
		
		Node temp = head;
		Node prev = null;
		
		//jump to find node
		for(int i=1;i<=pos;i++) {
			prev = temp; //keep track of previous node
			temp = temp.next; //jump to next node
			if(temp==null)
				throw new IndexOutOfBoundsException("Invalid position ");
				
		}
		
		prev.next = temp.next; //reassign the pointers
		if(temp.next==null)
			tail = prev;
		else
			temp.next.prev = prev;
	}
	
	public void deleteAtBeginning() {
		
		if(head==null) 
			throw new IndexOutOfBoundsException("Deletion attempted on empty list. ");
	
		head = head.next;
		if(head==null)
			tail=null;
		else
			head.prev = null;
		
	}
		
	public Iterator<T> iterator(){
		return new Iterator<T>() {
			Node temp = head;
			
			public boolean hasNext() {
				return temp!=null;
			}
			
			public T next() {
				T val = temp.data;
				temp = temp.next;
				return val;
				
			}
		};
	}
}
