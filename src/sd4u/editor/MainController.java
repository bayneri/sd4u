package sd4u.editor;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * This class provides adding tool bar to HTML editor
 * @author H. Cetiner & Y.H. Kalayci 
 */
public class MainController implements Initializable{
	
	@FXML
	MenuBar menuBar;
	
	@FXML
	ListView<CustomCellContent> slideList;
	
	@FXML
	ExtendedHTMLEditor htmlEditor;
	
	@FXML
	SplitPane splitPane;
	
	String initData = "";
	
	//HTMLCodeFragmentInserter inserter = new HTMLCodeFragmentInserter();
	
	String savedHtmlText = "";
	
	HTMLEditorHistory editorHistory;
	
	final String NEW_SLIDE = "Add New Slide";
	
	public static CustomCellContent lastSlide;
	public static int lastIndex;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<CustomCellContent> list = FXCollections.observableArrayList( new CustomCellContent(NEW_SLIDE) );
		
		slideList.setItems(list);
		
		htmlEditor.initialize(slideList,list);
		editorHistory = htmlEditor.getHistory();
		//htmlEditor.addEventFilter( MouseEvent.MOUSE_RELEASED , editorHistory);
		
		final ContextMenu cMenu = new ContextMenu();
		MenuItem delete = new MenuItem("Delete");
		MenuItem addNext = new MenuItem("Add new slide");
		cMenu.getItems().addAll(delete,addNext);
		
		slideList.setContextMenu(cMenu);
		slideList.getContextMenu().setAutoHide(true);
		
		delete.setOnAction(e -> {
			int index=slideList.getSelectionModel().getSelectedIndex();
			list.remove(index);
			
			//System.out.println("delete " + index );
		});
		
		addNext.setOnAction(e -> {
			int index=slideList.getSelectionModel().getSelectedIndex();
			list.add(index+1,new CustomCellContent("slide" + (list.size())) );
			System.out.println("add next " + index );
		});
		
		slideList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent click) {
				// TODO Auto-generated method stub
				if( click.getButton() == MouseButton.SECONDARY ){
					
					slideList.getContextMenu().show(slideList,click.getSceneX(),click.getSceneY());
					
				}
				if (click.getClickCount() == 1) {
		           //Use ListView's getSelected Item
					CustomCellContent item=slideList.getSelectionModel().getSelectedItem();
					int index = slideList.getSelectionModel().getSelectedIndex();
					
					if( item.title.equals( NEW_SLIDE ) ){
						list.remove(list.size()-1);
						list.add( new CustomCellContent("slide" + (list.size()+1)) );
						list.add( new CustomCellContent(NEW_SLIDE) );
						if(lastSlide!=null)
							slideList.getSelectionModel().select(lastIndex);
						System.out.println("new slide");
					}
					else{
						System.out.println(item.title + "\n" + item.htmlText);
						if( lastSlide!=null )
						{
							try {
								System.out.println("lastSlide: "+lastSlide.title+"\n" + htmlEditor.getBasicHtml());
								lastSlide.htmlText=htmlEditor.getBasicHtml();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						lastIndex=index;
						lastSlide=item;
						htmlEditor.textField.setText(item.title);
						htmlEditor.reloadWith( item.htmlText );
						editorHistory.setContent(item.undoHistory, item.redoHistory);
						editorHistory.addUndoHistory();
						item.title=item.title;
					}
		           //use this to do whatever you want to. Open Link etc.
		        }
			}
		});
		
		Menu menu = menuBar.getMenus().get(0);
		MenuItem saveProject = new MenuItem();
		MenuItem save = new MenuItem();
		MenuItem load = new MenuItem();
		MenuItem write = new MenuItem();
		MenuItem undo = new MenuItem();
		MenuItem redo = new MenuItem();
		saveProject.setText("Save Project");
		save.setText("Save File");
		load.setText("Load File");
		write.setText("Write");
		undo.setText("Undo");
		redo.setText("Redo");
		menuBar.getMenus().get(0).getItems().addAll(save,load,saveProject,write);
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
			
			HTMLSaver.finish(htmlEditor.getHtmlText(),sb.toString(),HTMLSaver.FILE);
			
		});
		
		saveProject.setOnAction(e -> {
			lastSlide.htmlText=htmlEditor.getBasicHtml();
			ProjectSaver saver = new ProjectSaver();
			saver.save( list );
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
		
	}

}
