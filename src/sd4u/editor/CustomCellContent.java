package sd4u.editor;

import java.util.Stack;

import sd4u.editor.HTMLEditorHistory.HTMLContent;
import sd4u.html.HTMLMerger;

/**
 * This method creates elements of the list
 * @author H. Cetiner & Y.H. Kalayci
 *
 */
public class CustomCellContent {
	
	String title;
	Stack< HTMLContent > undoHistory;
	Stack< HTMLContent > redoHistory;
	public String htmlText;
	
	private static final String CSDS_TITLE_PATH = "/WebContent/SubjectA/PageTypes2/columnSingleDoubleSingleTitle.html";
	private static final String CSDS_PAGE_PATH = "/WebContent/SubjectA/PageTypes2/columnSingleDoubleSinglePage.html";
	
	/**
	 * This method creates elements of the list in order to given title name
	 * @param title title of the content which will be created 
	 */
	public CustomCellContent(String title){
		
		this.title=title;
		
		undoHistory= new Stack< HTMLContent >();
		redoHistory= new Stack< HTMLContent >();
		HTMLMerger merger = new HTMLMerger( 	getClass().getResource(CSDS_TITLE_PATH),
				getClass().getResource(CSDS_PAGE_PATH)
			);
		htmlText=merger.getFullHTMLForEditor();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title;
	}
	
	
	
}
