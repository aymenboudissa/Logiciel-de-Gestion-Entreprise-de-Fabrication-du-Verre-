package controller.login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.SimpleCacheManager;
import model.Utilisateur;

public class MainController implements Initializable  {
    private boolean value=true;
    
	@FXML
	    private ToggleGroup admin;

	    @FXML
	    private PasswordField inputPassword;

	    @FXML
	    private TextField inputUser;
	    @FXML
	    private RadioButton rdNon;
            @FXML
           private Button btnConnect;
        public  String txtuser;
	    @FXML
	    private RadioButton rdOui;
	    public SimpleCacheManager cache=SimpleCacheManager.getInstance();
	    @FXML
	    void btnConnecter(MouseEvent event) throws SQLException, FileNotFoundException {
	    	Utilisateur user= new Utilisateur();
	    	String user1=inputUser.getText();
	    	String password=inputPassword.getText();
	    	user.setUser(user1);
	    	user.setPassword(password);	    	
	    	if(inputUser.getText().isEmpty() || inputPassword.getText().isEmpty()) {
	    		DynamiqueView.getInstance().alertError("Erreur d'accés", "Vueillez remplire tous les champs");
	    	}else {
	    		Connection conn=DbConnect.getInstance().getConnection();
	    		boolean result =DynamiqueView.getInstance().VerifyUser(user,conn);
	    		String output = user1.substring(0, 1).toUpperCase() + user1.substring(1);
	    		cache.put(1,output);
	    		if(result) {
	    			conn.close();
			    	DynamiqueView.getInstance().getPageOfButton(event, "index");
	    	}else {DynamiqueView.getInstance().alertError("Erreur d'accés", "Le nom d'utlisateur ou mot de passe est incorrecte");}
	    	}
	    }

	    @FXML
	    void txtChange(MouseEvent event) {
	    	try {
				Parent root= FXMLLoader.load(getClass().getResource("/view/login/modifier.fxml"));
			   Node node=(Node) event.getSource();
			   Stage stage=(Stage) node.getScene().getWindow();
			   Scene scene = new Scene(root);
	    	   controller.helpers.DynamiqueView.getPage(root, stage, scene); 
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    @FXML
	    void fermer(MouseEvent event) {
             System.exit(0);         
	    }
	    
	    public void setValue(boolean value) {
	    	this.value=value;
	    	if(!this.value) {
	    		 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setHeaderText(null);
                 alert.setContentText("Le mot de passe a été bien modifier");
                 alert.showAndWait();
			}
	    	
	    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
