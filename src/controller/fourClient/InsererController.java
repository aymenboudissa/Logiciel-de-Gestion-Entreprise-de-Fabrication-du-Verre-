package controller.fourClient;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Personne;

public class InsererController implements Initializable {
	 @FXML
	    private ComboBox<Personne> cmbType;

	    @FXML
	    private TextField inputAdresse;

	    @FXML
	    private TextField inputNom;
	    @FXML
	    private TextField inputSolde;

	    @FXML
	    private TextField inputNum;
	    @FXML
	    private RadioButton rdClient;
	    @FXML
	    private RadioButton rdFournisseur;
	    @FXML
	    private TextField inputPrenom;
	    private DynamiqueView dynamique=new DynamiqueView();
	    private Integer rdValue=0,valueEmpty=0;
        Connection connection=DbConnect.getInstance().getConnection();
	    @FXML
	    void btnInserer(MouseEvent event) {
	    	ArrayList<TextField> values=getvaluesOftable();
	    	values.forEach((value)->{
				
				if(value.getText()==null || value.getText().isEmpty()) {
					value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
				    valueEmpty++;
				}
			});
			if(valueEmpty !=0) {
				dynamique.alertError(null, "Vueiller Remplire tous les champs qui sont par couleur rouge");
				valueEmpty=0;
			}
			
			else  {
			  boolean bool= dynamique.ValidateInteger(values.get(0).getText());
			  if(!bool) {
					dynamique.alertError(null, "Le Num téléphone doit contient just des chiffres positives");
			  }
			  else {
				   bool= dynamique.ValidateDouble(values.get(4).getText());
				   if(!bool) {
						dynamique.alertError(null, "Le Solde doit contient just des chiffres ");
				  }else {
					  
				  
				  Personne p =new Personne();
				  p.setNum_telephone(Integer.parseInt(values.get(0).getText()));
				  p.setNom(values.get(1).getText());
				  p.setPrenom(values.get(2).getText());
				  p.setAdresse(values.get(3).getText());
				  p.setSolde(values.get(4).getText());
				  if(rdClient.isSelected()) {
					  p.setId_type(rdValue);
				  }else {
					  rdValue=1;
					  p.setId_type(rdValue);
					  
				  }
		          try {
					p.inserer(connection);
					  values.forEach((value)->{
		    				value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
		    		});
					
					dynamique.alertInfo(null, "Le personne a été bien inserer");
					clear();
				} catch (SQLException e) {
					e.printStackTrace();
				}		  
			  }
			}
			}
	    }
	    private ArrayList<TextField> getvaluesOftable(){
	    	ArrayList<TextField> values=new ArrayList<>();
	    	values.add(inputNum);
	    	values.add(inputNom);
	    	values.add(inputPrenom);
	    	values.add(inputAdresse);
	    	values.add(inputSolde);
	    	return values;
	    	
	    	
	    }
	    private void clear(){
	    	ArrayList<TextField> values=getvaluesOftable();
	    	values.forEach((v)->{
	    		v.clear();
	    	});
		    	
	    }

	    @FXML
	    void btnFermer(MouseEvent event) {
        dynamique.closeStage(event);
	    }
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		}
}
