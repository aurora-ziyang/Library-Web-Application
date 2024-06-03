import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.Member;
import library.HoldSet;
import library.BookTitleSet;
import library.LibraryMember;
import library.LibraryBookTitle;

/**
 * Class <b>MemberViewonholdController</b> contains 
 * the servlet controller functionality for
 * handling all requests for viewing
 * books on hold by library members.  
 *
 * @author CTE
 * @version 1.0
 */
public class MemberViewonholdController extends Controller {
    
     /**
	 * a default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * MemberViewonholdController doPost method.  This is can be called
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
	Member member = (Member) session.getAttribute("member");

	// Get the set of holds for this member.
	HoldSet heldBooks = null;
	
	try {
	    heldBooks = LibraryMember.getMemberHolds( member );
	} catch( Exception e ){
	    sendErrorRedirect(req, res, e);
	}

	// Create a BookTitleSet.
	BookTitleSet books = new BookTitleSet();
	
	// Move the holds to the BookTitleSet.
	try {
	    for( int i=0; i<heldBooks.getHoldCount(); i++ ) {
		books.addBookTitle( LibraryBookTitle.getBookTitle( heldBooks.getHoldAt(i).getCallNumber() ) );
	    }
	} catch( Exception e ){
	    sendErrorRedirect(req, res, e);
	}
	
	// Store the BookTitleSet object in the session.
	session.setAttribute("books", books);
	
	// redirect to the display page.
	res.sendRedirect("viewheldbooks.jsp");
    }
}

    


