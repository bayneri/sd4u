package sd4u.editor.additional_stages;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import sd4u.editor.HTMLCodeFragmentInserter;

/**
 * This class detects the path of the required code for "Switch to Code"
and checks the validity of the path. And then do its duty.
 * @author H. Cetiner & Y.H. Kalayci
 */
public class ChooseCodeController implements Initializable{
	
	@FXML
	Button chooseButton;
	
	@FXML
	TextField nameField;
	
	@FXML
	TextField lineNumberField;
	
	@FXML
	TextField linkTitleField;
	
	@FXML
	TextField fileField;
	
	@FXML
	Button addButton;
	
	@FXML
	Button cancelButton;
	
	@FXML
	ChoiceBox<String> languageChooser;
	
	Stage stage;
	HTMLCodeFragmentInserter inserter;
	
	final String BEGIN = "<div class=\"softdev4u-openExampleDiv\" ";
	final String LANGUAGE = "data-softdev4u-language=";
	final String NAME = "data-softdev4u-exampleName=";
	final String PATH = "data-softdev4u-filePath=";
	final String LINE_NUMBER = "data-softdev4u-lineNumber=";
	final String LINK_TITLE = "data-softdev4u-linkTitle=";
	final String END = "></div>";
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setInserter(HTMLCodeFragmentInserter inserter){
		this.inserter = inserter;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		languageChooser.setItems(FXCollections.observableArrayList(
		    "C","C++","Java","Pascal")
		);
		
		addButton.setOnAction( e->{
			
			String language = languageChooser.getValue();
			String name = nameField.getText();
			String lineNumber = lineNumberField.getText();
			String linkTitle = linkTitleField.getText();
			String path = fileField.getText();
			
			if( path.isEmpty() || linkTitle.isEmpty() || lineNumber.isEmpty() || 
			name.isEmpty() || language.isEmpty() ){
				return ;
			}
			
			String code = BEGIN + "\n" +
						  LANGUAGE + "\"" + language +"\"" + "\n" +
						  NAME + "\"" + name +"\"" + "\n" +
						  PATH + "\"" + path +"\"" + "\n" +
						  LINE_NUMBER + "\"" + lineNumber +"\"" + "\n" +
						  LINK_TITLE + "\"" + linkTitle +"\"" + 
						  END;
			
			System.out.println(code);
			
			inserter.insertFragmentCode(code);
			
			stage.close();
		});
		
		cancelButton.setOnAction( e->{
			stage.close();
		});
		
		fileField.setEditable(false);
		
		fileField.setOnMouseClicked( e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Files", "*.java", "*.cpp", "*.c","*.fpc"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null){
				fileField.setText( "file:"+selectedFile.toString() );
				//System.out.println(selectedFile);
			}
		});
		
		chooseButton.setOnAction( e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Program**** Files", "*.java", "*.cpp", "*.c","*.fpc"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null){
				fileField.setText( "file:"+selectedFile.toString() );
				//System.out.println(selectedFile);
			}
		});
		
	}

}
