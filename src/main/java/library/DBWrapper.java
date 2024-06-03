package library;

import java.sql.*;

/**
 * Class <b>DBWrapper</b> contains wrapper routines for using JDBC to access the
 * database.
 * 
 * @author A Jiayi
 * @version 1.0
 */
public class DBWrapper {

	private static int CONNECTION_RETRIES = 10;
	private static int QUERY_RETRIES = 10;

	private String jdbcClassName;
	private String dbUrl;
	private String password;
	private String username;

	private Connection dbCon;

	private boolean hasError = false;
	private String errorString = null;
	private static DBWrapper myInstance;

	/**
	 * DBWrapper constructor
	 */
	private DBWrapper() {}

	/**
	 * DBWrapper conscrutor
	 * 
	 * @param inUrl
	 *            String url of database
	 * @param inJdbcClassName
	 *            String containing name of jdbc driver
	 * @param inUserName
	 *            String containing database username
	 * @param inPassWord
	 *            String containing database password
	 */
	public DBWrapper(String inUrl, String inJdbcClassName, String inUserName,
			String inPassWord) throws Exception {
		dbUrl = inUrl;
		jdbcClassName = inJdbcClassName;
		username = inUserName;
		password = inPassWord;
		connect();
	}

	/**
	 * Create a connection to the CTE library using
	 * the default connection parameters.
	 * 
	 * @return void
	 */
	public void connectAsDefaultCteLibrary() throws Exception {

		jdbcClassName = "com.mysql.cj.jdbc.Driver";
		dbUrl = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Shanghai&useTimezone=true";
		username = "root";
		password = "123456";

		closeConnections();
		connect();
	}

//	/**
//	 * Create a connection to the CTE library using
//	 * the default connection parameters.
//	 * 
//	 * @return void
//	 */
//	public void connectAsDefaultCteLibrary() throws Exception {
//
//		jdbcClassName = "org.postgresql.Driver";
//		dbUrl = "jdbc:postgresql://localhost:5432/libraryDB";
//		username = "postgres";
//		password = "123456";
//
//		closeConnections();
//		connect();
//	}
	
	/**
	 * closeConnections closes any currently open connections
	 * 
	 * @return void
	 */
	private void closeConnections() throws Exception {
		if (dbCon != null) {
			dbCon.close();
		}
	}

	/**
	 * DBWrapper Instance() Get a singleton instance of the DBWrapper object.
	 * 
	 * @return DBWrapper
	 */
	public static DBWrapper Instance() throws Exception {

		if (myInstance == null) {
			myInstance = new DBWrapper();
			myInstance.connectAsDefaultCteLibrary();
		}
		return myInstance;
	}

	/**
	 * boolean connect() Connect to a database using the parameters supplied in
	 * the constructor.
	 * 
	 * @return boolean
	 */
	private boolean connect() throws Exception {

		boolean opened = false;

		// Set the JDBC class name.
		//Driver driver = (Driver) Class.forName(jdbcClassName).newInstance();
		Driver driver = (Driver) Class.forName(jdbcClassName).getDeclaredConstructor().newInstance();
		DriverManager.registerDriver(driver);

		// Try to open a connection the database.
		int retry = 0;
		while (retry++ <= CONNECTION_RETRIES) {
			dbCon = DriverManager.getConnection(dbUrl, username, password);
			opened = true;
			break;
		}
		return opened;
	}

	/**
	 * boolean connect() Connect to a JDBC datasource without using the
	 * parameters supplied in the constructor.
	 * 
	 * @param inUrl
	 *            String url of database
	 * @param inJdbcClassName
	 *            String containing name of jdbc driver
	 * @param inUserName
	 *            String containing database username
	 * @param inPassWord
	 *            String containing database password
	 * @return boolean
	 */
	 public boolean connect( String inUrl, String inJdbcClassName, String
	 inUserName, String inPassWord )
	 throws Exception {
	 dbUrl = inUrl;
	 jdbcClassName = inJdbcClassName;
	 username = inUserName;
	 password = inPassWord;
	 closeConnections();
	
	 return connect();
	 }

	/**
	 * ResultSet runQuery() Executes a query and returns a resultset.
	 * 
	 * @param String
	 *            containing a SQL statement
	 * @return ResultSet
	 */
	public ResultSet runQuery(String sqlQuery) throws Exception {

		int retry = 0;

		ResultSet resultSet = null;
		Statement dbStatement = null;
		while (retry++ < QUERY_RETRIES) {
			dbStatement = dbCon.createStatement();
			resultSet = dbStatement.executeQuery(sqlQuery);
			break;
		}
		return resultSet;
	}

	/**
	 * boolean runUpdate() Executes an update and returns true of successfully
	 * executed.
	 * 
	 * @param String
	 *            containing a SQL statement
	 * @return boolean
	 */
	public boolean runUpdate(String sqlQuery) throws Exception {

		int retry = 0;
		boolean wasExecuted = false;

		// Create the Statement object.
		Statement dbStatement = null;
		while (retry++ < QUERY_RETRIES) {
			// Connect to the database.
			dbStatement = dbCon.createStatement();
			// Run the update.
			dbStatement.executeUpdate(sqlQuery);
			wasExecuted = true;
		}
		return wasExecuted;
	}

	/**
	 * ResultSet runChainedQuery() Executes a chained mode transaction query.
	 * 
	 * @param <b>String</b> containing a SQL statement
	 * @param <b>String</b> containing the isolation level to run the
	 *        transaction.
	 * @return ResultSet
	 */
	public ResultSet runChainedQuery(String sqlQuery, String isolationLevel)
			throws Exception {

		int retry = 0;

		// Create the resultset and statement object.
		ResultSet resultSet = null;
		Statement dbStatement = null;
		// Connect to the database.
		dbStatement = dbCon.createStatement();
		// Retry the query until complete or timeout.
		while (retry++ < QUERY_RETRIES) {
			// Begin a transaction.
			dbStatement.executeUpdate("Begin Transaction");
			// Set the isolation level.
			dbStatement.executeUpdate(new String(
					"Set Transaction Isolation level " + isolationLevel));
			// Execute the query.
			resultSet = dbStatement.executeQuery(sqlQuery);
			// Commit the transaction.
			dbStatement.executeUpdate("commit");
			// Close the connection.
			dbStatement.close();
			break;
		}
		return resultSet;
	}

	/**
	 * boolean runChainedUpdate() Executes a chained mode transaction query.
	 * 
	 * @param String
	 *            [] containing a series of SQL statments
	 * @param String
	 *            containing the isolation level to run the transaction.
	 * @return boolean
	 */
	public boolean runChainedUpdate(String[] sqlQuery, String isolationLevel)
			throws Exception {
		int retry = 0;

		// Create the statement object.
		Statement dbStatement = null;
		boolean wasExecuted = false;
		// Connect to the database.
		dbStatement = dbCon.createStatement();

		while (retry++ < QUERY_RETRIES) {
			// Begin a new transaction.
			try {
				dbStatement.executeUpdate("Begin Transaction");
				// Set the isolation level.
				dbStatement.executeUpdate(new String(
						"Set Transaction Isolation level " + isolationLevel));
				// For each sql statement, perform the update.
				for (int i = 0; i < sqlQuery.length; i++) {
					dbStatement.executeUpdate(sqlQuery[i]);
				}
				// Commit the transaction and close.
				dbStatement.executeUpdate("commit");
				dbStatement.close();
				wasExecuted = true;
			} catch (Exception e) {
				errorString = new String("Error executing: " + sqlQuery
						+ "\nCause: " + e.toString());
				hasError = true;
				// Rollback if an error has occured.
				dbStatement.executeUpdate("rollback");
				dbStatement.close();
			}
		}
		return wasExecuted;
	}
}
