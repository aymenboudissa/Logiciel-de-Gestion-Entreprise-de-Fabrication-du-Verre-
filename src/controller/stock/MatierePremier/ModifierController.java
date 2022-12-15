package controller.stock.MatierePremier;

import java.sql.Connection;
import java.sql.SQLException;

import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Matiere;

public class ModifierController {

    @FXML
    private TextField inputCretique;

    @FXML
    private TextField inputDesignation;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputInitiale;
    DynamiqueView view = new DynamiqueView();
    private Connection connection;
    @FXML
    void btnFerner(MouseEvent event) {
    view.closeStage(event);      
    }
    private void clear() {
    	inputDesignation.setText("");
    	inputCretique.setText("");
    	inputInitiale.setText("");
    }

    @FXML
    void btnValider(MouseEvent event) {
    	Integer id = Integer.parseInt(inputID.getText());
        String designation=inputDesignation.getText();
        String cretique=inputCretique.getText();
        String initiale=inputInitiale.getText();
       
        if(designation.isEmpty()|| cretique.isEmpty() || initiale.isEmpty()  ) {
        	view.alertError(null, "Vueiller remplire tous les champs");
        	clear();
        }else if(view.ValidateDouble(initiale) && view.ValidateDouble(cretique)) {
   		 try {
    		 Matiere matiere=new Matiere();
    	        matiere.setId_matiere(id);
    	        matiere.setDesignation(designation);
    	        matiere.setCretique(cretique);
    	        matiere.setInitiale(initiale);
			     updateLigne(matiere);
			     view.closeStage(event);  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        }else {
   		 view.alertError("Impossible Inserer", "Stock initiale et cretique ne contient un charactere  ");
   		clear();
   	 }
        
   	
    }
    void setTextField(Matiere m,Connection conn) {
    	connection=conn;
    	String newInitiale=m.getInitiale().replace("m2", "");
    	String newCretique=m.getCretique().replace("m2", "");
    	inputID.setText(String.valueOf(m.getId_matiere()));
    	inputDesignation.setText(m.getDesignation());
    	inputInitiale.setText(newInitiale);
        inputCretique.setText(newCretique);
    }
    private void updateLigne(Matiere m) throws SQLException {
    	m.update(connection);
    	view.alertInfo(null, "Vote Matiere premiere a été bien modifier");
    }
    		

}
