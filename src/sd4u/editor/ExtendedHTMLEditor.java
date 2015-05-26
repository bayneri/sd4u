package sd4u.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sd4u.editor.additional_stages.ChooseCodeController;
import sd4u.editor.additional_stages.CodeAddController;
import sd4u.editor.additional_stages.ImageAddController;
import sd4u.editor.additional_stages.LatexAddController;
import sd4u.html.HTMLMerger;
import sd4u.html.HTMLReplacer;

/**
 * This class imports default HTML Editor default slide layouts
 */

public class ExtendedHTMLEditor extends HTMLEditor{
	
	private static final String CD_TITLE_PATH = "/WebContent/SubjectA/PageTypes2/columnDoubleTitle.html";
	private static final String CD_PAGE_PATH = "/WebContent/SubjectA/PageTypes2/columnDoublePage.html";
	
	private static final String CDS_TITLE_PATH = "/WebContent/SubjectA/PageTypes2/columnDoubleSingleTitle.html";
	private static final String CDS_PAGE_PATH = "/WebContent/SubjectA/PageTypes2/columnDoubleSinglePage.html";
	
	private static final String CS_TITLE_PATH = "/WebContent/SubjectA/PageTypes2/columnSingleTitle.html";
	private static final String CS_PAGE_PATH = "/WebContent/SubjectA/PageTypes2/columnSinglePage.html";
	
	private static final String CSDS_TITLE_PATH = "/WebContent/SubjectA/PageTypes2/columnSingleDoubleSingleTitle.html";
	private static final String CSDS_PAGE_PATH = "/WebContent/SubjectA/PageTypes2/columnSingleDoubleSinglePage.html";
	
	HTMLCodeFragmentInserter inserter;
	
	HTMLGarbageCleaner cleaner;
	
	HTMLEditorHistory editorHistory;
	
	Set<Node> scrollBars;
	
	boolean initialized = false;
	
	public void setEditorHistory( HTMLEditorHistory history){
		this.editorHistory=history;
	}
	
	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * This method provides functions of the buttons
	 */
	public void initialize(){
		cleaner = new HTMLGarbageCleaner();
		editorHistory = new HTMLEditorHistory(this);
		inserter = new HTMLCodeFragmentInserter( this );
		inserter.readInitialVariables(editorHistory);
		this.addEventFilter( KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				
				ArrayList<KeyCode> suitableKeys = new ArrayList<KeyCode>(
						Arrays.asList( KeyCode.SPACE,KeyCode.ENTER,KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP,KeyCode.DOWN )
						);
				
				for(int i=0;i<suitableKeys.size();i++){
					if( event.getCode()==suitableKeys.get(i) ){
						editorHistory.addUndoHistory();
						//System.out.println(event.getCode().toString() + " " + undoHistory.size() );
						break;
					}
				}
			}
			
		} );
		
		this.initializeTopToolBar();
		this.initializeBottomToolBar();
	}
	
	/**
	 * This method provides undo by returning act history
	 * @return User's act history
	 */
	public HTMLEditorHistory getHistory(){
		return editorHistory;
	}
	
	/**
	 *This method initializes Top Tool Bar 
	 */
	public void initializeTopToolBar(){
		Node toolNode = this.lookup(".top-toolbar");
		Node webNode = this.lookup(".web-view");
        if (toolNode instanceof ToolBar && webNode instanceof WebView) {
            ToolBar bar = (ToolBar) toolNode;
            //WebView webView = (WebView) webNode;
            //scrollBars = webView.lookupAll(".scroll-bar");
            //WebEngine engine = webView.getEngine();
            
            Button btnSeperateVertically = new Button("DC");
            btnSeperateVertically.setMinSize(24.0, 24.0);
            btnSeperateVertically.setMaxSize(200.0, 24.0);
            
            Button btnTheorem = new Button("T");
            btnTheorem.setMinSize(24.0, 24.0);
            btnTheorem.setMaxSize(200.0, 24.0);
            
            Button btnRemark = new Button("R");
            btnRemark.setMinSize(24.0, 24.0);
            btnRemark.setMaxSize(200.0, 24.0);
            
            Button btnDefinition = new Button("D");
            btnDefinition.setMinSize(24.0, 24.0);
            btnDefinition.setMaxSize(200.0, 24.0);
            
            Button btnAnswer = new Button("Show/Hide");
            btnAnswer.setMinSize(24.0, 24.0);
            btnAnswer.setMaxSize(200.0, 24.0);
            
            Button btnImage = new Button("Image");
            btnImage.setMinSize(24.0, 24.0);
            btnImage.setMaxSize(200.0, 24.0);
            
            Button btnLatex = new Button("Latex");
            btnLatex.setMinSize(24.0, 24.0);
            btnLatex.setMaxSize(200.0,24.0);
            
            Button btnCode = new Button("Code");
            btnCode.setMinSize(24.0, 24.0);
            btnCode.setMaxSize(200.0,24.0);
            
            Button btnFile = new Button("Code File");
            btnFile.setMinSize(24.0, 24.0);
            btnFile.setMaxSize(200.0,24.0);
            
            bar.getItems().addAll(btnSeperateVertically,btnTheorem,btnRemark,btnDefinition,btnAnswer,btnImage,btnCode,btnFile,btnLatex);
            
            btnFile.setOnAction( new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					FXMLLoader loader = new FXMLLoader( ImageAddController.class.getResource("choose_code.fxml") );
					Pane pane;
					try {
						pane = (Pane) loader.load();
						Scene scene = new Scene(pane);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.setTitle("Insert Code File");
		            	stage.show();
		            	ChooseCodeController iadd = (ChooseCodeController) loader.getController();
		            	iadd.setStage(stage);
		            	iadd.setInserter(inserter);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
            
            btnCode.setOnAction( new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					FXMLLoader loader = new FXMLLoader( ImageAddController.class.getResource("code_add.fxml") );
					Pane pane;
					try {
						pane = (Pane) loader.load();
						Scene scene = new Scene(pane);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.setTitle("Insert Code");
		            	stage.show();
		            	CodeAddController iadd = (CodeAddController) loader.getController();
		            	iadd.setStage(stage);
		            	iadd.setInserter(inserter);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
            
            btnImage.setOnAction( new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					FXMLLoader loader = new FXMLLoader( ImageAddController.class.getResource("image_add.fxml") );
					Pane pane;
					try {
						pane = (Pane) loader.load();
						Scene scene = new Scene(pane);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.setTitle("Insert Image");
		            	stage.show();
		            	ImageAddController iadd = (ImageAddController) loader.getController();
		            	iadd.setStage(stage);
		            	iadd.setInserter(inserter);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} );
            
            btnLatex.setOnAction(new EventHandler<ActionEvent>(){
				
            	@Override
				public void handle(ActionEvent event) {
					FXMLLoader loader = new FXMLLoader( ImageAddController.class.getResource("latex_add.fxml") );
					Pane pane;
					try {
						pane = (Pane) loader.load();
						Scene scene = new Scene(pane);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.setTitle("Insert Latex Code");
		            	stage.show();
		            	LatexAddController iadd = (LatexAddController) loader.getController();
		            	iadd.setStage(stage);
		            	iadd.setInserter(inserter);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
            });
            
            btnSeperateVertically.setOnAction(e -> {
            	inserter.insertFragmentType(HTMLCodeFragmentInserter.COLUMN_DOUBLE);
            });
            
            btnTheorem.setOnAction(e -> {
            	inserter.insertFragmentType(HTMLCodeFragmentInserter.THEOREM);
            });

            btnRemark.setOnAction(e -> {
            	inserter.insertFragmentType(HTMLCodeFragmentInserter.REMARK);
            });
            
            btnDefinition.setOnAction(e -> {
            	inserter.insertFragmentType(HTMLCodeFragmentInserter.DEFINITION);
            });
            
            btnAnswer.setOnAction( e->{
            	inserter.insertFragmentType(HTMLCodeFragmentInserter.ANSWER);
            });
            
        }
	}
	
	/**
	 *This method initializes Bottom Tool Bar 
	 */
	public void initializeBottomToolBar(){
		Node bottomTool = this.lookup(".bottom-toolbar");
		Node webNode = this.lookup(".web-view");
        if (bottomTool instanceof ToolBar && webNode instanceof WebView) {
        	ToolBar bar = (ToolBar) bottomTool;
        	ObservableList<String> options = 
        		    FXCollections.observableArrayList(
        		        "Column Double",
        		        "Column Double Single",
        		        "Column Single",
        		        "Column Single Double Single",
        		        "Seperator Concept B",
        		        "Seperator Concept B SubCall"
        		    );
        	final ComboBox<String> comboBox = new ComboBox<String>(options);
        	bar.getItems().add(comboBox);
        	comboBox.setOnAction( (event)->{
        		int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        		String tmp;
        		HTMLReplacer replacer = new HTMLReplacer();
        		HTMLMerger merger;
        		switch( selectedIndex ){
        		case 0:
        			merger = new HTMLMerger( getClass().getResource(CD_TITLE_PATH),
        									getClass().getResource(CD_PAGE_PATH)
        									);
        	        this.setHtmlText( merger.getFullHTMLForEditor() );
        			break;
        		case 1:
        			merger = new HTMLMerger( 	getClass().getResource(CDS_TITLE_PATH),
												getClass().getResource(CDS_PAGE_PATH)
        									);
        			this.setHtmlText( merger.getFullHTMLForEditor() );
        			break;
        		case 2:
        			merger = new HTMLMerger( 	getClass().getResource(CS_TITLE_PATH),
												getClass().getResource(CS_PAGE_PATH)
        									);
        			this.setHtmlText( merger.getFullHTMLForEditor() );
        			break;
        		case 3:
        			merger = new HTMLMerger( 	getClass().getResource(CSDS_TITLE_PATH),
												getClass().getResource(CSDS_PAGE_PATH)
        									);
        			this.setHtmlText( merger.getFullHTMLForEditor() );
					break;
        		case 4:
        			tmp=replacer.htmlGetter( new File(getClass().getResource("/WebContent/SubjectA/PageTypes/sepConceptB.html").getPath()) );
        	        this.setHtmlText( tmp );
        			break;
        		case 5:
        			tmp=replacer.htmlGetter( new File(getClass().getResource("/WebContent/SubjectA/PageTypes/sepConceptBSubCall.html").getPath()) );
        	        this.setHtmlText( tmp );
        			break;
        		}
        		editorHistory.clearHistory();
        		editorHistory.addUndoHistory();
        	} );
        }
		
	}
	
	/**
	 * This method reloads initial slide layout
	 */
	public void loadInitialPage(){
		setInitialized(true);
		HTMLMerger merger = new HTMLMerger( 	getClass().getResource(CSDS_TITLE_PATH),
				getClass().getResource(CSDS_PAGE_PATH)
			);
		this.setHtmlText( merger.getFullHTMLForEditor() );
		editorHistory.addUndoHistory();
	}
	
	/**
	 * This method reloads with specified slide layout
	 */
	public void reloadWith( String htmlText ){
		this.setHtmlText( htmlText );
	}
	
	/**
	 * This method reloads last slide for some changes which requires JavaScript changes.
	 */
	public void reload(){
		//double value=0;
		
		//Node webNode = this.lookup(".web-view");
		//WebView webView = (WebView) webNode;
		//WebEngine engine = webView.getEngine();
		this.setHtmlText(this.getBasicHtml());
		//engine.loadContent(getBasicHtml());
		//engine.reload();
		//System.out.println("reload");
		//System.out.println(getBasicHtml());
		//engine.executeScript("window.scrollTo(1000, 1000);");	
	}
	
	/**
	 * This method is written for debugging
	 */
	public void write(){
		System.out.println(super.getHtmlText());
	}
	
	/**
	 * This method gets default slide layout's HTML code
	 * @return default slide layout's HTML code
	 */
	public String getBasicHtml(){
	//	System.out.println(super.getHtmlText());
		return cleaner.getCleanedHtmlText( super.getHtmlText() );
	}
	
	@Override
	public String getHtmlText() {
		// TODO Auto-generated method stub
		return getBasicHtml();
	}
	
}
