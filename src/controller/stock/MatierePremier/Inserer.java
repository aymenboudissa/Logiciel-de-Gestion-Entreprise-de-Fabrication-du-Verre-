package controller.stock.MatierePremier;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Matiere;

public class Inserer implements Initializable{
   private DynamiqueView view = new DynamiqueView();
    @FXML
    private TextField inputDesignation;

    @FXML
    private TextField inputStockCretique;

    @FXML
    private TextField inputStockInitiale;

    @FXML
    void fermer(MouseEvent event) {
    view.closeStage(event); 
	
    }

    @FXML
    void valider(MouseEvent event) throws IOException {
     Matiere premier = new Matiere(); 
   	 String designation = inputDesignation.getText();
     String cretique= inputStockCretique.getText();
     String initiale= inputStockInitiale.getText();
     if(designation.isEmpty() || cretique.isEmpty() || initiale.isEmpty()) {
    	view.alertError("Impossible Inserer", "Les champs ne doit pas être vide  ");
     }else if(view.ValidateDouble(initiale) &&  view.ValidateDouble(cretique)) {
    	 premier.setDesignation(designation);
    	 premier.setCretique(cretique);                        
    	 premier.setInitiale(initiale);
    	 try {
			InsererMatiere(premier);
			inputDesignation.clear();;
		     inputStockCretique.clear();
		     inputStockInitiale.clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}
     }else {
    	 view.alertError("Impossible Inserer", "Stock initiale et cretique ne contient un charactere  ");
     }
    }
    
   private void InsererMatiere(Matiere premier) throws SQLException, IOException{
	   Connection connection=DbConnect.getInstance().getConnection();
	   premier.inserer(connection);
//	   MAke alerte de success
	   view.alertInfo(null, "Vote Matiere premiere a été bien inserer");

   }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
      		
	}

}
