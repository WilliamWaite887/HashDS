/**  SequenceInterface<T> interface for CS 0445 Assignment 4
 * @author Sherif Khattab
 * @param <T> The data type of the items stored in the sequence.
 *
 * Carefully read the specifications for each of the operations and
 * Please make sure to implement them correctly in your LinkedDS class.
 *
 * A Sequence stores an unlimited number of items in a certain logical order. The head of the Sequence is 
 * its first item and the Sequence's tail is its last item. Items can only be added 
 * to the beginning (using the prefix operation) or to the end (using the tail operation) of 
 * the Sequence. The items in a Sequence are not necessarily unique.
 */

 public interface SequenceInterface<T> {
	/** Add a new item to the tail (logical end) of the SequenceInterface<T>
	 * Runtime: O(1)
	 * @param item the item to be added.
	 */
	public void append(T item);

	/** Add a new item to the head (logical beginning) of the SequenceInterface<T>
	 * Runtime: O(1)
	 * @param item the item to be added.
	 */
	public void prefix(T item);
	
	/** Return the item at a given logical position in the SequenceInterface<T>
	 * Runtime: O(n)
	 * @param position the int logical position
	 * @return the T item at position
	 * @throws IndexOutOfBoundsException if position < 0
	                                     or position > size()-1
	 */
	public T itemAt(int position);

	/**
	 * Runtime: O(1)
	 * @return true if the SequenceInterface<T> is empty, and false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Runtime: O(1)
	 * @return the number of items currently in the SequenceInterface<T>
	 */
	public int size();

	/**
	 * Runtime: O(1)
	 * @return the logical first item in the SequenceInterface<T> or null if the SequenceInterface<T> is empty
	 */
	public T first();

	/**
	 * Runtime: O(1)
	 * @return the logical last item in the SequenceInterface<T> or null if the SequenceInterface<T> is empty,
	 */
	public T last();

	/** Return the number of occurrences in the SequenceInterface<T> of a given item
	 * Runtime: O(1) on average
	 * @param item the T item
	 * @return the number of occurrences in the SequenceInterface<T> of item
	 */
	public int getFrequencyOf(T item);

	/** Reset the SequenceInterface<T> to an empty Sequence.
	 * Runtime: O(1)
	 */
	public void clear();

	/** Delete the first item of the SequenceInterface<T>
	 * Runtime: O(1)
	* @return the deleted item
	* @throws EmptySequenceException if the SequenceInterface<T> is empty
	*/
	public T deleteHead();

	/** Delete the last item of the SequenceInterface<T>
	 * Runtime: O(1)
	 * @return the deleted item
	 * @throws EmptySequenceException if the Sequence is empty
	 */
	public T deleteTail();

	/** EXTRA CREDIT: Remove any occurrence of a given item from the SequenceInterface<T>
	 * Runtime: O(1) on average
	 * @param item the T item to be removed
	 * @return true if item existed and false otherwise
	 */
	public boolean remove(T item);

}