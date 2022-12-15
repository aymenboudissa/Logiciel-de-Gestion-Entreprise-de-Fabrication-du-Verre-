package controller.humaine.utilisateurs;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Utilisateur;

public class ModifierController implements Initializable {
	 @FXML
	    private TextField inputID;

	    @FXML
	    private TextField inputPassword;

	    @FXML
	    private TextField inputUser;

	    @FXML
	    private RadioButton rdNon;

	    @FXML
	    private RadioButton rdOui;

	    private Integer valueEmpty=0;
		private DynamiqueView view = new DynamiqueView();
	    @FXML
	    void btnModifier(MouseEvent event) {
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
	    		UpdateUser(items);
				view.closeStage(event);
	    	}
	    }
		private ArrayList<TextField>  getValues() {
			ArrayList<TextField> items=new ArrayList<>();
			items.add(inputID);
		    items.add(inputUser);
		    items.add(inputPassword);
		return items;
		} 
		private void UpdateUser(ArrayList<TextField> items) {
			Utilisateur u =new Utilisateur();
			u.setId(Integer.parseInt(items.get(0).getText()) );
			u.setUser(items.get(1).getText());
			u.setPassword(items.get(2).getText());
			if(rdNon.isSelected()) {
				u.setId_role(0);
			}else {
				u.setId_role(1);
			}
			try {
				u.update();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			clear(items);
			view.alertInfo(null, "Votre utilisateur a été bien inserer");

		}
		
		private void clear(ArrayList<TextField> items) {
			
		    items.forEach((field)->{
		    	field.clear();
		    });
		}
	    
		@FXML
	    void btnFermer(MouseEvent event) {
	        view.closeStage(event);
	    }

		public void setTextField(Utilisateur u) {
			inputID.setText(String.valueOf(u.getId()));
			inputUser.setText(u.getUser());
			inputPassword.setText(u.getPassword());
			if(u.getId_role()==1) {
				rdOui.setSelected(true);
			}else {
				rdNon.setSelected(true);
				
			}
		}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}

}
