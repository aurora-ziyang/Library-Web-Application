import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.Member;
import library.Library;

/**
 * Class <b>MemberLoginController</b> contains 
 * the servlet controller functionality for processing
 * member login requests.
 * 
 *
 * @author CTE
 * @version 1.0
 */
public class MemberLoginController extends Controller {

    /**
	 * a default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * MemberLoginController doPost method.  This is can be called
     * by <b>Controller</b> superclass' doGet() method.
     * @param req HttpServletRequest servlet request object
     * @param res HttpServletResponse servlet response object
     * @throws ServletException
     * @throws IOException
     */
    public void doPost (HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
	
	String pass=null, uname=null;

	// Get the HttpSession object.
	HttpSession session = req.getSession();
	
	// Create a Library object instance.
	Library library = null;

	// Create a new instance of the Library object.
        try {
	    library = new Library();
	} catch( Exception e ) {
	    sendErrorRedirect(req, res, e);
	}
	
	// Get the username and password parameters.
	pass = req.getParameter("passwd");
	uname = req.getParameter("uname");
	
	// Invalidate the session to get rid of any old Member or Librarian objects.
	session.invalidate();
	
	// Get a fresh session.
	session = req.getSession();
	
	// Create a new library member through validation.  If the uname is not valid, the member is null.
	Member member = null;
	
	if (uname=="") {
		res.sendRedirect("member.jsp?errMsg=Invalid user id");
	} else {
	
	try {
	    member = library.validateMember( uname, pass );
	} catch( Exception e ){
	    sendErrorRedirect(req, res, e);
	}
	
	try {
	    // The member is not null, so he must have been validated.  Place the member into the session.
	    if (member!=null) {
		session.setAttribute("member", member);
		res.sendRedirect("memberaccount.jsp");
	    } else {
		/**
		 * The member was not validated.  We simply
		 * set an error message, and redirect back to
		 * the member login page.
		 */
		res.sendRedirect("member.jsp?errMsg=Invalid user id and/or password");
	    }
	} catch (Exception e2) {
	    // Something bad happened, so redirect to the error page.
	    sendErrorRedirect(req, res, e2);
	}
	}
    }
}

    


