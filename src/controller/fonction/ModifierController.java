package controller.fonction;

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
import model.BisouRectelign;

public class ModifierController implements Initializable {
    @FXML
    private TextField inputFonction;

    @FXML
    private TextField inputID;
    private Connection connection=DbConnect.getInstance().getConnection();
    @FXML
    private TextField inputPrix;
    private DynamiqueView view=new DynamiqueView();
    @FXML
    void btnModifier(MouseEvent event) throws SQLException {
      String fonction=inputFonction.getText();
      String prix=inputPrix.getText();
      if(prix.isEmpty() || fonction.isEmpty()) {
    	  view.alertError(null, "Vueiller Remplire tous les champs");
      }else if(!view.ValidateDouble(prix)) {
    	  view.ErrorOfDouble(null);
      }else {
    	  BisouRectelign b = new BisouRectelign();
    	  b.setId(Integer.parseInt(inputID.getText()));
    	  b.setDesignation(fonction);
    	  b.setPrix(prix);
    	  b.update(connection);
    	  view.alertInfo(null, "Votre Activitie a été bien modifier");
    	  view.closeStage(event);
      }
    }

	public void setTextField(BisouRectelign b) {
		String prix=b.getPrix().replace("DA", "");
		inputID.setText(String.valueOf(b.getId()));
		inputPrix.setText(prix);
		inputFonction.setText(b.getDesignation());
	}
	  @FXML
	    void btnFermer(MouseEvent event) {
		  	view.closeStage(event);
	    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
