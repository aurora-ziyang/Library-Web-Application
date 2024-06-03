import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.Book;
import library.Library;
import library.LibraryBook;

/**
 * Class <b>LibrarianCheckinController</b> contains 
 * the servlet controller functionality for processing
 * book check ins by librarians.
 * 
 *
 * @author CTE
 * @version 1.0
 */
public class LibrarianCheckinController extends Controller {

    /**
	 * a default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * LibrarianCheckinController doPost method.  This is can be called
     * by <b>Controller</b> superclass' doGet() method.
     * @param req HttpServletRequest servlet request object
     * @param res HttpServletResponse servlet response object
     * @throws ServletException
     * @throws IOException
     */
    public void doPost (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

	// Get the current HttpSession.
	HttpSession session = req.getSession();
	
	// Get the Librarian bean from the session.
	
	// Create a book object. 
	Book book = null;
	
	Library library = null;

	// Create a new instance of the Library object.
        try {
	    library = new Library();
	} catch( Exception e ) {
	    sendErrorRedirect(req, res, e);
	}
	
	// Get the bookid parameter.
	int bookID = (req.getParameter("bookID") == null) ? 0 : Integer.parseInt(req.getParameter("bookID"));
	
	// Initialize the book object and place it into the session.
	try {
	    book = LibraryBook.getBook(bookID);
	    session.setAttribute( "book", book );
	} catch( Exception e ) {
	    sendErrorRedirect(req, res, e);
	}
	
	
	// If the book is null, it is not in the database.  If it is not null,
	// check it back in.
	if (book != null) {
	    try {
		// Check the book in.
		if (library.checkInBook(bookID)) {
		    res.sendRedirect("entrycomplete.jsp?action=checkedin");
		} else {
		    res.sendRedirect("librarianaccess.jsp");
		}
	    } catch (Exception e) {
		// Send the exception to the standard error page.
		sendErrorRedirect(req, res, e);
	    }
	} else {
	    // This book did not exist. Set the error in the HttpSession.
	    session.setAttribute( "checkinbook", "This book does not exist" );
	    
	    // Send the user back to the librarianaccess page.
	    res.sendRedirect("librarianaccess.jsp");
	}
    }
}





