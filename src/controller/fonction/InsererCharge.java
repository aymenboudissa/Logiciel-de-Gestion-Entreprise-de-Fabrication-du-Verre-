package controller.fonction;

import java.sql.SQLException;

import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Charge;

public class InsererCharge {
	 private DynamiqueView view= new DynamiqueView();
    @FXML
    private TextField inputCharge;

    @FXML
    private TextField inputMontant;

    @FXML
    void btnFermer(MouseEvent event) {
    	view.closeStage(event);
    }

    @FXML
    void btnInserer(MouseEvent event) {
    	 String charge=inputCharge.getText();
         String prix=inputMontant.getText();
         if(prix.isEmpty() || charge.isEmpty()) {
       	  view.ErrorOfEmpty();
         }else if(!view.ValidateDouble(prix)) {
       	  view.ErrorOfDouble(null);
         }else {
        	 Charge c= new Charge();
        	 c.setCharge(charge);
        	 c.setCout_charge(prix);
        	 c.setDate_charge(view.getDateOfThisDay());
        	 try {
				c.inserer();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	 view.alertInfo(null, "Votre Charge a été bien inserer");
       	  inputCharge.clear();
       	  inputMontant.clear();
         }
    }

}
