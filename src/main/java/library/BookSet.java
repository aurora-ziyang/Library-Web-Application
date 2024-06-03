package library;

import java.util.*;

/**
 * Class <b>BookSet</b> represents a collection of books.
 *
 * @author A Jaiyi
 * @version 1.0
 */

public class BookSet {

	/** The set is maintained in a vector. */
	private Vector<Book> set;

	/**
	 * Class BookSet constructor.
	 *
	 */
	public BookSet() {
		set = new Vector<Book>();
	}

	/**
	 * Class BookSet constructor.
	 * 
	 * @param initalSet Vector vector of objects to initialize this set.
	 */
	public BookSet(Vector<Book> initalSet) {
		set = new Vector<Book>(initalSet);
	}

	/**
	 * Returns the book at the specified location in the set.
	 * 
	 * @param int index index of Book to return
	 * @return Book
	 */
	public Book getBookAt(int index) {
		return (Book) set.get(index);
	}

	/**
	 * Returns the number of books in the set.
	 * 
	 * @return int
	 */
	public int getBookCount() {
		return set.size();
	}

	/**
	 * Adds a book to the set
	 * 
	 * @param book Book book to be added to the set.
	 * @return void
	 */
	public void addBook(Book book) {
		set.add(book);
	}

	/**
	 * Removes a book at the specified index and returns it
	 * 
	 * @param index int index of book to remove
	 * @return Book
	 */
	public Book removeBookAt(int index) {
		return (Book) set.remove(index);
	}

	/**
	 * Removes the input book from the bookset
	 * 
	 * @param book Book book to remove
	 * @return boolean
	 */
	public boolean removeBook(Book book) {
		return set.remove(book);
	}
}
