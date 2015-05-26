package sd4u.editor;

import java.util.Stack;

import sd4u.html.HTMLMerger;
import sd4u.html.HTMLParser;

/**
 * This method provides undo and redo by storing any action
 */
public class HTMLEditorHistory{
	
	ExtendedHTMLEditor editor;
	
	Stack< HTMLContent > undoHistory = new Stack< HTMLContent >();
	Stack< HTMLContent > redoHistory = new Stack< HTMLContent >();
	
	public void setContent( Stack< HTMLContent > undoHistory,Stack< HTMLContent > redoHistory ){
		this.undoHistory=undoHistory;
		this.redoHistory=redoHistory;
	}
	
	public HTMLEditorHistory( ExtendedHTMLEditor editor){
		this.editor = editor;
	}
	
	public void addUndoHistory(){
		String html = editor.getBasicHtml();
		HTMLContent tmp = new HTMLContent(HTMLParser.getTitle(html), HTMLParser.getPage(html));
		
		if( !undoHistory.empty() && tmp.equals( undoHistory.peek() ) )
			return ;
		
		undoHistory.add(tmp);
		redoHistory.clear();
	}
	
	/**
	 * This method provides undo
	 */
	public void undo(){
		if(undoHistory.empty())
			return ;
		String html = editor.getHtmlText();
		HTMLContent prev = new HTMLContent( HTMLParser.getTitle(html),HTMLParser.getPage(html) );
		
		if( redoHistory.empty() || !prev.equals( redoHistory.peek() ) )
			redoHistory.push(prev);
		
		HTMLContent tmp = undoHistory.pop();
		HTMLMerger merger = new HTMLMerger(tmp.getTitle(),tmp.getPage());
		System.out.println(merger.getFullHTMLForEditor());
		editor.setHtmlText(merger.getFullHTMLForEditor());
	}
	
	/**
	 * This method provides redo
	 */
	public void redo(){
		if(redoHistory.empty())
			return ;
		String html = editor.getHtmlText();
		HTMLContent prev = new HTMLContent( HTMLParser.getTitle(html),HTMLParser.getPage(html) );
		
		if( undoHistory.empty() || !prev.equals( undoHistory.peek() ) )
			undoHistory.push(prev);
		
		HTMLContent tmp = redoHistory.pop();
		HTMLMerger merger = new HTMLMerger(tmp.getTitle(),tmp.getPage());
		editor.setHtmlText(merger.getFullHTMLForEditor());	
	}
	
	/**
	 * This method clears all action history
	 */
	public void clearHistory(){
		undoHistory.clear();
		redoHistory.clear();
	}
	
	public class HTMLContent{
		String title;
		String page;
		
		public HTMLContent(String title,String page){
			this.title = title;
			this.page = page;
		}
		
		public boolean equals( HTMLContent other ){
			return title.equals(other.getTitle()) && page.equals(other.getPage());
		}

		public String getTitle() {
			return title;
		}

		public String getPage() {
			return page;
		}
		
	}

}
