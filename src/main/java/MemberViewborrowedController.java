import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.Member;
import library.BookSet;
import library.LibraryMember;

/**
 * Class <b>MemberViewborrowedController</b> contains 
 * the servlet controller functionality for
 * handling all requests for viewing
 * borrowed books by library members.  
 *
 * @author CTE
 * @version 1.0
 */
public class MemberViewborrowedController extends Controller {
  
    /**
	 * a default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * MemberViewborrowedController doPost method.  This is can be called
     * by <b>Controller</b> superclass' doGet() method.
     * @param req HttpServletRequest servlet request object
     * @param res HttpServletResponse servlet response object
     * @throws ServletException
     * @throws IOException
     */
    public void doPost (HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {

	// Get the session object.
	HttpSession session = req.getSession();
	
	// Retrieve the member from the session.
	Member member =(Member) session.getAttribute("member");

	BookSet checkedoutBooks = null;
	
	try {
	    // Get the checked out books from the LibraryMember object and place them into a BookSet.
	    checkedoutBooks = LibraryMember.getCheckedOutBooks( member );
	} catch( Exception e ){
	    sendErrorRedirect(req, res, e);
	}

	// Store the BookSet object in the session.
	session.setAttribute("checkedoutBooks", checkedoutBooks);
	
	// Forward results to the display page.
	res.sendRedirect("viewborrowedbooks.jsp");
    }
    
}

    


