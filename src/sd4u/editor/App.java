package sd4u.editor;
/**
<<<<<<< HEAD
 * @author H. Cetiner & Y.H. Kalayci
=======
 * @author H. �etiner & Y.H. Kalayc�
>>>>>>> origin/master
 * @version v1.0
 *
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is where all the application magic starts
 */
public class App extends Application{
	
	@Override
	/**
	 * This method starts the Java Application
	 */
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Project v0.0");
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