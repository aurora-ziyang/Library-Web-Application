package library;

/**
 * A class representing a book title
 *
 * @author A Jiayi
 * @version 1.0
 */
public class BookTitle {

	protected String callNumber;
	protected String name;
	protected String authors;
	protected String isbn;
	protected String edition;
	protected String publisher;
	protected int publishedYear;

	/**
	 * class BookTitle constructor
	 *
	 */
	public BookTitle() {
	}

	/**
	 * class BookTitle constructor.
	 * 
	 * @param inCallNumber    String book's call number.
	 * @param inName          String book's name (title).
	 * @param inAuthors       String book's authors.
	 * @param inIsbn          String book's ISBN
	 * @param inEdition       String book's edition.
	 * @param inPublisher     String book's publisher.
	 * @param inPublishedYear int book's year.
	 *
	 */
	public BookTitle(String inCallNumber, String inName, String inAuthors, String inIsbn, String inEdition,
			String inPublisher, int inPublishedYear) {
		callNumber = inCallNumber;
		name = inName;
		authors = inAuthors;
		isbn = inIsbn;
		edition = inEdition;
		publisher = inPublisher;
		publishedYear = inPublishedYear;
	}

	/**
	 * Accessor for Call Number
	 * 
	 * @return String
	 */
	public String getCallNumber() {
		return callNumber;
	}

	/**
	 * Mutator for Call Number
	 * 
	 * @param inCN String input call number
	 * @return void
	 */
	public void setCallNumber(String inCN) {
		callNumber = inCN;
	}

	/**
	 * Accessor for Name
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Mutator for Name
	 * 
	 * @param inName String input name
	 * @return void
	 */
	public void setName(String inName) {
		name = inName;
	}

	/**
	 * Accessor for isbn
	 * 
	 * @return String
	 */
	public String getISBN() {
		return isbn;
	}

	/**
	 * Mutator for isbn
	 * 
	 * @param inIsbn String input isbn
	 * @return void
	 */
	public void setISBN(String inIsbn) {
		isbn = inIsbn;
	}

	/**
	 * Accessor for Edition
	 * 
	 * @return String
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * Mutator for Edition
	 * 
	 * @param inEdition String input edition
	 * @return void
	 */
	public void setEdition(String inEdition) {
		edition = inEdition;
	}

	/**
	 * Accessor for year of publication
	 * 
	 * @return int
	 */
	public int getPublishedYear() {
		return publishedYear;
	}

	/**
	 * Mutator for year of publication
	 * 
	 * @param inYear int input year
	 * @return void
	 */
	public void setYear(int inPublishedYear) {
		publishedYear = inPublishedYear;
	}

	/**
	 * Accessor for authors
	 * 
	 * @return String
	 *
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * Mutator for authors
	 * 
	 * @param inAuthors String input authors
	 * @return void
	 *
	 */
	public void setAuthors(String inAuthors) {
		authors = inAuthors;
	}

	/**
	 * Accessor for publisher
	 * 
	 * @return String
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Mutator for publisher
	 * 
	 * @param inPublisher String input publisher
	 * @return void
	 */
	public void setPublisher(String inPublisher) {
		publisher = inPublisher;
	}
}
