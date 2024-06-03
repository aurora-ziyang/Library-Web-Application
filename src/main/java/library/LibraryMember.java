package library;

import java.sql.*;

/**
 * This bean represents a heavy weight library member.
 *
 * @author A Jiayi
 * @version 1.0
 */
public class LibraryMember {

	/**
	 * Class constructor
	 */
	public LibraryMember() {
	}

	/**
	 * Returns a member with specified ssn
	 * 
	 * @param ssn int ssn of member
	 * @return Member
	 */
	public static Member getMember(int ssn) throws Exception {

		DBWrapper myConnection = DBWrapper.Instance();
		String sqlQuery = "select * from member where ssn=" + ssn;
		ResultSet r = null;
		Member member = null;

		/** Get a member with ssn from the database and build a new member object. */
		r = myConnection.runQuery(sqlQuery);
		if (r.next()) {
			member = new Member(r.getInt("ssn"), r.getString("lname"), r.getString("fname"), r.getString("street"),
					r.getString("city"), r.getString("state"), r.getString("pcode"), r.getString("phone"),
					r.getString("email"), r.getString("passwd"), r.getString("driverlicstate"),
					r.getString("driverlicnum"));
		}
		return member;
	}

	/**
	 * Returns the number of holds held by the specified member from the database
	 * 
	 * @param member Member member to check
	 * @return int
	 */
	public static int getMemberHoldCount(Member member) throws Exception {

		int count = 0;
		DBWrapper myConnection = DBWrapper.Instance();
		String sqlQuery = "select count(*) as counted from hold where ssn= " + member.getSSN() + " group by ssn";
		ResultSet r = null;
		
		/** Get hold number from the database and return it. */
		r = myConnection.runQuery(sqlQuery);
		if (r.next()) {
			count = r.getInt("counted");
		}

		return count;
	}

	/**
	 * Returns a hold set of all holds held by the specified member from the database
	 * 
	 * @param member Member member to check
	 * @return HoldSet
	 */
	public static HoldSet getMemberHolds(Member member) throws Exception {

		HoldSet currentHolds = new HoldSet();
		DBWrapper myConnection = DBWrapper.Instance();
		String sqlQuery = "SELECT * FROM Hold WHERE ssn = " + member.getSSN();
		ResultSet r = null;

		/** Get hole set from the database and return it. */
		r = myConnection.runQuery(sqlQuery);
		while (r.next()) {
			currentHolds.addHold(new Hold(r.getString("callnumber"), r.getInt("ssn"), r.getDate("holdDate")));
		}

		return currentHolds;
	}

	/**
	 * Returns the number of books checked out for the specified member from the database
	 * 
	 * @param member Member member to check
	 * @return int
	 */
	public static int getCheckedOutBookCount(Member member) throws Exception {

		int count = 0;
		DBWrapper myConnection = DBWrapper.Instance();
		String sqlQuery = "SELECT Count(*) as counted FROM Book WHERE borrowerssn = " + member.getSSN();
		ResultSet r = null;

		/** Get book count from the database and return it. */
		r = myConnection.runQuery(sqlQuery);
		if (r.next()) {
			count = r.getInt("counted");
		}

		return count;
	}

	/**
	 * Returns the set of books currently checked out by a member from the database
	 * 
	 * @param member Member member to check
	 * @return BookSet
	 */
	public static BookSet getCheckedOutBooks(Member member) throws Exception {

		BookSet currentBooks = new BookSet();
		String sqlQuery = "SELECT bookid FROM Book WHERE borrowerssn = " + member.getSSN();
		DBWrapper myConnection = DBWrapper.Instance();
		ResultSet r = null;

		/** Get book info from the database and build a new book object. */
		r = myConnection.runQuery(sqlQuery);
		while (r.next()) {
			currentBooks.addBook(LibraryBook.getBook(r.getInt("bookid")));
		}
		return currentBooks;
	}
}
