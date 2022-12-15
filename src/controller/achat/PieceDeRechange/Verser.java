package controller.achat.PieceDeRechange;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Versement;

public class Verser implements Initializable {
	    @FXML
	    private TextField inputNumAchat;

	    @FXML
	    private TextField inputVersement;
       private DynamiqueView view =new DynamiqueView();
       private void clear() {
	    	inputVersement.setText("");
	    }
       @FXML
	    void btnValider(MouseEvent event) throws SQLException {
		       Integer id=Integer.parseInt(inputNumAchat.getText()) ; 
		       String versement=inputVersement.getText();
		       if(versement.isEmpty()) {
		    	   view.alertError(null, "Vueillez remplire tous les champs");
		       }else if(!view.ValidateDouble(versement)) {
		    	   view.alertError(null, "Le versement ne doit pas contient un charactère ");
		    	   clear();
		       }else {
		    	   
		    	   Versement v= new Versement();
		    	   v.setId(id);
		    	   v.setDate_versement(view.getDateOfThisDay());
		    	   v.setVersement(versement);
		    	   v.setTypeAchat(1);
		    	   view.insererVersement(v);
		    	   view.alertInfo(null, "Le versement a été bien insérer");
		    	   view.closeStage(event);
		       }
	    }
	    @FXML
	    void btnFermer(MouseEvent event) {
	    	view.closeStage(event);
	    }
	public void setTextFieled(Integer id){
		inputNumAchat.setText(String.valueOf(id));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
