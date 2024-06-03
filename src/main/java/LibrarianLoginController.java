import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.Librarian;
import library.Library;

/**
 * Class <b>LibrarianLoginController</b> contains 
 * the servlet controller functionality for processing
 * librian login requests.
 * 
 *
 * @author CTE
 * @version 1.0
 */
public class LibrarianLoginController extends Controller {
    
    /**
	 * a default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * LibrarianLoginController doPost method.  This is can be called
     * by <b>Controller</b> superclass' doGet() method.
     * @param req HttpServletRequest servlet request object
     * @param res HttpServletResponse servlet response object
     * @throws ServletException
     * @throws IOException
     */
    public void doPost (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
    	
    String passWord=null, ssn=null;
	
	// Get the HTTPSession object.
	HttpSession session = req.getSession();
	
	// Create a Library object instance.
	Library library = null;

	// Create a new instance of the Library object.
        try {
	    library = new Library();
	} catch( Exception e ) {
	    sendErrorRedirect(req, res, e);
	}
	
	// Get the username and password parameters from the librarian log in form.
	ssn = req.getParameter("uname");
	passWord = req.getParameter("passwd");
	
	// Lets invalidate the session to get rid of the unnecessary objects.
	session.invalidate();
	
	// Get a fresh HttpSession.
	session = req.getSession();
	
	// Attempt to validate the librarian.
	Librarian librarian = null;
	
	if (ssn=="") {
		res.sendRedirect("librarian.jsp?errMsg=Invalid user id");
	} else {
	
	try {
	    librarian = library.validateLibrarian(ssn, passWord );
	} catch( Exception e ){
	    sendErrorRedirect(req, res, e);
	}
	
	try {
	    // Place the librarian bean back into the session.
	    if (librarian!=null) {
		session.setAttribute("librarian", librarian);
		res.sendRedirect("librarianaccess.jsp");
	    } else {
		// If the librarian object is null, then validation has failed.  Tell this to the user.
		res.sendRedirect("librarian.jsp?errMsg=Invalid user id and/or password");
	    }
	} catch (Exception e) {
	    // Send the exception to the standard error page.
	    sendErrorRedirect(req, res, e);
	}
    }
    }
}





