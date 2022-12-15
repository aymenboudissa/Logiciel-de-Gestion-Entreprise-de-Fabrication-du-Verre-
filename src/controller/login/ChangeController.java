package controller.login;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Utilisateur;

public class ChangeController implements Initializable {
	public boolean value=true;
	private Connection connection=DbConnect.getInstance().getConnection();
	@FXML
    private TextField inputUser;
	@FXML
    private PasswordField pwdCurrent;

    @FXML
    private PasswordField pwdNew;

    @FXML
    void btnUpdate(MouseEvent event) throws SQLException {
    	Utilisateur user= new Utilisateur();
    	user.setUser(inputUser.getText());
    	user.setPassword(pwdCurrent.getText());	    	
    	user.setNewPassword(pwdNew.getText());	    	
    	if(inputUser.getText().isEmpty() || pwdCurrent.getText().isEmpty() || pwdNew.getText().isEmpty()) {
    		DynamiqueView.getInstance().alertError("Erreur d'accés", "Vueillez remplire tous les champs");
    	}else {
//    		verfier le mot de passe et utilisateur :
    		
    		boolean result =DynamiqueView.getInstance().VerifyUser(user,connection);
    		
    		if(result) {
//    			modifier le mot de passe :
    			String query="update utilisateurs set password='"+user.getNewPassword()+"' "
    					+ "where user='"+user.getUser()+"'";
    			PreparedStatement statement = connection.prepareStatement(query);
			    	 this.value=statement.execute();
			    	 MainController main=new MainController();
			    	 main.setValue(this.value);
 			    	connection.close();
			    	getPageLogin(event);
    		
    		}else {DynamiqueView.getInstance().alertError("Erreur d'accés", "Le nom d'utlisateur ou mot de passe est incorrecte");}
    	    	
    	}
    	
    	
    }

    
    @FXML
    void txtReturn(MouseEvent event) {
    	getPageLogin(event);
    }
    
    private void getPageLogin(MouseEvent event) {

    	DynamiqueView.getInstance().getPageOfButton(event, "login/login");
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
