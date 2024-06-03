package library;

import java.util.*;

/**
 * Class <b>BookTitleSet</b> represents a collection of book titles.
 *
 * @author A Jiayi
 * @version 1.0
 */
public class BookTitleSet {

	/** BookTitleSet is represented using a vector.*/
	private Vector<BookTitle> set = null;

	/**
	 * Class BookTitleSet constructor.
	 *
	 */
	public BookTitleSet() {
		set = new Vector<BookTitle>();
	}

	/**
	 * Class BookTitleSet constructor
	 * 
	 * @param initialSet Vector of objects to populate this set.
	 */
	public BookTitleSet(Vector<BookTitle> initialSet) {
		set = new Vector<BookTitle>(initialSet);
	}

	/**
	 * Returns the book title at the specified location in the set.
	 * 
	 * @param int index index of BookTitle to return
	 * @return BookTitle
	 */
	public BookTitle getBookTitleAt(int index) {
		return (BookTitle) set.get(index);
	}

	/**
	 * Returns the number of book titles in the set.
	 * 
	 * @return int
	 */
	public int getBookTitleCount() {
		return set.size();
	}

	/**
	 * Adds a book title to the set
	 * 
	 * @param bookTitle BookTitle book title to be added to the set.
	 * @return void
	 */
	public void addBookTitle(BookTitle bookTitle) {
		set.add(bookTitle);
	}

	/**
	 * Removes a book title at the specified index and returns it
	 * 
	 * @param index int index of book title to remove
	 * @return BookTitle
	 */
	public BookTitle removeBookTitleAt(int index) {
		return (BookTitle) set.remove(index);
	}

	/**
	 * Removes the input book title from the BookTitleSet
	 * 
	 * @param bookTitle BookTitle book title to remove
	 * @return boolean
	 */
	public boolean removeBookTitle(BookTitle bookTitle) {
		return set.remove(bookTitle);
	}
}
