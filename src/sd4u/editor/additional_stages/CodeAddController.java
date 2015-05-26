package sd4u.editor.additional_stages;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sd4u.editor.HTMLCodeFragmentInserter;

/**
 * This class detects the path of the required code for "Add Code"
and checks the validity of the path. And then do its duty.
 */
public class CodeAddController implements Initializable{
	
	@FXML
	TextArea codeArea;
	
	@FXML
	Button addButton;
	
	@FXML
	Button cancelButton;
	
	Stage stage;
	HTMLCodeFragmentInserter inserter;
	
	final String BEGIN = "<div class=\"softdev4uContent-div-code\">\n"+
						"<pre class=\"prettyprint\">\n";
	
	final String END = "\n</pre></div>";
	
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
		
		addButton.setOnAction(e -> {
			
			String code = codeArea.getText();
			
			inserter.insertFragmentCode(BEGIN+code+END);
			
			stage.close();
		});
		
	}

}
