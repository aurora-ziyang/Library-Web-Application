package library;

import java.sql.*;

/**
 * This bean represents a heavyweight librarian.
 * 
 * @author A Jiayi
 * @version 1.0
 */
public class LibraryLibrarian {

	/**
	 * Class LibraruLibrarian constructor
	 */
	public LibraryLibrarian() {
	}

	/**
	 * Returns a librarian with the specified ssn from the database
	 * 
	 * @param ssn int ssn of librarian to create
	 * @retrun Librarian
	 */
	public static Librarian getLibrarian(int ssn) throws Exception {

		DBWrapper myConnection = DBWrapper.Instance();
		String sqlQuery = "select * from Librarian where ssn=" + ssn;
		ResultSet r = null;
		Librarian librarian = null;
		
		/** Get a librarian with snn from the database and return the librarian. */
		r = myConnection.runQuery(sqlQuery);
		if (r.next()) {
			librarian = new Librarian(r.getInt("ssn"), r.getString("lname"), r.getString("fname"),
					r.getString("street"), r.getString("city"), r.getString("state"), r.getString("pcode"),
					r.getString("phone"), r.getString("email"), r.getString("passwd"));
		}
		return librarian;
	}
}
