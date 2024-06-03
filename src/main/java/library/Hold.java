package library;

/**
 * A class representing a hold.
 *
 * @author A Jiayi
 * @version 1.0
 *
 */
public class Hold {

	private String callNumber;
	private Integer holderSSN;
	private java.sql.Date holdDate;

	/**
	 * Class Hold constructor
	 *
	 */
	public Hold() {
	}

	/**
	 * Class Hold constructor
	 * 
	 * @param inCallNumber String input call number
	 * @param inHolder     String input holder ssn
	 * @param inHoldDate   java.sql.Timestamp input hold date and time
	 */
	public Hold(String inCallNumber, Integer inHolder, java.sql.Date inHoldDate) {
		callNumber = inCallNumber;
		holderSSN = inHolder;
		holdDate = inHoldDate;
	}

	/**
	 * Accessor for call number
	 * 
	 * @return String
	 */
	public String getCallNumber() {
		return callNumber;
	}

	/**
	 * Mutator for call number
	 * 
	 * @param inCallNumber String input call number
	 * @return void
	 */
	public void setCallNumber(String inCallNumber) {
		callNumber = inCallNumber;
	}

	/**
	 * Accessor for holder's ssn
	 * 
	 * @return String
	 */
	public Integer getHolderSSN() {
		return holderSSN;
	}

	/**
	 * Mutator for holder's ssn
	 * 
	 * @param inHolder String new holder's ssn
	 * @return void
	 */
	public void setHolderSSN(Integer inHolder) {
		holderSSN = inHolder;
	}

	/**
	 * Accessor for hold date and time
	 * 
	 * @return java.sql.Timestamp
	 */
	public java.sql.Date getHoldDate() {
		return holdDate;
	}

	/**
	 * Mutator for hold date and time
	 * 
	 * @param inDateTime java.sql.Timestamp new date and time
	 * @return void
	 */
	public void setHoldDate(java.sql.Date inDate) {
		holdDate = inDate;
	}
}
