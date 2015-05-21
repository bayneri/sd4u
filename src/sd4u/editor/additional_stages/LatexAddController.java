package sd4u.editor.additional_stages;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sd4u.editor.HTMLCodeFragmentInserter;

public class LatexAddController implements Initializable{

	@FXML
	TextArea latexArea;
	
	@FXML
	Button addButton;
	
	@FXML
	Button cancelButton;
	
	Stage stage;
	HTMLCodeFragmentInserter inserter;
	
	public void setStage(Stage stage){
		this.stage = stage;
	}

	public void setInserter(HTMLCodeFragmentInserter inserter){
		this.inserter = inserter;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		addButton.setOnAction( e->{
			inserter.insertFragmentType(latexArea.getText());
			stage.close();
		});
		
		cancelButton.setOnAction( e->{
			stage.close();
		});
		
	}

}
