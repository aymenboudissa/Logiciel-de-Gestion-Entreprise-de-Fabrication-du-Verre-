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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Personne;
public class ModifierController implements Initializable {

	    @FXML
	    private TextField inputAdresse;
	    @FXML
	    private TextField inputID;
	    
	    @FXML
	    private TextField inputSolde;

	    @FXML
	    private TextField inputNom;

	    @FXML
	    private TextField inputNum;

	    @FXML
	    private RadioButton rdClient;

	    @FXML
	    private RadioButton rdFourisseur;
	    @FXML
	    private TextField inputPrenom;
	    private DynamiqueView view=new DynamiqueView();
	    private Integer rdValue=0,valueEmpty=0;
	    Connection connection=DbConnect.getInstance().getConnection();
	    @FXML
	    void btnModifier(MouseEvent event) {
	    	
	    	ArrayList<TextField> values=getvaluesOftable();
	    	values.forEach((value)->{
				
				if(value.getText()==null || value.getText().isEmpty()) {
					value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
				    valueEmpty++;
				}
			});
			if(valueEmpty !=0) {
				view.alertError(null, "Vueiller Remplire tous les champs qui sont par couleur rouge");
				valueEmpty=0;
			}
			
			else  {
			  boolean bool= view.ValidateInteger(values.get(1).getText());
			  if(!bool) {
					view.alertError(null, "Le Num téléphone doit contient just des chiffre positives");
			  }else {
				  
				  bool= view.ValidateDouble(values.get(5).getText());
				  if(!bool) {
					  view.alertError(null, "Le Solde doit contient juste des chiffres ");
				  }else {
					  
				  
				  Personne p =new Personne();
				  p.setId(Integer.parseInt(values.get(0).getText()));
				  p.setNum_telephone(Integer.parseInt(values.get(1).getText()));
				  p.setNom(values.get(2).getText());
				  p.setPrenom(values.get(3).getText());
				  p.setAdresse(values.get(4).getText());
				  p.setSolde(values.get(5).getText());
				  if(rdClient.isSelected()) {
					  p.setId_type(rdValue);
				  }else {
					  rdValue=1;
					  p.setId_type(rdValue);
				  }
		          try {
					p.update(connection);
					 values.forEach((value)->{
		    				value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
		    		});
					
					view.alertInfo(null, "Le personne a été bien modifier");
				    clear();
		          } catch (SQLException e) {
					e.printStackTrace();
				}		  
			  }
			}
			}
	    }
	    private void clear(){
	    	ArrayList<TextField> values=getvaluesOftable();
	    	values.forEach((v)->{
	    		v.clear();
	    	});
		    	
	    }

	    private ArrayList<TextField> getvaluesOftable(){
	    	ArrayList<TextField> values=new ArrayList<>();
	    	values.add(inputID);
	    	values.add(inputNum);
	    	values.add(inputNom);
	    	values.add(inputPrenom);
	    	values.add(inputAdresse);
	    	values.add(inputSolde);
	    	return values;
	    	
	    }
	    public void setTextField(Personne p) {
	     inputID.setText(String.valueOf(p.getId()));
	   	 inputNom.setText(p.getNom());
	   	 inputPrenom.setText(p.getPrenom());
	   	 inputAdresse.setText(p.getAdresse());
	   	 String solde=p.getSolde().replace("DA", " ");
	   	 inputSolde.setText(solde);
	   	 inputNum.setText(String.valueOf(p.getNum_telephone()) );
	   	 if(p.getId_type()==0) {
	   		 rdClient.setSelected(true);
	   	 }else {
	   		 rdFourisseur.setSelected(true);
	   	 }
	    }

	    @FXML
	    void btnFermer(MouseEvent event) {
	    	view.closeStage(event);
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		}
}
