package controller.humaine.utilisateurs;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import model.Utilisateur;

public class InsererController {
     
	private DynamiqueView view = new DynamiqueView();
    @FXML
    private ToggleGroup group;

    @FXML
    private TextField inputNomUtlisateur;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private RadioButton rdNon;

    @FXML
    private RadioButton rdOui;
    private Integer valueEmpty=0;
    @FXML
    void btnInserer(MouseEvent event) {
    	ArrayList<TextField> items=getValues();
    	items.forEach((field)->{
    		if(field.getText()==null || field.getText().isEmpty()) {
    			field.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
			    valueEmpty++;
			}
    	});
    	if(valueEmpty !=0) {
    		view.alertError(null, "Vueiller Remplire tous les champs qui sont par couleur rouge");
			valueEmpty=0;
		
    	}else {
    		getUser(items);
    	}
    }
	private ArrayList<TextField>  getValues() {
		ArrayList<TextField> items=new ArrayList<>();
	    items.add(inputNomUtlisateur);
	    items.add(inputPassword);
	return items;
	} 
	private void clear(ArrayList<TextField> items) {
	    items.forEach((field)->{
	    	field.clear();
	    });
	}
    @FXML
    void fermer(MouseEvent event) {
        view.closeStage(event);
    }
    private void getUser(ArrayList<TextField> items) {
    	Utilisateur u =new Utilisateur();
		u.setPassword(items.get(1).getText());
		u.setUser(items.get(0).getText());
		if(rdNon.isSelected()) {
			u.setId_role(0);
		}else {
			u.setId_role(1);
		}
		try {
			items.forEach((field)->{
		    	field.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
		    });
			u.inserer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear(items);
		view.alertInfo(null, "Votre utilisateur a été bien inserer");
    }

}
