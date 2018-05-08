import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] things;
	private int size;



	public RandomizedQueue(){
		// construct an empty randomized queue
		things = (Item[]) new Object[1];
		size = 0;
	}
	public boolean isEmpty(){
		// is the queue empty?
		return size == 0;
	}
	public int size(){
		// return the number of items on the queue
		return size;
	}
	public void enqueue(Item item){
		// add the item
		if(item == null) throw new java.lang.NullPointerException("Please add something that would work");
		if(things[size] == null) things[size] = item;
		size++;
		if(size == things.length){
			Item[] newthings = (Item[])new Object[size*2];
			for(int i = 0; i < size; i++){
				newthings[i] = things[i];
			}
			things = newthings;
		}
	}
	public Item dequeue(){
		// remove and return a random item
		if(this.isEmpty()) throw new java.util.NoSuchElementException("The Queue is empty");
		int index = StdRandom.uniform(size);
		Item fin = things[index];
		size--;
		things[index] = things[size];
		if(size <= things.length / 4){
			Item[] newarray = (Item[]) new Object[things.length / 2];
			for(int i = 0; i < size; i++){
				newarray[i] = things[i];
			}
			things = newarray;
		}
		
		
		
		return fin;
	}
	public Item sample(){
		// return a random item (but do not remove it)
		if(this.isEmpty()) throw new java.util.NoSuchElementException("The Queue is empty");
		int index = StdRandom.uniform(size);
		return things[index];
	}
	public Iterator<Item> iterator(){
		// return an independent iterator over items in random order
        return new ReverseArrayIterator();
	}
	
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] save;
        public ReverseArrayIterator() {
            i = size-1;
            save = (Item[])new Object[size];
            for(int j = 0; j < size; j++){
            	save[j] = things[j];
            }
            StdRandom.shuffle(save);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return save[i--];
        }
    }
	
	public static void main(String[] args){
		// unit testing (required)
		RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
		test.enqueue(1);
		test.enqueue(2);
		test.enqueue(3);
		test.enqueue(4);
		test.enqueue(5);
		test.enqueue(6);
		test.enqueue(7);
		test.enqueue(8);
		test.enqueue(9);
		test.enqueue(10);
		test.enqueue(11);
		test.enqueue(12);
		test.enqueue(13);
		for(int i = 0; i < 12; i++){
			test.dequeue();
		}
		System.out.println(test.sample());
		System.out.println(test.sample());
		System.out.println(test.sample());
		
	}
}