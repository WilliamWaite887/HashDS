# CS 0445 – Algorithms and Data Structures 1 – Assignment#4 [^1]

**Due: Friday, July 26th @ 11:59pm**

You should submit `HashDS.java` to GradeScope (the link is on
Canvas). You must also submit an Assignment Information Sheet `InfoSheet.md` as described in the Submission
Requirements section below.

**Late submission deadline: Sunday July 28th @11:59pm with 10% penalty per late day**

## TABLE OF CONTENTS

- [Overview](#overview)
- [HashDS](#HashDS)
- [Extra Credit](#Extra-Credit)
- [Submission Requirements](#submission-requirements)
- [Rubrics](#rubrics)

## OVERVIEW

**Purpose:** To sharpen your Java programming skills and to practice working with Hash Tables. Specifically, you will work with control
structures, class-building, interfaces and generics to **create** a data structure that uses hashing to implement an Abstract Data Type (ADT).

- **Task:** To design and implement a generic class `HashDS<T>` that will act as a data structure for maintaining
sequences of Java Objects. Your `HashDS<T>` class will primarily implement **one** interface –
`SequenceInterface<T>`. The details of the interface is explained in the file `SequenceInterface.java`. **Read the file carefully before implementing
your `HashDS<T>` class.**


[^1]: Assignment description adapted from Dr. John Ramirez’s CS 0445 class.


## HashDS

For the details on the functionality of your `HashDS<T>` class, carefully read the file
`SequenceInterface.java`. You must use the interface file as specified and **cannot remove/alter any of
the code already written in it**. There are different ways of implementing the
`SequenceInterface<T>` interface methods, some of which are more efficient than
others. Could you think of the best way to implement these methods to comply with the specified runtimes? A lot of pencil-and-paper work is
recommended before starting to write your code. Your `HashDS<T>` class header should be:

`public class HashDS<T> implements SequenceInterface<T> {`

**Important Note: The primary data within your `HashDS<T>` class *must be* a _Hash Table_** of your own design. You can use code from the code handouts, but you cannot use Java HashTable implementations. Besides the Hash Table, you may use other data structures (e.g., a predefined Java collection class, such as `ArrayList`).

You must add instance variables and named constants inside the `HashDS<T>` class to keep the state of the HashDS<T> and to follow the secure programming practices we mentioned in class.

To illustrate the semantics of the interface, let's have an example.

Let's take the sequence `9875732732` as an example. This is a sequence of `Integer` objects, which can be defined as follows:

```java
SequenceInterface<Integer> number = new HashDS<>();
```

Here are some properties of the sequence in the example above. Please match these with the methods
in `SequenceInterface.java` and `ReorderInterface.java`.

```java
size() == 10
isEmpty() == false
first() == 9
last() == 2
getFrequencyOf(3) == 2
itemAt(3) == 5
number.append(0)
number.toString() == "98757327320"
number.prefix(1)
number.toString() == "198757327320"
number.deleteHead()
number.toString() == "98757327320"
number.deleteTail()
number.toString() == "9875732732"
```

Besides the methods of `SequenceInterface<T>`, you will also need to write the following constructors:

```java
public HashDS()
public HashDS(HashDS<T> other)
```

The first constructor initializes the underlying data structure(s). The second constructor (copy constructor) initializes the `HashDS` object as a deep copy of the argument `other`.

Finally, you will need to override the following method:

`public String toString();`

This method will return a `String` that is the concatenation of all of the items in the Sequence without spaces. For example, if an `HashDS` object contains the numbers `1, 2, 3, 4, 5, 6`, toString() should output: `"123456"`.


After you have finished your coding of `HashDS<T>`, the `Assig4Test.java` file provided for you should compile and run correctly and should give output identical to the output shown in the file `A4Out.txt`. Please note that this statement doesn't suggest that you delay testing until you are done with all the methods of `HashDS`. Instead, you should use stubs and incrementally test your code using `Assig4Test.java` as you code up each of the methods. Think carefully about the order of implementing the methods of `HashDS` as _some methods can make other methods easier to implement_.


## Coding Style and Documentation

Please check [this guide](https://introcs.cs.princeton.edu/java/11style/) for directions regarding the expected coding style and documentation. For Javadoc comments, please refer to Appendix A of the textbook.

## EXTRA CREDIT

There is one method denoted as EXTRA CREDIT in the interface file. It is worth 5 extra points ONLY if its runtime is as specified.

## SUBMISSION REQUIREMENTS

The only source file that will be graded is:

1. `HashDS.java`

You must submit the above file together with an edited version of `InfoSheet.md`.

The idea from your submission is that the autograder can compile and run your code **from the command line** WITHOUT ANY additional files or changes, so be
sure to test them thoroughly before submitting it.

**Note: If you use an IDE such as NetBeans, Eclipse, or IntelliJ, to develop your programs, make sure
they will compile and run on the command line before submitting – this may require some
modifications to your program (such as removing some package information).**


## RUBRICS

Please note that if an autograder is available, its score will be used as a guidance for the TA, not as an official final score. Please also note that the autograder rubrics are the definitive rubrics for the assignment. The rubrics below will be used by the TA to assign partial credit in case your code scored less than 60% of the autograder score. If your code is manually graded for partial credit, the maximum you can get for the autograded part is 60%.

| Item | Grade |
|------|-------|
|Autograder Score| 93|
|Assignment Information Sheet and correct submission | 2 |
| Javadoc comments and coding style  | 5 |
| Extra credit  | 5 |
