package sd4u.html;

public class HTMLParser {
	final static String PRE_TITLE = "<!-- SOFTDEV4U PRETITLE -->";
	final static String POST_TITLE = "<!-- SOFTDEV4U POSTTITLE -->";
	final static String PRE_PAGE = "<!-- SOFTDEV4U PREPAGE -->";
	final static String POST_PAGE = "<!-- SOFTDEV4U POSTPAGE -->";
	
	final static String DEFINITION = "<!-- erase--><b>Definition</b>";
	final static String REMARK = "<!-- erase--><b>Remark</b>";
	final static String THEOREM = "<!-- erase--><b>Theorem</b>";
	
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
