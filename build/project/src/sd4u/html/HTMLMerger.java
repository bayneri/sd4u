package sd4u.html;

import java.net.URL;

public class HTMLMerger {
	final static String PRE_TITLE = "<!-- SOFTDEV4U PRETITLE -->";
	final static String POST_TITLE = "<!-- SOFTDEV4U POSTTITLE -->";
	final static String PRE_PAGE = "<!-- SOFTDEV4U PREPAGE -->";
	final static String POST_PAGE = "<!-- SOFTDEV4U POSTPAGE -->";
	
	private static String fixed1Path = "/WebContent/SubjectA/PageTypes2/fixed1.html";
	private static String fixed2Path = "/WebContent/SubjectA/PageTypes2/fixed2.html";
	private static String fixed3Path = "/WebContent/SubjectA/PageTypes2/fixed3.html";
	private static String fixed1ForEditorPath = "/WebContent/SubjectA/PageTypes2/fixed1ForEditor.html";
	
	String title;
	String page;
	private static String fixed1;
	private static String fixed1ForEditor;
	private static String fixed2;
	private static String fixed3;

	private static boolean initialized = false;
	
	HTMLReplacer replacer = new HTMLReplacer();
	
	public void readFixedParts(){
		initialized=true;
		fixed1 = HTMLReader.readFile(getClass().getResource(fixed1Path));
		fixed1ForEditor = HTMLReader.readFile( getClass().getResource(fixed1ForEditorPath) );
		fixed1ForEditor = replacer.toReplacedHTML(fixed1ForEditor);
		fixed2 = HTMLReader.readFile(getClass().getResource(fixed2Path));
		fixed3 = HTMLReader.readFile(getClass().getResource(fixed3Path));
	}
	
	public HTMLMerger(URL titleUrl, URL pageUrl){
		
		title=HTMLReader.readFile(titleUrl);
		page =HTMLReader.readFile(pageUrl);
		if(!initialized)
			readFixedParts();
		
	}
	
	public HTMLMerger(String title,String page){
		this.title = title;
		this.page=page;
		if(!initialized)
			readFixedParts();
	}
	
	public String getFullHTML(){
		StringBuffer sb = new StringBuffer();
		sb.append(fixed1);
		sb.append(title);
		sb.append(fixed2);
		sb.append(page);
		sb.append(fixed3);
		return sb.toString();
	}
	
	public String getFullHTMLForEditor(){
		StringBuffer sb = new StringBuffer();
		sb.append(fixed1ForEditor);
		sb.append(title);
		sb.append(fixed2);
		sb.append(page);
		sb.append(fixed3);
		return sb.toString();
		
	}
	
}
