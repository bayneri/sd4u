package sd4u.editor;

import sd4u.html.HTMLReader;
import sd4u.image.ImageDownloader;
import sd4u.image.WrongFormatException;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;

/**
 * This class reads the curent page style and applies user's changes in web codes
 * @author H. Cetiner & Y.H. Kalayci
 */
public class HTMLCodeFragmentInserter {
	
	public final static String COLUMN_DOUBLE = "COLUMN_DOUBLE";
	public final static String DEFINITION = "DEFINITION";
	public final static String REMARK = "REMARK";
	public final static String THEOREM = "THEOREM";
	public final static String IMAGE = "IMAGE";
	public final static String ANSWER = "ANSWER";
	public final static String CODE = "CODE";
	
	String columnDouble = "";
	String columnDoubleSingle = "";
	String columnSingle = "";
	String columnSingleDoubleSingle = "";
	String remarkCode = "";
	String definitionCode = "";
	String theoremCode = "";
	String imageCode = "";
	String answerCode = "";
	String codeCode = "";
	HTMLEditorHistory editorHistory;
	
	private static final String columnDoublePath = "/WebContent/SubjectA/InsertibleFragments/columnDouble.html";
	private static final String columnDoubleSinglePath = "/WebContent/SubjectA/InsertibleFragments/columnDoubleSingle.html";
	private static final String columnSinglePath = "/WebContent/SubjectA/InsertibleFragments/columnSingle.html";
	private static final String columnSingleDoubleSinglePath = "/WebContent/SubjectA/InsertibleFragments/columnSingleDoubleSingle.html";
	private static final String remarkPath = "/WebContent/SubjectA/InsertibleFragments/remark.html";
	private static final String definitionPath = "/WebContent/SubjectA/InsertibleFragments/definition.html";
	private static final String theoremPath = "/WebContent/SubjectA/InsertibleFragments/theorem.html";
	private static final String answerPath = "/WebContent/SubjectA/InsertibleFragments/answer.html";
	private static final String codePath = "/WebContent/SubjectA/InsertibleFragments/code.html";
	
	WebEngine engine;
	
	ExtendedHTMLEditor editor;
	
	/**
	 * This method inserts current pages web codes
	 */
	public HTMLCodeFragmentInserter(ExtendedHTMLEditor editor){
		this.editor = editor;
		Node webNode = editor.lookup(".web-view");
		WebView webView = (WebView) webNode;
		this.engine = webView.getEngine();
	}
	
	/*
	 * This method reads data and stores to use adding html fragment.
	 * @author Yusuf Hakan Kalayci
	 */
	public void readInitialVariables(HTMLEditorHistory editorHistory){
		
		System.out.println("okuduk");
		this.editorHistory = editorHistory;
		columnDouble = HTMLReader.readFile( getClass().getResource(columnDoublePath) );
		columnDoubleSingle = HTMLReader.readFile( getClass().getResource(columnDoubleSinglePath) );
		columnSingle = HTMLReader.readFile( getClass().getResource(columnSinglePath) );
		columnSingleDoubleSingle = HTMLReader.readFile( getClass().getResource(columnSingleDoubleSinglePath) );
		remarkCode = HTMLReader.readFile( getClass().getResource(remarkPath) );
		definitionCode = HTMLReader.readFile( getClass().getResource(definitionPath) );
		theoremCode = HTMLReader.readFile( getClass().getResource(theoremPath) );
		answerCode = HTMLReader.readFile( getClass().getResource(answerPath) );
		codeCode = HTMLReader.readFile( getClass().getResource(codePath) );
		//System.out.println(getClass().getResource("/WebContent/SubjectA/InsertibleFragments/asd asd.html").toString());
		String tmp = HTMLReader.readFile( getClass().getResource("/WebContent/SubjectA/InsertibleFragments/asd asd.html") );
	}
	
	final static String HTMLCodeInserterJS = "function insertHtmlAtCursor(html) {\n" +
            "    var range, node;\n" +
            "    if (window.getSelection && window.getSelection().getRangeAt) {\n" +
            "        range = window.getSelection().getRangeAt(0);\n" +
            "        node = range.createContextualFragment(html);\n" +
            "        range.insertNode(node);\n" +
            "    } else if (document.selection && document.selection.createRange) {\n" +
            "        document.selection.createRange().pasteHTML(html);\n" +
            "    }\n" +
            "}insertHtmlAtCursor('####html####')";
	
	private String escapeJavaStyleString(String str,boolean escapeSingleQuote, boolean escapeForwardSlash) {
        StringBuilder out = new StringBuilder("");
        if (str == null) {
            return null;
        }
        int sz;
        sz = str.length();
        for (int i = 0; i < sz; i++) {
            char ch = str.charAt(i);
 
            // handle unicode
            if (ch > 0xfff) {
                out.append("\\u").append(hex(ch));
            } else if (ch > 0xff) {
                out.append("\\u0").append(hex(ch));
            } else if (ch > 0x7f) {
                out.append("\\u00").append(hex(ch));
            } else if (ch < 32) {
                switch (ch) {
                    case '\b':
                        out.append('\\');
                        out.append('b');
                        break;
                    case '\n':
                        out.append('\\');
                        out.append('n');
                        break;
                    case '\t':
                        out.append('\\');
                        out.append('t');
                        break;
                    case '\f':
                        out.append('\\');
                        out.append('f');
                        break;
                    case '\r':
                        out.append('\\');
                        out.append('r');
                        break;
                    default:
                        if (ch > 0xf) {
                            out.append("\\u00").append(hex(ch));
                        } else {
                            out.append("\\u000").append(hex(ch));
                        }
                        break;
                }
            } else {
                switch (ch) {
                    case '\'':
                        if (escapeSingleQuote) {
                            out.append('\\');
                        }
                        out.append('\'');
                        break;
                    case '"':
                        out.append('\\');
                        out.append('"');
                        break;
                    case '\\':
                        out.append('\\');
                        out.append('\\');
                        break;
                    case '/':
                        if (escapeForwardSlash) {
                            out.append('\\');
                        }
                        out.append('/');
                        break;
                    default:
                        out.append(ch);
                        break;
                }
            }
        }
        return out.toString();
    }

	private String hex(int i) {
        return Integer.toHexString(i);
    }
	
	public void insertFragment( WebEngine engine,String str ){
		
		System.out.println( escapeJavaStyleString(str, true, true));
		
		try {
            engine.executeScript(HTMLCodeInserterJS.
                    replace("####html####",
                            escapeJavaStyleString(str, true, true)));
        } catch (JSException e1) {
            // A JavaScript Exception Occured
        }
		
	}
	
	/**
	 * This method provides user's changes occur in web codes
	 */
	public void insertFragmentType(String type){
		editorHistory.addUndoHistory();
		switch(type){
		case COLUMN_DOUBLE:
			insertFragment(engine,columnDouble);
			//this script for adjust column_double settings
			//engine.executeScript("$(function() { prettyPrint(); softdev4u_init(); });" );
			break;
		case THEOREM:
			insertFragment(engine,theoremCode);
			break;
		case REMARK:
			insertFragment(engine, remarkCode);
			break;
		case DEFINITION:
			insertFragment(engine, definitionCode);
			break;
		case ANSWER:
			insertFragment(engine, answerCode);
			break;
		case CODE:
			insertFragment(engine, codeCode);
			break;
		default:
			insertFragment(engine, type);
			//engine.executeScript(latexScript);
			break;
		}
		editor.reload();
	}
	
	public void insertFragmentCode(String code){
		
		editorHistory.addUndoHistory();
		
		insertFragment(engine, code);
		
		editor.reload();
		
	}
	
}
