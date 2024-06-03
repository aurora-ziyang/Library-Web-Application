package library;

/**
 * Class <b>CommonTags</b> represents a the functions to print a common header
 * and footer.
 *
 * @author A Jiayi
 * @version 1.0
 */
public class CommonTags {

	/** Separators for links. */
	private final String SEP = "&nbsp;&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;&nbsp;";

	/**
	 * Class CommonTags constructor
	 */
	public CommonTags() {
	}

	/**
	 * Returns the string of html for the standard library header.
	 * 
	 * @param omit String which link to omit
	 * @return String
	 */
	public String getHeader(String omit) {
		String search = (omit.equals("OMIT_SEARCH")) ? "Search" : "<a href=\"index.jsp\">Search</a>";
		String member = (omit.equals("OMIT_MEMBER")) ? "Member Login" : "<a href=\"member.jsp\">Member Login</a>";
		String librarian = (omit.equals("OMIT_LIBRARIAN")) ? "Librarian Login"
				: "<a href=\"librarian.jsp\">Librarian Login</a>";

		return new String("<img src=\"images/logo.gif\" class=\"logo\">\n\n"
				+ "    <div class=\"center\" style=\"width: 600\">\n" + "      <b class=\"smaller\">\n" + "        "
				+ search + SEP + member + SEP + librarian + "\n" + "      </b>\n" + "    </div>");
	}

	/**
	 * Returns the standard library footer.
	 * 
	 * @return String
	 */
	public String getFooter() {
		return new String("<hr size=\"1\" noshade align=\"left\" width=\"600\">\n"
				+ "<font size=\"-1\">Copyright &copy; 2024 CTE, Inc. All rights reserved.</font>");
	}
}
