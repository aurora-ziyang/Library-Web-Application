import java.io.*;
import java.net.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import library.DBWrapper;
import library.BookTitleSet;
import library.BookTitle;

/**
 * Class <b>SearchController</b> contains the servlet controller functionality
 * for processing user search requests.
 * 
 * 
 * @author CTE
 * @version 1.0
 */
public class SearchController extends Controller {

	/**
	 * a default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * SearchController doPost method. This is can be called by
	 * <b>Controller</b> superclass' doGet() method.
	 * 
	 * @param req
	 *            HttpServletRequest servlet request object
	 * @param res
	 *            HttpServletResponse servlet response object
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// Get the search parameters.
		String pterms = req.getParameter("str");

		// Redirect the user back to the index page if no terms are selected
		if (pterms.equals("")) {
			res.sendRedirect("index.jsp");
		} else {
			// Get the searchby parameter;
			String searchby = req.getParameter("searchby");

			// Build the sql statement based on what is to be searched on
			// (author or title).
			String sql = "SELECT * FROM BookTitle WHERE ";

			if (searchby.equals("author")) {
				sql += "author like '%" + pterms + "%'";
			} else {
				sql += "name like '%" + pterms + "%'";
			}

			// The results of the search are to be stored in a BookTitleSet
			// object.
			BookTitleSet results = new BookTitleSet();

			// Execute the search and fill the BookTitleSet with the results.
			try {
				ResultSet r = null;

				// Get an instance of the singleton DBWrapper object.
				DBWrapper db = DBWrapper.Instance();

				// Run the query.
				r = db.runQuery(sql);

				// Fill the BookTitleSet with BookTitles.
				while (r.next()) {
					BookTitle tempTitle = new BookTitle(
							r.getString("callNumber"), r.getString("name"),
							r.getString("author"), r.getString("isbn"),
							r.getString("edition"), r.getString("publisher"),
							r.getInt("publishedYear"));
					results.addBookTitle(tempTitle);

				}
			} catch (Exception e) {
				// Send this exception to the standard error page.
				sendErrorRedirect(req, res, e);
			}

			/**
			 * Redirect to the search.jsp page to display the search results.
			 * The search results are stored in the HttpServletRequest's
			 * session.
			 */
			try {
				req.getSession().setAttribute("results", results);

				// Be sure to tell search.jsp that it should initially show
				// books 0-9, i.e. the first ten books.
				String url = "search.jsp?start=0&searchby=" + searchby
						+ "&str=" + URLEncoder.encode(pterms, "UTF-8");
				res.sendRedirect(url);
			} catch (Exception e) {
				sendErrorRedirect(req, res, e);
			}
			return;
		}
	}
}
