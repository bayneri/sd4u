package sd4u.editor;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class MainController implements Initializable{
	
	@FXML
	MenuBar menuBar;
	
	@FXML
	ExtendedHTMLEditor htmlEditor;
	
	String initData = "";
	
	//HTMLCodeFragmentInserter inserter = new HTMLCodeFragmentInserter();
	
	String savedHtmlText = "";
	
	HTMLEditorHistory editorHistory;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		htmlEditor.initialize();
		editorHistory = htmlEditor.getHistory();
		//htmlEditor.addEventFilter( MouseEvent.MOUSE_RELEASED , editorHistory);
		
		Menu menu = menuBar.getMenus().get(0);
		MenuItem save = new MenuItem();
		MenuItem load = new MenuItem();
		MenuItem write = new MenuItem();
		MenuItem undo = new MenuItem();
		MenuItem redo = new MenuItem();
		save.setText("Save File");
		load.setText("Load File");
		write.setText("Write");
		undo.setText("Undo");
		redo.setText("Redo");
		menuBar.getMenus().get(0).getItems().addAll(save,load,write);
		menuBar.getMenus().get(1).getItems().addAll(undo,redo);
		
		write.setOnAction(e -> {
			htmlEditor.write();
		});
		
		save.setOnAction(e -> {
			
			StringBuffer sb = new StringBuffer(getClass().getResource("/WebContent/SubjectA/SavedFiles/").toString());
			sb.replace(0, "file:/".length()-1, "");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date date = new Date();
			
			sb.append(dateFormat.format(date));
			//sb.append(".html");
			
			HTMLSaver.finish(htmlEditor.getHtmlText(),sb.toString());
			
		});
		
		undo.setOnAction(e -> {
			editorHistory.undo();
		});
		undo.setAccelerator(new KeyCodeCombination( KeyCode.Z , KeyCombination.CONTROL_DOWN ));
		
		redo.setOnAction(e -> {
			editorHistory.redo();
		});
		redo.setAccelerator(new KeyCodeCombination( KeyCode.Y , KeyCombination.CONTROL_DOWN ));
		
		htmlEditor.loadInitialPage();
		
		/*HTMLMerger merger = new HTMLMerger( 	getClass().getResource(CS_TITLE_PATH),
				getClass().getResource(CS_PAGE_PATH)
			);
		htmlEditor.setHtmlText( merger.getFullHTMLForEditor() );
		editorHistory.addUndoHistory();*/
		
	}

}
