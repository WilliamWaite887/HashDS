
/**
 * Driver program for CS 0445 Assignment 4
 * 
 * @author Sherif Khattab (Adapted from Dr. John Ramirez's Spring 2017 CS 0445
 *         Assignment 1 code)
 *
 *         This program must work as is with your HashDS<T> class.
 *         Look carefully at all of the method calls so that
 *         you create your HashDS<T> methods correctly. For example,
 *         note the constructor calls and the toString() method call.
 *         The output should be identical to the sample output except for the
 *         predecessor method output.
 *         This can be verified by running the diff program (fc on Windows)
 *         between the provided expected output and the output of running this driver using
 *         your HashDS<T> implementation. The diff program shouldn't
 *         give any differences.
 */
public class Assig4Test {
	private static final int SIZE = 5;
	private static final int LARGE_SIZE = 1000000;

	public static void main(String[] args) {
		// Testing constructors and SequenceInterface<T> interface

		HashDS<Integer> s1 = new HashDS<>();

		// Testing append and toString()
		for (int i = 0; i < SIZE; i++) {
			s1.append(2 * i);
			System.out.println(2 * i + " appended to sequence");
		}

		System.out.println(s1);

		// Testing clear() and size()
		int sz = s1.size();
		System.out.println("Before clear(): " + sz + " item(s) in the sequence.");

		s1.clear();
		sz = s1.size();
		System.out.println("After clear(): " + sz + " item(s) in the sequence.");

		// Testing prefix and toString
		for (int i = 0; i < SIZE; i++) {
			s1.prefix(2 * i);
			System.out.println(2 * i + " prefixed to sequence");
		}
		System.out.println(s1);

		// Testing Copy Constructor
		HashDS<Integer> s2 = new HashDS<>(s1);
		System.out.println("After making a copy: " + s2);

		SequenceInterface<Integer> theS = new HashDS<>();

		for (int i = 0; i < SIZE; i++) {
			theS.append(2 * i);
			System.out.println(2 * i + " appended to sequence");
		}
		System.out.println(theS);

		// Testing deleteHead() and isEmpty()
		while (!theS.isEmpty()) {
			Integer oldItem = theS.deleteHead();
			System.out.println(oldItem + " retrieved from head of sequence");
		}
		try {
			theS.deleteHead();
		} catch (EmptySequenceException e) {
			System.out.println("Nothing in the sequence");
		}

		// Testing deleteTail()
		System.out.println(s1);
		while (!(s1.isEmpty())) {
			Integer oldItem = s1.deleteTail();
			System.out.println(oldItem + " retrieved from tail of sequence");
		}
		try {
			s1.deleteTail();
		} catch (EmptySequenceException e) {
			System.out.println("Nothing in the sequence");
		}

		// Alternating prefix, deleteHead, and deleteTail
		System.out.println("Starting from an empty sequence:");
		SequenceInterface<String> theS2 = new HashDS<>();
		for (int i = 0; i < 9; i++) {
			String theItem = new String("Item " + i);
			System.out.println("Prefixing " + theItem);
			theS2.prefix(theItem);
			theItem = theS2.deleteHead();
			System.out.println(theItem + " removed from Sequence head");
			System.out.print("Attempting deleteTail: ");
			try {
				theS2.deleteTail();
			} catch (EmptySequenceException e) {
				System.out.println("Nothing in the sequence");
			}
		}
		sz = theS2.size();
		System.out.println(sz + " item(s) in the sequence.");

		// Testing first, last, itemAt, getFrequencyOf
		SequenceInterface<Character> sentence = new HashDS<>();
		String s = "algorithms and data structures are fun";
		for (Character c : s.toCharArray()) {
			sentence.append(c);
		}
		System.out.println(sentence);
		System.out.println("The first letter is " + sentence.first());
		System.out.println("The last letter is " + sentence.last());

		for (int i = 0; i < sentence.size(); i++) {
			System.out.print("Character at index " + i + " is " + sentence.itemAt(i) + "; ");
			System.out.println("The letter " + sentence.itemAt(i) + " appeared "
					+ sentence.getFrequencyOf(sentence.itemAt(i)) + " times");
		}
		System.out.print("Attempting item at "+ sentence.size() + ": ");
		try {
			System.out.println("Character at index " + sentence.size() + " is " + sentence.itemAt(sentence.size()));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException");
		}
		System.out.println("The letter " + 'z' + " appeared " + sentence.getFrequencyOf('z') + " times");

		System.out.println(sentence);
		int n = 8;
		System.out.print("Deleting " + n + " characters from tail: ");
		for(int i=0; i<n; i++){
			sentence.deleteTail();
		}
		System.out.println(sentence);
		// Testing remove

		String str = " one is fun";
		System.out.print("Appending \"" + str + "\": ") ;
		for (Character c : str.toCharArray()) {
			sentence.append(c);
		}
		System.out.println(sentence);

		char c = 'a';
		System.out.println("Removing all '" + c + "'s: ");
		while(sentence.remove(c)){
			System.out.println("Removing one " + c);
		}
		System.out.println(sentence);

		// Testing with a large sequence
		System.out.println("Creating a large sequence with " + LARGE_SIZE + " items");
		SequenceInterface<Integer> largeNumber = new HashDS<>();
		int different = 100;
		for (int i = 0; i < LARGE_SIZE; i++) {
			if(i % (LARGE_SIZE/different) == 0){
				largeNumber.append(0);

			} else {
				largeNumber.append(9);
			}
		}
		// System.out.println(largeNumber);
		System.out.println("The size of the sequence is " + largeNumber.size());

		System.out.print("Removing " + different + " items: ");

		int d = 0;
		while(largeNumber.remove(d)){
		}
		System.out.println("The size of the sequence is " + largeNumber.size());
	}
}