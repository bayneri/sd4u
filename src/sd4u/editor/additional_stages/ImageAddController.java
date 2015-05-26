package sd4u.editor.additional_stages;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import sd4u.editor.HTMLCodeFragmentInserter;

/**
 * This class detects the path of the required code for "Add Image"
and checks the validity of the path. And then do its duty.
 */
public class ImageAddController implements Initializable{
	
	@FXML
	Pane pane;
	
	@FXML
	Button addButton;
	
	@FXML
	Button cancelButton;
	
	@FXML
	Button chooseButton;
	
	@FXML
	TextField urlField;
	
	@FXML
	TextField widthField;
	
	@FXML
	TextField heightField;
	
	@FXML
	TextField fileField;
	
	Stage stage;
	HTMLCodeFragmentInserter inserter;
	
	final String IMG_BEGIN = "<img src=\"";
	final String IMG_END = " alt=\"IMAGE\" >";
	final String IMG_WIDTH = "  width=\"";
	final String IMG_HEIGHT = "  height=\"";
	
	public void setStage(Stage stage){
		this.stage = stage;
	}

	public void setInserter(HTMLCodeFragmentInserter inserter){
		this.inserter = inserter;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		cancelButton.setOnAction(e -> {
			stage.close();
		});
		
		addButton.setOnAction( e->{
			//bu degerlerin 1..100 arasinda mi diye check edilmesi lazim.
			//ayrica height icinde editlenmesi lazim buranin.
			//son asamada bununla ilginecegim.
			
			String widthText = widthField.getText();
			//String heightText = heightField.getText();
			//Double width = new Double(widthText);
			//Double height = new Double(heightText);
			
			String url=fileField.getText();
			
			System.out.println(url);
			
			if( url.isEmpty() ){
				url = urlField.getText();
			}
			
			//ImageDownloader downloader = new ImageDownloader();
			
			if( !url.isEmpty() ){
				
				/*try {
					String tmp = downloader.downloadImage(url);
					System.out.println(tmp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					stage.close();
					return;
				}*/
				
				System.out.println("image  +++   " + url);
				
				StringBuffer sb=new StringBuffer();
				
				sb.append( IMG_BEGIN );
				sb.append( url );
				sb.append( "\"" );
				sb.append( IMG_WIDTH );
				sb.append( widthText + "%" );
				sb.append( "\"" );
				sb.append( IMG_END );
				
				System.out.println(sb.toString());
				
				inserter.insertFragmentCode(sb.toString() );
			}
			
			stage.close();
		});
		
		fileField.setEditable(false);
		
		fileField.setOnMouseClicked( e->{
			FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open Resource File");
			 fileChooser.getExtensionFilters().addAll(
			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
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
			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
			         new ExtensionFilter("All Files", "*.*"));
			 File selectedFile = fileChooser.showOpenDialog(stage);
			 if (selectedFile != null){
				 fileField.setText( "file:"+selectedFile.toString() );
				 //System.out.println(selectedFile);
			 }
		});
		
	}

}
