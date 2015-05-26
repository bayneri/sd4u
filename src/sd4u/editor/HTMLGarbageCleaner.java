package sd4u.editor;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sd4u.html.HTMLMerger;
import sd4u.html.HTMLParser;
import sd4u.html.HTMLReader;

/**
 * This method provides clean forsaken changes from HTML code
 */
public class HTMLGarbageCleaner {
	
	final static String DEFINITION = "<b>Definition </b>";
	final static String REMARK = "<b>Remark </b>";
	final static String THEOREM = "<b>Theorem </b>";
	final static String DOUBLE_COLUMN_BEGIN = "<div class=\"row\">";
	final static String DOUBLE_COLUMN_END = "</div>";
	
	String latexGarbageBeginFirst = "<span class=\"MathJax";
	String latexGarbageBeginLast = "script type=\"math/tex\" id=\"MathJax-Element-";
	String replaceBeginWith = "\\(";
	String latexGarbageEnd = "</script>";
	String replaceEndWith = "\\)";
	final static String latexGarbagePath = "/WebContent/SubjectA/Garbages/LatexGarbages.txt";
	
	final static ArrayList<String> showHideGarbages = new ArrayList<String>();
	final static String ShowHideGarbagesPath = "/WebContent/SubjectA/Garbages/ShowHideGarbages.txt";
	
	public HTMLGarbageCleaner(){
		HTMLReader.readFile(getClass().getResource(ShowHideGarbagesPath), showHideGarbages);
	}
	
	public String getCleanedHtmlText(String html){
		HTMLMerger merger = new HTMLMerger(HTMLParser.getTitle(html), HTMLParser.getPage(html));
		html=merger.getFullHTMLForEditor();
		html=clearMarkups(html);
		html=clearLatexGarbages(html);
		html=clearHtml(html);
		html=clearDoubleColumn(html);
		html=clearShowHideGarbages(html);
		html=clearSwitchToCodeGarbages(html);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+html);
		return html;
	}
	
	private String clearStyle(String html){
		Pattern replace = Pattern.compile("style=\"(.*?)\"");
		
		Matcher regexMatcher = replace.matcher( html.trim() );
		
		return regexMatcher.replaceAll( "style=\"\"" );
	}
	
	public String clearHtml(String html){
		//html = html.replaceAll("<span style=[^>]*>([^<]*)</span>", "$1");
		html=clearStyle(html);
		//html=clearSpan(html);
		//html = html.replaceAll("<style=\"[^\"]*\"", "$1");
		return html;
	}
	
	/**
	 * This method clears Markups' garbages
	 */
	public String clearMarkups(String html){
		html=html.replace(DEFINITION, "");
		html=html.replace(REMARK, "");
		html=html.replace(THEOREM, "");
		return html;
	}
	
	/**
	 * This method clears Latex's garbages
	 */
	public String clearLatexGarbages(String html){
		
		StringBuffer sb = new StringBuffer(html);
		
		while(true)
		{
			System.out.println("asdasdasdasd");
			int begIndex=0,endIndex=0;
			int begIndex2=0,endIndex2=0;
			begIndex = sb.indexOf(latexGarbageBeginFirst);
			//System.out.println(begIndex);
			if(begIndex!=-1)
				System.out.println( " " + sb.substring(begIndex) );
			if(begIndex==-1)	break;
			//endIndex = sb.indexOf
			endIndex = sb.indexOf(latexGarbageBeginLast,begIndex);
			//System.out.println(begIndex + " " + endIndex);
			endIndex+=latexGarbageBeginLast.length()+3;
			begIndex2 = sb.indexOf(latexGarbageEnd, endIndex);
			endIndex2 = begIndex2 + latexGarbageEnd.length();
			sb.replace(begIndex2,endIndex2,replaceEndWith);
			sb.replace(begIndex,endIndex,replaceBeginWith);
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * This method clears Double Column's garbages
	 */
	public String clearDoubleColumn(String html){
		
		int pos = 0;
		
		int counter = 0;
		
		Stack<Integer> st = new Stack<Integer>();
		
		StringBuffer sb = new StringBuffer(html);
		
		while( pos!=-1 ){
			
			pos = sb.indexOf("div", pos);
			if(pos==-1) break;
			//System.out.println(pos);
			if(sb.charAt( pos-1 )=='/'){
				if(!st.isEmpty() && counter == st.peek().intValue()){
					sb.replace(pos-2, pos-2+DOUBLE_COLUMN_END.length(), "");
				}
				counter--;
			}
			else if( sb.indexOf( DOUBLE_COLUMN_BEGIN,pos-1 )==pos-1 ){
				counter++;
				sb.replace( pos-1,pos-1+DOUBLE_COLUMN_BEGIN.length(),"" );
				st.add( new Integer(counter) );
			}else{
				counter++;
			}
			pos++;
			
		}
		
		return sb.toString();
		
	}
	
	/**
	 * This method clears Show/Hide's garbages
	 */
	public String clearShowHideGarbages(String html){
		
		StringBuffer sb = new StringBuffer(html);
		
		for(String currentString:showHideGarbages)
		{
			//System.out.println(currentString);
			//System.out.println(html.indexOf(currentString));
			while(true){
				int ind = sb.indexOf(currentString);
				if(ind==-1)
					break;
				sb.replace(ind,ind+currentString.length(),"");
			}
		}
		return sb.toString();
		
	}
	
	/**
	 * This method clears Switch to Code's garbages
	 */
	public String clearSwitchToCodeGarbages(String html){
		
		System.out.println("asdasdasd S To C");
		
		//<button onclick="openExample('asdd', 'file:/Users/yhkalayci/Documents/workspaceJavaFX/Project/src/sd4u/editor/App.java', 2, 'Java');">1234t</button>
		
		Pattern replace = Pattern.compile("<button onclick=\"openExample((.*?),(.*?),(.*?),(.*?));\">(.*?)</button>");
		
		Matcher regexMatcher = replace.matcher( html.trim() );
		
		return regexMatcher.replaceAll( "" );
		
	}
	
}
