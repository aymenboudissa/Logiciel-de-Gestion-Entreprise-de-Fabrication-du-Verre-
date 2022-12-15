package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/login/login.fxml"));
		  Scene scene=new Scene(root);
		  controller.helpers.DynamiqueView.getPage(root, primaryStage, scene);
		  primaryStage.initStyle(StageStyle.TRANSPARENT);
		  primaryStage.show();
		}
	  public static void main(String[] args) {
		  launch(args);
	}
	
	
}
