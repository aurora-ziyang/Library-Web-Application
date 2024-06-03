import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.Book;
import library.Librarian;
import library.Member;
import library.LibraryBook;
import library.LibraryMember;
import library.Library;

/**
 * Class <b>LibrarianCheckoutController</b> contains 
 * the servlet controller functionality for processing
 * book checkouts by librarians.
 * 
 *
 * @author CTE
 * @version 1.0
 */
public class LibrarianCheckoutController extends Controller {

    /**
	 * a default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * LibrarianCheckoutController doPost method.  This is can be called
     * by <b>Controller</b> superclass' doGet() method.
     * @param req HttpServletRequest servlet request object
     * @param res HttpServletResponse servlet response object
     * @throws ServletException
     * @throws IOException
     */
    public void doPost (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

	// Get the session.
	HttpSession session = req.getSession();
	
	// Get the librarian bean from the session.
	Librarian librarian = (Librarian) session.getAttribute("librarian");    

	// Clear any old error messages in the session.
	session.removeAttribute( "checkoutmember" );
	session.removeAttribute( "checkoutbook" );
	
	// Get the book bean from the session.
	Book book = null;
	
	// Get the bookid parameter
	int bookID = (req.getParameter("bookID") == null) ? -1 : Integer.parseInt(req.getParameter("bookID"));
	
	// Initialize the book object. 
	try {
	    book = LibraryBook.getBook(bookID);
	} catch( Exception e ) {
	    sendErrorRedirect(req, res, e);
	}
	//Create a new instance of the library object.  
	Library library = null;

	// Create a new instance of the Library object.
        try {
	    library = new Library();
	} catch( Exception e ) {
	    sendErrorRedirect(req, res, e);
	}

	// Get the member's ssn.
	int ssn = (req.getParameter("ssn") == null) ? -1 : Integer.parseInt(req.getParameter("ssn"));

	// Create a new member object with the ssn.
	Member member = null;
	try {
	    member = LibraryMember.getMember( ssn );
	} catch( Exception e ){
	    sendErrorRedirect(req, res, e);
	}	
	
	// Create a get parameter version of the ssn.
	String pssn = (ssn == -1) ? "" : "?ssn=" + ssn;
	
	// Set the book object to the specified book.
	// If the book or member is null, throw an error.
	// Check the book out otherwise.
	if (book != null) {
	    if (member != null) {
		try {
		    // Check out.
		    if ( library.checkOutBook(bookID, ssn, librarian )) {
			// Set the book attribute into the session.
			session.setAttribute( "book", book );			  
			res.sendRedirect("entrycomplete.jsp?action=checkedout");
		    } else {
			// If the checkout fails, let the user know.
			session.setAttribute("checkoutbook", "This book is already checked out.");
			res.sendRedirect("librarianaccess.jsp" + pssn);
		    }
		} catch (Exception e) {
		    // Send the exception to the standard error page.
		    sendErrorRedirect(req, res, e);
		}
	    } else {
		// This member did not exist.  Set the error in the book object.
		session.setAttribute("checkoutmember", "This member does not exist");
		
		// Return the user to the main librarian page.
		res.sendRedirect("librarianaccess.jsp" + pssn);
	    }
	} else {
	    // This book did not exist.  Set the error in the book.
	    session.setAttribute("checkoutbook", "This book does not exist");
	    
	    // Return the user to the main librarian page.
	    res.sendRedirect("librarianaccess.jsp" + pssn);
	}
    }
}

