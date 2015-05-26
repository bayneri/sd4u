package sd4u.editor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Project v1.0");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui.fxml"));
		Parent parent 	= fxmlLoader.load();
				//FXMLLoader.load(getClass().getResource("gui.fxml"));
		Scene scene 	= new Scene(parent);
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}

}