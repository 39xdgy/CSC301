import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node head;
	private Node tail;
	
	private class Node{
		private Item item;
		private Node next;
		private Node before;
	}
	
	
	
	public Deque(){
		// construct an empty deque
		head = null;
		tail = head;
		size = 0;
	}
	public boolean isEmpty(){
		// is the deque empty?
		return size == 0;
	}
	public int size(){
		// return the number of items on the deque
		return size;
	}
	public void addFirst(Item item){
		// add the item to the front
		if (item == null) throw new java.lang.NullPointerException("Please give something as an input");
		if(this.size() == 0){
			head = new Node();
			head.item = item;
			tail = head;
			size++;
		}
		else if(this.size() == 1){
			head = new Node();
			head.item = item;
			head.next = tail;
			tail.before = head;
			size++;
		}
		else{
			Node newhead = new Node();
			newhead.item = item;
			newhead.next = head;
			head.before = newhead;
			head = newhead;
		}
	}
	public void addLast(Item item){
		// add the item to the end
		if(this.size() == 0){
			head = new Node();
			head.item = item;
			tail = head;
			size++;
		}
		if(this.size() == 1){
			tail = new Node();
			tail.item = item;
			head.next = tail;
			tail.before = head;
			size++;
		}
		else{
			Node newtail = new Node();
			newtail.item = item;
			tail.next = newtail;
			newtail.before = tail;
			tail = newtail;
			size++;
		}
	}
	public Item removeFirst(){
		// remove and return the item from the front
		Item fin;
		if(this.size() == 0) throw new java.util.NoSuchElementException("The Deque is already empty");
		if(this.size() == 1){
			fin = head.item;
			head = head.next;
			tail = head;
			size--;
		}
		else{
			fin = head.item;
			head = head.next;
			head.before = null;
			size--;
		}
		return fin;
	}
	public Item removeLast(){
		// remove and return the item from the end
		Item fin;
		if(this.size() == 1){
			fin = tail.item;
			head = head.next;
			tail = head;
			size--;
		}
		else{
			fin = tail.item;
			Node newlast = tail.before;
			tail = newlast;
			tail.next = null;
		}
		return fin;
	}
	public Iterator<Item> iterator(){
		// return an iterator over items in order from front to end
		return new ListIterator();
	}
	
    private class ListIterator implements Iterator<Item> {
        private Node current = head;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
	public static void main(String[] args){
		// unit testing (required)
		Deque<Integer> test = new Deque<Integer>();
		test.addFirst(1);
		test.addLast(2);
		test.addLast(3);
		test.addLast(4);
		test.addFirst(5);
		test.removeFirst();
		test.addFirst(6);
		test.removeLast();
		for(Integer i : test){
			System.out.println(i);
		}
		
		
	}
}