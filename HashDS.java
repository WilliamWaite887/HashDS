import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class HashDS<T> implements SequenceInterface<T> {
	private static final int defaultSize = 20;
	private ArrayList<LinkedList<T>> table;
	private List<Integer> otherLinkedList;
	private int size;
	private int head;
	private int tail;
	private static final int frequencyHashTableSize = 100;
	private freqEntry<T>[] frequencyTable;
	
	private static class freqEntry<T> {
		T key;
		int count;

		freqEntry(T key, int count){
			this.key = key;
			this.count = count;
		}
	}

// default constructor
	public HashDS() {
		table = new ArrayList<>(defaultSize);
		for (int i = 0; i < defaultSize; i++){
			table.add(new LinkedList<>());

		}
		otherLinkedList = new LinkedList<>();
		frequencyTable = new freqEntry[frequencyHashTableSize];
		size = 0;
		head = -1;
		tail = -1;
	}
//copy constructor

public HashDS(HashDS<T> other){
	table = new ArrayList<>(other.table.size());
	for (int i=0; i < other.table.size(); i++){
		LinkedList<T> firstList = other.table.get(i);
		LinkedList<T> newList = new LinkedList<>(firstList);
		table.add(newList);
	}
	otherLinkedList = new LinkedList<>(other.otherLinkedList);
	frequencyTable = new freqEntry[frequencyHashTableSize];
	size = other.size;
	head = other.head;
	tail = other.tail;
}

public int hash(T item){
	int code = item.hashCode();
	return Math.abs(code) % table.size();

}
// i need to make a separate hash table that keeps track of frequency because i dont know any other way to do frequency
// in O(1) time
public int freqHash(T item){
	int freqcode = item.hashCode();
	return Math.abs(freqcode) % frequencyHashTableSize;
}
// method to add values to the frequency hash table in order to keep track of the frequency
public void increaseFreq(T item){
	int i = freqHash(item);
	while(frequencyTable[i] != null){
		if(frequencyTable[i].key.equals(item)) {
			frequencyTable[i].count++;
			return;
		}
		i = (i+1)%frequencyHashTableSize; // im doing linear probing for this one
	}
	frequencyTable[i] = new freqEntry<>(item, 1);
}

public void decreaseFreq(T item){
	int i = freqHash(item);
	while(frequencyTable[i] != null){
		if(frequencyTable[i].key.equals(item)) {
			if(frequencyTable[i].count > 1){
				frequencyTable[i].count--;
			}else{
				frequencyTable[i] = null; // takes entry out if count is 0
			}
			return;
		}
		i = (i+1)%frequencyHashTableSize; // im doing linear probing for this one
	}
	
}

//tostring

public String toString() {
	StringBuilder result = new StringBuilder();
	for (int i : otherLinkedList) {
		LinkedList<T> list = table.get(i);
		for (T item : list) {
			result.append(item.toString());
		}
	}
	return result.toString();
}
/** Add a new item to the tail (logical end) of the SequenceInterface<T>
	 * Runtime: O(1)
	 * @param item the item to be added..
	 */
	public void append(T item){
		int i = hash(item);
		LinkedList<T> list = table.get(i);
		if (list.isEmpty()){
			otherLinkedList.add(i);
			if (head == -1 || head > i){
				head = i;
			}
			if (tail == -1 || i > tail){
				tail = i;
			}
		}
		list.add(item);
		size++;
		increaseFreq(item);
		
    }

	/** Add a new item to the head (logical beginning) of the SequenceInterface<T>
	 * Runtime: O(1)
	 * @param item the item to be added.
	 */
	public void prefix(T item){
		int i = hash(item);
		LinkedList<T> list = table.get(i);
		if (list.isEmpty()){
			otherLinkedList.add(0, i);
			if (head == -1 || head > i){
				head = i;
			}
			if (tail == -1){
				tail = i;
			}
		}

		list.addFirst(item);
		size++;
		increaseFreq(item);
		System.out.println(otherLinkedList);
    }
	
	/** Return the item at a given logical position in the SequenceInterface<T>
	 * Runtime: O(n)
	 * @param position the int logical position
	 * @return the T item at position
	 * @throws IndexOutOfBoundsException if position < 0
	                                     or position > size()-1
	 */
	public T itemAt(int position){
		if (position >= size || position < 0){
			throw new IndexOutOfBoundsException("out of bounds");
		}
		int count = 0;
		for (int i : otherLinkedList){
			LinkedList<T> list = table.get(i);
			for (T item : list){
				if (count == position){
					return item;
				}
				count++;
			}
		}
		return null;
    }

	/**
	 * Runtime: O(1)
	 * @return true if the SequenceInterface<T> is empty, and false otherwise
	 */
	public boolean isEmpty(){
		if (size == 0){
			return true;
		} 
		return false;
    }

	/**
	 * Runtime: O(1)
	 * @return the number of items currently in the SequenceInterface<T>
	 */
	public int size(){
		return size;
    }

	/**
	 * Runtime: O(1)
	 * @return the logical first item in the SequenceInterface<T> or null if the SequenceInterface<T> is empty
	 */
	public T first(){
		if (size == 0){
			return null;
		}
		return table.get(head).getFirst();
    }

	/**
	 * Runtime: O(1)
	 * @return the logical last item in the SequenceInterface<T> or null if the SequenceInterface<T> is empty,
	 */
	public T last(){
		if (size == 0){
			return null;
		}
		return table.get(tail).getLast();
    }

	/** Return the number of occurrences in the SequenceInterface<T> of a given item
	 * Runtime: O(1) on average
	 * @param item the T item
	 * @return the number of occurrences in the SequenceInterface<T> of item
	 */
	public int getFrequencyOf(T item){
		int i = freqHash(item);
		while (frequencyTable[i] != null){
			if (frequencyTable[i].key.equals(item)){
				return frequencyTable[i].count;
			}
			i = (i+1) % frequencyHashTableSize;
		}
		return 0;
    }

	/** Reset the SequenceInterface<T> to an empty Sequence.
	 * Runtime: O(1)
	 */
	public void clear(){
		table = new ArrayList<>(defaultSize);
		for (int i = 0; i < defaultSize; i++){
			table.add(new LinkedList<>());
		}
		size = 0;
    }

	/** Delete the first item of the SequenceInterface<T>
	 * Runtime: O(1)
	* @return the deleted item
	* @throws EmptySequenceException if the SequenceInterface<T> is empty
	*/
	public T deleteHead(){
		if (size == 0){
			throw new EmptySequenceException("empty sequence");
		}
			LinkedList<T> list = table.get(head);
			T item = list.removeFirst();
			size--;
			if (list.isEmpty()){
				otherLinkedList.remove((Integer) head);
				head = otherLinkedList.isEmpty() ? -1 : otherLinkedList.get(0);
			}
			
		return item;
    }

	/** Delete the last item of the SequenceInterface<T>
	 * Runtime: O(1)
	 * @return the deleted item
	 * @throws EmptySequenceException if the Sequence is empty
	 */
	public T deleteTail(){
		if (size == 0) {
			throw new EmptySequenceException("empty sequence");
		}
		while (table.get(tail).isEmpty()) {

			otherLinkedList.remove((Integer) tail);
			
			if (otherLinkedList.isEmpty()) {
				tail = -1;
				return null;
			}
			tail = otherLinkedList.get(otherLinkedList.size() - 1);
		}
		LinkedList<T> list = table.get(tail);
		T item = list.removeLast();
		size--;
		if (list.isEmpty()) {
			otherLinkedList.remove((Integer) tail);
			tail = otherLinkedList.isEmpty() ? -1 : otherLinkedList.get(otherLinkedList.size() - 1);
		}
		return item;
	}

	/** EXTRA CREDIT: Remove any occurrence of a given item from the SequenceInterface<T>
	 * Runtime: O(1) on average
	 * @param item the T item to be removed
	 * @return true if item existed and false otherwise
	 */
	public boolean remove(T item){
        return false;
    }

}