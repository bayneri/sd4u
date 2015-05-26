package sd4u.html;

/**
 *This class parses given whole slide to editable containers and blocks which are fixed by HTML code.
 * @author H. Cetiner & Y.H. Kalayci
 */
public class HTMLParser {
	final static String PRE_TITLE = "<!-- SOFTDEV4U PRETITLE -->";
	final static String POST_TITLE = "<!-- SOFTDEV4U POSTTITLE -->";
	final static String PRE_PAGE = "<!-- SOFTDEV4U PREPAGE -->";
	final static String POST_PAGE = "<!-- SOFTDEV4U POSTPAGE -->";
	
	final static String DEFINITION = "<!-- erase--><b>Definition</b>";
	final static String REMARK = "<!-- erase--><b>Remark</b>";
	final static String THEOREM = "<!-- erase--><b>Theorem</b>";
	
	/**
	 * This class detects title of the container while parsing.
	 * @param html HTML code of the slide
	 * @return Title of the container.
	 */
	public static String getTitle(String html){
		StringBuffer sb = new StringBuffer(html);
		int startPosition = sb.indexOf( PRE_TITLE );
		int endPosition = sb.indexOf( POST_TITLE );
		String rev=sb.substring(startPosition, endPosition).toString();
		rev=rev.replace(DEFINITION, "");
		rev=rev.replace(REMARK, "");
		rev=rev.replace(THEOREM, "");
		return rev;
	}
	
	/**
	 * This class detects which page will be parsed into its elements.
	 * @param html HTML code of the slide
	 * @return The page, which will be parsed into its elements.
	 */
	public static String getPage(String html){
		StringBuffer sb = new StringBuffer(html);
		int startPosition = sb.indexOf( PRE_PAGE );
		int endPosition = sb.indexOf( POST_PAGE );
		String rev=sb.substring(startPosition, endPosition).toString();
		rev=rev.replace(DEFINITION, "");
		rev=rev.replace(REMARK, "");
		rev=rev.replace(THEOREM, "");
		return rev;
	}
	
}
